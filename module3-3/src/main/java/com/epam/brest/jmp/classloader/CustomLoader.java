package com.epam.brest.jmp.classloader;

import sun.misc.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * This classloader is able to load class at runtime from compiled sources in provided
 * source path (via system property -Dcp)
 * Created by alexander_borohov on 25.2.17.
 */
public class CustomLoader extends ClassLoader {
    private final String classToLoad = "com.epam.brest.jmp.semaphore.Semaphore";
    private final String className = "Semaphore";

    public CustomLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {

        byte[] classBytes = getClassBytes();
        if (classBytes != null) {
            return defineClass(classToLoad, classBytes, 0, classBytes.length);
        } else {
            throw new ClassNotFoundException();
        }
    }

    private byte[] getClassBytes() {
        String classpath = System.getProperty("cp", null);
        if (classpath != null) {
            try {
                InputStream stream = this.getClass().getClassLoader().getResourceAsStream(classpath + "/" + className + ".class");
                return IOUtils.readFully(stream, -1, false);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            System.out.println("You did not provide -Dcp system property! Try again later");
            return null;
        }
    }


}
