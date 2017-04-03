package com.epam.brest.jmp.dao.impl;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.epam.brest.jmp.dao.mapper.EntityRowMapper;
import com.epam.brest.jmp.model.User;
import com.epam.brest.jmp.test.config.TestDaoConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * For test purposes are used singleton-scoped beans for {@link MapSqlParameterSource}, {@link KeyHolder} and {@link NamedParameterJdbcOperations}
 * Created by alexander_borohov on 1.4.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDaoConfig.class)
@DirtiesContext
public class UserORMDaoTest {
    private static final Integer TEST_USER_ID = 1;
    private static final String TEST_USER_NAME_FIRST = "user1 test name";
    private static final String TEST_USER_NAME_SECOND = "user2 test name";
    private static final String TEST_USER_SURNAME_FIRST = "user1 test surname";
    private static final String TEST_USER_SURNAME_SECOND = "user2 test surname";
    private static final String TEST_USER_EMAIL_FIRST = "test1@email.com";
    private static final String TEST_USER_EMAIL_SECOND = "test2@email.com";
    private User testUser;

    private final Map<String, Object> params = new HashMap<>();
    @Autowired
    private UserOrmDao userDao;
    @Autowired
    private NamedParameterJdbcOperations mockJdbc;
    @Autowired
    private MapSqlParameterSource testParamSource;
    @Autowired
    private KeyHolder mockKeyHolder;
    @Autowired
    private EntityRowMapper<User> userEntityRowMapper;

    @Before
    public void setUp() throws Exception {


    }

    @After
    public void tearDown() throws Exception {
        verify(mockJdbc, mockKeyHolder);
        reset(mockJdbc, mockKeyHolder);
        params.clear();
    }

    @Test
    public void userCreateTest() throws Exception {
        testUser = new User(TEST_USER_NAME_FIRST, TEST_USER_SURNAME_FIRST, TEST_USER_EMAIL_FIRST);
        params.put("user_name", TEST_USER_NAME_FIRST);
        params.put("user_surname", TEST_USER_SURNAME_FIRST);
        params.put("user_email", TEST_USER_EMAIL_FIRST);

        testParamSource.addValues(params);

        String testSql = "INSERT INTO users(user_email, user_name, user_surname) VALUES (:user_email, :user_name, :user_surname);";

        expect(mockJdbc.update(testSql, testParamSource, mockKeyHolder)).andReturn(1);
        expect(mockKeyHolder.getKey()).andReturn(2);

        replay(mockJdbc, mockKeyHolder);

        userDao.create(testUser);
    }

    @Test
    public void userReadTest() throws Exception {
        testUser = new User(TEST_USER_NAME_FIRST, TEST_USER_SURNAME_FIRST, TEST_USER_EMAIL_FIRST);
        params.put("user_id", TEST_USER_ID);
        testParamSource.addValues(params);

        String testSql = "SELECT * FROM users WHERE user_id = :user_id";

        expect(mockJdbc.queryForObject(testSql, testParamSource, userEntityRowMapper)).andReturn(testUser);
        replay(mockJdbc, mockKeyHolder);

        User userTest = userDao.read(TEST_USER_ID);

        assertEquals("Returned user was incorrect!", userTest, testUser);
    }

    @Test
    public void userUpdateTest() throws Exception {
        testUser = new User(TEST_USER_NAME_SECOND, TEST_USER_SURNAME_SECOND, TEST_USER_EMAIL_SECOND);
        testUser.setId(TEST_USER_ID);

        params.put("user_name", TEST_USER_NAME_SECOND);
        params.put("user_surname", TEST_USER_SURNAME_SECOND);
        params.put("user_email", TEST_USER_EMAIL_SECOND);

        testParamSource.addValues(params);

        String testSql = "UPDATE users SET user_name = :user_name, user_surname = :user_surname, user_email = :user_email WHERE user_id = :user_id";

        expect(mockJdbc.update(testSql, testParamSource)).andReturn(1);
        replay(mockJdbc, mockKeyHolder);

        User updatedUser = userDao.update(testUser);

        assertEquals("Different names!", updatedUser.getName(), testUser.getName());
    }

    @Test
    public void userDeleteTest() throws Exception {
        testParamSource.addValue("user_id", TEST_USER_ID);

        String testSql = "DELETE FROM users WHERE user_id = :user_id";

        expect(mockJdbc.update(testSql, testParamSource)).andReturn(1);
        replay(mockJdbc, mockKeyHolder);

        assertTrue("Delete incorrect", userDao.delete(TEST_USER_ID));
    }

    @Test
    public void userGetAllTest() throws Exception {
        testUser = new User(TEST_USER_NAME_FIRST, TEST_USER_SURNAME_FIRST, TEST_USER_EMAIL_FIRST);
        User secondUser = new User(TEST_USER_NAME_SECOND, TEST_USER_SURNAME_SECOND, TEST_USER_EMAIL_SECOND);

        List<User> users = new ArrayList<>();

        users.add(testUser);
        users.add(secondUser);

        String testSql = "SELECT * FROM users";

        expect(mockJdbc.query(testSql, testParamSource, userEntityRowMapper)).andReturn(users);
        replay(mockJdbc, mockKeyHolder);

        List<User> gotUsers = new ArrayList<>(userDao.readAll());

        assertEquals("Did not read a list", gotUsers.size(), users.size());
        assertTrue("Different lists!", gotUsers.containsAll(users));
    }
}
