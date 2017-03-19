package com.epam.brest.jmp.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.epam.brest.jmp.dao.impl.TaskInMemoryDao;
import com.epam.brest.jmp.model.Task;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by alexander_borohov on 9.2.17.
 */
public class TaskDaoTest {
    private static TaskDao taskDao;
    private Task testTaskFirst;
    private Task testTaskSecond;
    private Task testTaskThird;
    private static Collection<Task> tasksInitialList;
    private final Integer TEST_USER_ID = 1;
    private final String TEST_TASK_DESC_FIRST = "test task 1 description";
    private final String TEST_TASK_DESC_SECOND = "test task 2 description";
    private final String TEST_TASK_DESC_THIRD = "test task 3 description";
    private final String TEST_TASK_DESC_FOURTH = "test task 4 description";

    @BeforeClass
    public static void setUpGlobal() throws Exception {
        taskDao = new TaskInMemoryDao(new ArrayList<>());
        System.setProperty("env", "test");
        tasksInitialList = taskDao.readAll();
    }

    @Before
    public void setUp() throws Exception {
        testTaskFirst = new Task(TEST_TASK_DESC_FIRST, TEST_USER_ID);
        testTaskSecond = new Task(TEST_TASK_DESC_SECOND, TEST_USER_ID);
        testTaskThird = new Task(TEST_TASK_DESC_THIRD, TEST_USER_ID);

        taskDao.deleteAll();

        testTaskFirst.setId(taskDao.create(testTaskFirst));
        testTaskSecond.setId(taskDao.create(testTaskSecond));
        testTaskThird.setId(taskDao.create(testTaskThird));

    }

    @AfterClass
    public static void tearDownGlobal() throws Exception {
        taskDao.deleteAll();
        for (Task task : tasksInitialList) {
            taskDao.create(task);
        }
    }

    @Test
    public void getTask() throws Exception {
        Task testTask = taskDao.read(testTaskFirst.getId());
        assertEquals("Tasks are not equal", testTask, testTaskFirst);
    }


    @Test
    public void getAllTasks() throws Exception {
        List<Task> testTasks = (List<Task>) taskDao.readAll();
        assertFalse("Returned empty list", testTasks.isEmpty());
        assertEquals("1st task is not on board", testTasks.get(0), testTaskFirst);
        assertEquals("1st task is not on board", testTasks.get(1), testTaskSecond);
        assertEquals("1st task is not on board", testTasks.get(2), testTaskThird);
    }

    @Test
    public void addTask() throws Exception {
        Task task = new Task(TEST_TASK_DESC_FOURTH, TEST_USER_ID);
        Integer addedId = taskDao.create(task);
        assertEquals("Ids are not equal", addedId, new Integer(3));
    }

    @Test
    public void removeTask() throws Exception {
        List<Task> listBefore = (List<Task>) taskDao.readAll();
        assertTrue("Task was not deleted!", taskDao.delete(testTaskFirst.getId()));
        List<Task> listAfter = (List<Task>) taskDao.readAll();
        assertTrue("Lists are equal!", listBefore.size() - listAfter.size() == 1);
    }

    @Test
    public void removeAllTasks() throws Exception {
        assertTrue("Tasks were not deleted!", taskDao.deleteAll());
        List<Task> listAfter = (List<Task>) taskDao.readAll();
        assertTrue("List was not empty", listAfter.isEmpty());
    }

}