package com.epam.brest.jmp.dao.mapper;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;

import com.epam.brest.jmp.model.Task;
import com.epam.brest.jmp.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.ResultSet;
import java.util.List;

/**
 * Testing
 * Created by alexander_borohov on 25.5.17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ResultSet.class)
@Ignore
public class EntityRowMapperTest {
    private User testUser;
    private List<Task> tasks;
    private final Integer TEST_USER_ID = 1;
    private final String TEST_USER_NAME = "TestName";
    private final String TEST_USER_SURNAME = "TestSurname";
    private final String TEST_USER_EMAIL = "TestEmail";

    private ResultSet resultSet;
    private EntityRowMapper<User> rowMapper;

    @Before
    public void setUp() throws Exception {
        resultSet = PowerMockito.mock(ResultSet.class);

        testUser = new User(TEST_USER_NAME, TEST_USER_SURNAME, TEST_USER_EMAIL);
        testUser.setId(TEST_USER_ID);
        when(resultSet.getString("user_name")).thenReturn(TEST_USER_NAME);
        when(resultSet.getString("user_surname")).thenReturn(TEST_USER_SURNAME);
        when(resultSet.getString("user_email")).thenReturn(TEST_USER_EMAIL);
        when(resultSet.getInt("user_id")).thenReturn(TEST_USER_ID);

        rowMapper = new EntityRowMapper<>(User.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void mapRow() throws Exception {
        User newUser = rowMapper.mapRow(resultSet, 1);
        assertEquals(newUser, testUser);
    }

}