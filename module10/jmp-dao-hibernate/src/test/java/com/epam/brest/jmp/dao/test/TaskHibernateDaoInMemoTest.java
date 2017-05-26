package com.epam.brest.jmp.dao.test;

import static org.apache.logging.log4j.LogManager.getLogger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static java.lang.String.format;
import static java.time.temporal.ChronoUnit.DAYS;

import com.epam.brest.jmp.dao.impl.TaskHibernateDao;
import com.epam.brest.jmp.dao.impl.UserHibernateDao;
import com.epam.brest.jmp.dao.test.config.TestHibernateDaoConfig;
import com.epam.brest.jmp.model.Task;
import com.epam.brest.jmp.model.exceptions.DaoException;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;

/**
 * For test purposes are used singleton-scoped beans for {@link MapSqlParameterSource}, {@link KeyHolder} and {@link NamedParameterJdbcOperations}
 * Created by alexander_borohov on 31.3.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestHibernateDaoConfig.class)
@ActiveProfiles({"INMEMORY", "hibernate"})
@Transactional
public class TaskHibernateDaoInMemoTest {
    private static final Logger LOGGER = getLogger();

    private Task testTaskFirst;
    private Task testTaskFourth;
    private static final Integer TEST_USER_ID = 1;
    private static final Integer TEST_USER_NEW_ID = 2;
    private static final Integer TEST_TASK_ID = 1;
    private static final String TEST_TASK_NAME_FIRST = "test1 name";
    private static final String TEST_TASK_NAME_FOURTH = "test4 name";
    private static final String TEST_TASK_DESC_FIRST = "test task 1 description";
    private static final String TEST_TASK_DESC_THIRD = "test task 3 description";
    private static final Date TEST_TASK_DATE_FIRST = Date.from(new Date().toInstant().plus(1, DAYS));
    private static final Date TEST_TASK_DATE_FOURTH = Date.from(new Date().toInstant().plus(4, DAYS));

    @Autowired
    TaskHibernateDao taskDao;
    @Autowired
    UserHibernateDao userDao;

    @Before
    public void setUp() throws Exception {
        testTaskFirst = new Task(TEST_TASK_NAME_FIRST, TEST_TASK_DESC_FIRST, TEST_TASK_DATE_FIRST, TEST_USER_ID);
        testTaskFirst.setUser(userDao.read(TEST_USER_ID));
        testTaskFourth = new Task(TEST_TASK_NAME_FOURTH, TEST_TASK_DESC_THIRD, TEST_TASK_DATE_FOURTH, TEST_USER_NEW_ID);
        testTaskFourth.setUser(userDao.read(TEST_USER_NEW_ID));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void readTaskTest() throws Exception {
        Task testTask = taskDao.read(TEST_TASK_ID);
        assertEquals("Different IDs", TEST_TASK_ID, testTask.getId());
    }

    @Test
    public void createTaskTest() throws Exception {
        Integer id = taskDao.create(testTaskFirst);
        assertNotNull("Did not create!", id);
        LOGGER.info("Got created id: {}", id);
        testTaskFirst.setId(id);
        assertEquals("Wrong task creation!", testTaskFirst, taskDao.read(id));
    }

    @Test
    public void deleteTaskTest() throws Exception {
        Task testTask = taskDao.read(TEST_TASK_ID);
        assertNotNull(testTask);
        assertTrue(taskDao.delete(TEST_TASK_ID));
        try {
            testTask = taskDao.read(TEST_TASK_ID);
            assertNull(testTask);
        } catch (DaoException e) {
            assertEquals(format("Problem in DAO with: %d, while performing: not found by ID", TEST_TASK_ID),
                    e.getMessage());
        }
    }

    @Test (expected = UnsupportedOperationException.class)
    public void deleteAllTest() throws Exception {
        Collection<Task> allTasksBefore = taskDao.readAll();
        assertFalse(allTasksBefore.isEmpty());

        taskDao.deleteAll();
        Collection<Task> allTasksAfter = taskDao.readAll();
        assertTrue(allTasksAfter.isEmpty());
    }

    @Test
    public void updateTaskTest() throws Exception {
        Task testTask = taskDao.read(TEST_TASK_ID);
        assertNotNull(testTask);
        assertEquals("TEST_TASK2", testTask.getName());
        assertNotEquals(TEST_TASK_NAME_FIRST, testTask.getName());
        testTask.setName(TEST_TASK_NAME_FIRST);

        Task updatedTask = taskDao.update(testTask);
        assertEquals(TEST_TASK_NAME_FIRST, updatedTask.getName());

        Task reallyUpdatedTask = taskDao.read(testTask.getId());
        assertEquals(TEST_TASK_NAME_FIRST, reallyUpdatedTask.getName());
    }

    @Test
    public void readAllTasks() throws Exception {
        Collection<Task> tasks = taskDao.readAll();
        assertNotNull(tasks);
        LOGGER.info(tasks);
        assertEquals(4, tasks.size());
    }

    @Test
    public void readAllTasksOfAUserTest() throws Exception {
        Collection<Task> tasks = taskDao.getAllTaskOfAUser(TEST_USER_ID);
        assertNotNull(tasks);
        LOGGER.info(tasks);
        assertEquals(3, tasks.size());

        Collection<Task> otherTasks = taskDao.getAllTaskOfAUser(TEST_USER_NEW_ID);
        assertNotNull(otherTasks);
        assertEquals(1, otherTasks.size());

        assertNotNull(taskDao.create(testTaskFourth));

        Collection<Task> yetAnotherTasks = taskDao.getAllTaskOfAUser(TEST_USER_NEW_ID);
        assertNotNull(yetAnotherTasks);
        assertEquals(2, yetAnotherTasks.size());
        LOGGER.info(yetAnotherTasks);
    }
}