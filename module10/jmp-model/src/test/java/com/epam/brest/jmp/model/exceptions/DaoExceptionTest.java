package com.epam.brest.jmp.model.exceptions;

import static org.junit.Assert.assertNotNull;

import com.epam.brest.jmp.model.Task;
import org.junit.Test;

/**
 * Created by alexander_borohov on 26.5.17.
 */
public class DaoExceptionTest {
    @Test
    public void constructorMethod() throws Exception {
        DaoException exception = new DaoException(new Task(), "READ");
        assertNotNull(exception);
    }
}