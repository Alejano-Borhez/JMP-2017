package com.epam.brest.jmp.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.epam.brest.jmp.model.User;
import com.epam.brest.jmp.model.exceptions.DaoException;
import com.epam.brest.jmp.test.config.TestDaoConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Testing on a "real" database
 * Created by alexander_borohov on 3.4.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDaoConfig.class)
@Transactional
@ActiveProfiles({"INMEMORY", "custom"})
public class UserORMDaoInMemoTest {
    private static final Integer TEST_USER_ID = 1;
    private static final String TEST_USER_NAME_FIRST = "USER NAME 1";
    private static final String TEST_USER_NAME_SECOND = "USER SURNAME 2";
    private static final String TEST_USER_SURNAME_FIRST = "USER SURNAME 1";
    private static final String TEST_USER_SURNAME_SECOND = "USER SURNAME 2";
    private static final String TEST_USER_EMAIL_FIRST = "test1@mail.com";
    private static final String TEST_USER_EMAIL_SECOND = "test2@mail.com";
    private User testUser;

    private final Map<String, Object> params = new HashMap<>();
    @Autowired
    private UserOrmDao userDao;

    @Test
    public void testGetUser() throws Exception {
        User user = userDao.read(TEST_USER_ID);

        assertNotNull("Returned null result", user);
        assertEquals("Different names", user.getName(), TEST_USER_NAME_FIRST);
        assertEquals("Different surnames", user.getSurname(), TEST_USER_SURNAME_FIRST);
        assertEquals("Different emails", user.getEmail(), TEST_USER_EMAIL_FIRST);

    }

    @Test
    public void createUserTest() throws Exception {
        testUser = new User(TEST_USER_NAME_FIRST, TEST_USER_SURNAME_FIRST, TEST_USER_EMAIL_FIRST);

        Integer newId = userDao.create(testUser);

        assertNotNull(newId);
        testUser.setId(newId);
//        assertTrue("Not incremented: " + newId, newId == 2);

        User user = userDao.read(newId);
        assertEquals("Not created", testUser, user);
    }

    @Test
    public void updateUserTest() throws Exception {
        testUser = new User(TEST_USER_NAME_SECOND, TEST_USER_SURNAME_SECOND, TEST_USER_EMAIL_SECOND);

        testUser.setId(TEST_USER_ID);

        User user = userDao.update(testUser);

        assertEquals("Not updated!", testUser, user);
    }

    @Test(expected = DaoException.class)
    public void deleteUserTest() throws Exception {
        testUser = new User(TEST_USER_NAME_FIRST, TEST_USER_SURNAME_FIRST, TEST_USER_EMAIL_FIRST);

        Integer newId = userDao.create(testUser);

        Boolean result = userDao.delete(newId);
        testUser = userDao.read(newId);
        assertNull(testUser);
        assertTrue(result);
        assertFalse(userDao.delete(newId));
    }
}
