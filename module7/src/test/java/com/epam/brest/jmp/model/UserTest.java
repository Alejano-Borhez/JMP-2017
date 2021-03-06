package com.epam.brest.jmp.model;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by alexander_borohov on 17.3.17.
 */
public class UserTest {
    private User testUser;
    private final Integer TEST_USER_ID = 1;
    private final String TEST_USER_NAME = "TestName";
    private final String TEST_USER_SURNAME = "TestSurname";
    private final String TEST_USER_EMAIL = "TestEmail";

    @Before
    public void setUp() throws Exception {
        this.testUser = new User();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getId() throws Exception {
        testUser.setId(TEST_USER_ID);
        assertEquals("Id's are not equal.", TEST_USER_ID, testUser.getId());
    }

    @Test
    public void getName() throws Exception {
        testUser.setName(TEST_USER_NAME);
        assertEquals("Names are not equal.", TEST_USER_NAME, testUser.getName());
    }

    @Test
    public void getSurname() throws Exception {
        testUser.setSurname(TEST_USER_SURNAME);
        assertEquals("Surnames are not equal.", TEST_USER_SURNAME, testUser.getSurname());
    }

    @Test
    public void getEmail() throws Exception {
        testUser.setEmail(TEST_USER_EMAIL);
        assertEquals("Emails are not equal.", TEST_USER_EMAIL, testUser.getEmail());
    }

}