package com.epam.brest.jmp.classloader;

import com.epam.brest.jmp.exception.ClassNotReloadedException;

/**
 * ClassLoader sets a source to load class from through {@link ReloadClassLoader#setPathToJars(String)}
 *
 * <p>
 * Created by alexander_borohov on 24.2.17.
 */
public class ReloadClassLoader extends CustomClassLoader {
    public ReloadClassLoader() {
        super(ReloadClassLoader.class.getClassLoader());
    }

    public Class<?> reloadClass(String name) throws ClassNotReloadedException {
        try {
            return findClass(name);
        } catch (ClassNotFoundException e) {
            throw new ClassNotReloadedException("Class was not reloaded!", e);
        }
    }
}
