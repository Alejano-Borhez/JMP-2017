package com.epam.brest.jmp.exception;

/**
 * Simple Exception to throw in case of Re-loading of a class fails
 * Created by alexander_borohov on 24.2.17.
 */
public class ClassNotReloadedException extends ClassNotFoundException {
    public ClassNotReloadedException() {
        super();
    }

    public ClassNotReloadedException(String s, Throwable ex) {
        super(s, ex);
    }

    public ClassNotReloadedException(String s) {
        super(s);
    }
}
