package com.epam.brest.jmp.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexander_borohov on 17.3.17.
 */
public class UserTest {
    private User testUser;
    private List<Task> tasks;
    private final Integer TEST_USER_ID = 1;
    private final String TEST_USER_NAME = "TestName";
    private final String TEST_USER_SURNAME = "TestSurname";
    private final String TEST_USER_EMAIL = "TestEmail";

    @Before
    public void setUp() throws Exception {
        this.testUser = new User();
        this.tasks = new ArrayList<>();
        tasks.add(new Task("testDesc", TEST_USER_ID));
        tasks.add(new Task("testDesc1", TEST_USER_ID));
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

    @Test
    public void getTasks() throws Exception {
        testUser.setUserTasks(tasks);
        assertEquals("Tasks are different.", tasks, testUser.getUserTasks());
    }

    @Test
    public void constructorsTests_defaultConstructor() throws Exception {
        User testUser = new User();
        assertNotNull("Did not create a User", testUser);
        assertNull("Not null name", testUser.getName());
        assertNull("Not null surname", testUser.getSurname());
        assertNull("Not null email", testUser.getEmail());
        assertNull("Not null id", testUser.getId());
        assertNull("Not null tasks", testUser.getUserTasks());
    }

    @Test
    public void constructorsTests_ConstructorWithParameters() throws Exception {
        User testUser = new User(TEST_USER_NAME, TEST_USER_SURNAME, TEST_USER_EMAIL);
        assertNotNull("Did not create a User", testUser);
        assertEquals("Null name", testUser.getName(), TEST_USER_NAME);
        assertEquals("Null surname", testUser.getSurname(), TEST_USER_SURNAME);
        assertEquals("Null email", testUser.getEmail(), TEST_USER_EMAIL);
        assertNull("Not null id", testUser.getId());
        assertNull("Not null tasks", testUser.getUserTasks());
    }

    @Test
    public void toStringTest() throws Exception {
        User testUser = new User(TEST_USER_NAME, TEST_USER_SURNAME, TEST_USER_EMAIL);
        testUser.setId(TEST_USER_ID);
        assertEquals("User{" +
                "id=" + testUser.getId() +
                ", name='" + testUser.getName() + '\'' +
                ", surname='" + testUser.getSurname() + '\'' +
                ", email='" + testUser.getEmail() + '\'' +
                '}', testUser.toString());
    }

    @Test
    public void equalsTest() throws Exception {
        User user = new User();
        User user1 = user;

        assertTrue(user.equals(user1));
        User testUser1 = new User(TEST_USER_NAME, TEST_USER_SURNAME, TEST_USER_EMAIL);
        User testUser2 = new User(TEST_USER_NAME, TEST_USER_SURNAME, TEST_USER_EMAIL);

        assertTrue(testUser1.equals(testUser2));

        assertEquals(testUser1.hashCode(), testUser2.hashCode());

        testUser1 = null;

        assertFalse(testUser2.equals(testUser1));
        assertFalse(testUser2.equals(new Task()));
        testUser2 = null;

        assertEquals(testUser1, testUser2);
    }
}