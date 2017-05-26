package com.epam.brest.jmp.model.exceptions;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * Created by alexander_borohov on 26.5.17.
 */
public class RestServiceExceptionTest {
    @Test
    public void constructorTest() throws Exception {
        RestServiceException exception = new RestServiceException("testMessage");
        assertNotNull(exception);
    }

}