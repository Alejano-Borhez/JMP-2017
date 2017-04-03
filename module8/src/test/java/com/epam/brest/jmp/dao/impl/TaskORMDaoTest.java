package com.epam.brest.jmp.dao.impl;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;
import static java.time.temporal.ChronoUnit.DAYS;

import com.epam.brest.jmp.model.Task;
import com.epam.brest.jmp.test.config.TestDaoConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * For test purposes are used singleton-scoped beans for {@link MapSqlParameterSource}, {@link KeyHolder} and {@link NamedParameterJdbcOperations}
 * Created by alexander_borohov on 31.3.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDaoConfig.class)
public class TaskORMDaoTest {

    private Task testTaskFirst;
    private Task testTaskSecond;
    private Task testTaskThird;
    private Task testTaskFourth;
    private static Collection<Task> tasksInitialList;
    private final Map<String, Object> params = new HashMap<>();
    private final Integer TEST_USER_ID = 1;
    private final String TEST_TASK_NAME_FIRST = "test1 name";
    private final String TEST_TASK_NAME_SECOND = "test2 name";
    private final String TEST_TASK_NAME_THIRD = "test3 name";
    private final String TEST_TASK_NAME_FOURTH = "test4 name";
    private final String TEST_TASK_DESC_FIRST = "test task 1 description";
    private final String TEST_TASK_DESC_SECOND = "test task 2 description";
    private final String TEST_TASK_DESC_THIRD = "test task 3 description";
    private final String TEST_TASK_DESC_FOURTH = "test task 4 description";
    private final Date TEST_TASK_DATE_FIRST = Date.from(new Date().toInstant().plus(1, DAYS));
    private final Date TEST_TASK_DATE_SECOND = Date.from(new Date().toInstant().plus(2, DAYS));
    private final Date TEST_TASK_DATE_THIRD = Date.from(new Date().toInstant().plus(3, DAYS));
    private final Date TEST_TASK_DATE_FOURTH = Date.from(new Date().toInstant().plus(4, DAYS));

    @Autowired
    TaskOrmDao taskDao;
    @Autowired
    NamedParameterJdbcOperations mockJdbc;
    @Autowired
    MapSqlParameterSource testParamSource;
    @Autowired
    KeyHolder mockKeyHolder;

    @Before
    public void setUp() throws Exception {
        testTaskFirst = new Task(TEST_TASK_NAME_FIRST, TEST_TASK_DESC_FIRST, TEST_TASK_DATE_FIRST, TEST_USER_ID);
        testTaskSecond = new Task(TEST_TASK_NAME_SECOND, TEST_TASK_DESC_SECOND, TEST_TASK_DATE_SECOND, TEST_USER_ID);
        testTaskThird = new Task(TEST_TASK_NAME_THIRD, TEST_TASK_DESC_THIRD, TEST_TASK_DATE_THIRD, TEST_USER_ID);
        testTaskFourth = new Task(TEST_TASK_NAME_FOURTH, TEST_TASK_DESC_THIRD, TEST_TASK_DATE_FOURTH, TEST_USER_ID);
    }

    @After
    public void tearDown() throws Exception {
        verify(mockJdbc, mockKeyHolder);
        reset(mockJdbc, mockKeyHolder);
        params.clear();
    }

    @Test
    public void test() throws Exception {
        params.put("user_id", TEST_USER_ID);
        params.put("task_name", TEST_TASK_NAME_FIRST);
        params.put("task_desc", TEST_TASK_DESC_FIRST);
        params.put("task_creation_date", testTaskFirst.getCreationDate());
        params.put("task_deadline_date", TEST_TASK_DATE_FIRST);

        testParamSource.addValues(params);

        String testSql = "INSERT INTO tasks(task_name, task_creation_date, task_deadline_date, user_id, task_desc) VALUES (:task_name, :task_creation_date, :task_deadline_date, :user_id, :task_desc);";

        expect(mockJdbc.update(testSql, testParamSource, mockKeyHolder)).andReturn(1);
        expect(mockKeyHolder.getKey()).andReturn(2);
        replay(mockJdbc, mockKeyHolder);

        taskDao.create(testTaskFirst);


    }
}