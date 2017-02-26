package com.epam.brest.jmp.classloader;

import static java.lang.String.format;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * This classloader is implemented using hard-coded class-source path: {@link CustomClassLoader#pathToJars )}
 * Further it could be extended to use different sources or to set them through properties
 * <p>
 * Created by alexander_borohov on 24.2.17.
 */
public class CustomClassLoader extends ClassLoader {
    private final Logger logger = LogManager.getLogger(CustomClassLoader.class);

    //  This archive contains classes to be loaded dynamically
    private String pathToJars = "com/epam/brest/jmp/extensions/classes.jar";
    //  This is internal cache
    private Map<String, Class<?>> classes;
    private String className;

    public Map<String, Class<?>> getClasses() {
        return classes;
    }

    public void removeClass(String name) {
        this.classes.remove(name);
    }

    public void addClass(String name, Class<?> clazz) {
        this.classes.put(name, clazz);
    }

    public void setPathToJars(String pathToJars) {
        this.pathToJars = pathToJars;
    }

    public CustomClassLoader() {
        super(CustomClassLoader.class.getClassLoader());
        this.classes = new HashMap<>();
    }

    public CustomClassLoader(ClassLoader parent) {
        super(parent);
        this.classes = new HashMap<>();
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
//     Firstly check if class is already loaded
        Class<?> clazz = classes.get(name);

        if (clazz == null) {
//            Trying to get class source
            byte[] classSource = getBytes(name);
//            Trying to define a class
            clazz = super.defineClass(className, classSource, 0, classSource.length);
            addClass(name, clazz);
        }
        return clazz;
    }

    private byte[] getBytes(String name) throws ClassNotFoundException {
        JarFile file;
        Path tempFile = null;
        name = prepareClassName(name);
        try {
//            Getting inputStream from sources dir
            InputStream absolutePath = this.getClass().getClassLoader().getResourceAsStream(pathToJars);
            tempFile = Files.createTempFile(Paths.get(System.getProperty("user.home")), "source", ".jar");
            Files.copy(absolutePath, tempFile, REPLACE_EXISTING);
//            Obtaining JarFile instance from temp file
            file = new JarFile(tempFile.toFile());
            JarEntry neededClassEntry = null;
            Enumeration enumeration = file.entries();

            while (enumeration.hasMoreElements()) {
                JarEntry entry = (JarEntry) enumeration.nextElement();
                File file1 = new File(entry.getName());
                String className = file1.getName();
//                Checking if class is found by its name (package is ignored for now)
                if (entry.getName().endsWith(".class") &&
                        className.replaceAll("\\.class", "").equals(name)) {
                    neededClassEntry = entry;
                    break;
                }
            }
//            Setting a fully qualified class name (Package + className)
            this.className = getClassName(neededClassEntry);
            return getBytes0(file, neededClassEntry);
        } catch (IOException e) {
            logger.debug("Error while loading jar: {}", e.getMessage());
            throw new ClassNotFoundException(format("Specified jarFile not found %s", name));
        } catch (NullPointerException e) {
            logger.debug("Resource path is incorrect: {}", e.getMessage());
            throw new ClassNotFoundException(format("Specified resource not found %s", pathToJars));
        } finally {
            try {
                if (tempFile != null)
                    Files.deleteIfExists(tempFile);
            } catch (IOException e) {
            }
        }
    }

    private String prepareClassName(String name) {
        if (name.contains(".")) {
            String[] strings = className.split(name);
            if (strings[strings.length - 1].equals(".class") && strings.length >= 2) {
                return strings[strings.length - 2];
            }
            return strings[strings.length - 1];
        }
        return name;
    }

    private String getClassName(JarEntry neededClassEntry) {
        return neededClassEntry.getName().replaceAll("\\.class", "").replaceAll("/", ".");
    }

    private byte[] getBytes0(JarFile file, JarEntry neededClassEntry) throws IOException {
        byte[] bytes = new byte[(int) neededClassEntry.getSize()];
        InputStream stream = file.getInputStream(neededClassEntry);
        stream.read(bytes);
        return bytes;
    }
}
