package com.epam.brest.jmp.model.exceptions;

import static java.lang.String.format;

/**
 * Generic Exception for DAO
 * Created by alexander_borohov on 17.3.17.
 */
public class DaoException extends RuntimeException {
    public DaoException(Object object, String crudOperation) {
        super(format("Problem in DAO with: %s, while performing: %s",
                (object != null) ? object.toString() : "null", crudOperation));
    }
}
