package com.epam.brest.jmp.model.exceptions;

/**
 * Simple exception for use in a rest layer
 * Created by alexander_borohov on 19.3.17.
 */
public class RestServiceException extends RuntimeException {
    public RestServiceException(String message) {
        super(message);
    }
}
