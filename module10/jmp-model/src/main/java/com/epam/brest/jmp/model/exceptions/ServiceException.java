package com.epam.brest.jmp.model.exceptions;

import static java.lang.String.format;

/**
 * Created by alexander_borohov on 17.3.17.
 */
public class ServiceException extends RuntimeException {
    public ServiceException(Object object, String crudOperation) {
        super(format("Problem in Service with: %s, while performing: %s",
                (object != null) ? object.toString() : "null", crudOperation));
    }
}
