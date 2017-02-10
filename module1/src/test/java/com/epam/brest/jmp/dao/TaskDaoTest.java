package com.epam.brest.jmp.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.epam.brest.jmp.model.Task;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexander_borohov on 9.2.17.
 */
public class TaskDaoTest {
    private static TaskDao taskDao;
    private Task testTaskFirst;
    private Task testTaskSecond;
    private Task testTaskThird;
    private static List<Task> tasksInitialList;
    private List<Task> tasks;
    private final Integer TEST_TASK_ID_FIRST = 1;
    private final Integer TEST_TASK_ID_SECOND = 2;
    private final Integer TEST_TASK_ID_THIRD = 3;
    private final String TEST_TASK_DESC_FIRST = "test task 1 description";
    private final String TEST_TASK_DESC_SECOND = "test task 2 description";
    private final String TEST_TASK_DESC_THIRD = "test task 3 description";
    private final String TEST_TASK_DESC_FOURTH = "test task 4 description";

    @BeforeClass
    public static void setUpGlobal() throws Exception {
        System.setProperty("env", "test");
        taskDao = new TaskDaoCSVImpl();
        tasksInitialList = taskDao.getAllTasks();
    }

    @Before
    public void setUp() throws Exception {
        testTaskFirst = new Task(TEST_TASK_ID_FIRST, TEST_TASK_DESC_FIRST);
        testTaskSecond = new Task(TEST_TASK_ID_SECOND, TEST_TASK_DESC_SECOND);
        testTaskThird = new Task(TEST_TASK_ID_THIRD, TEST_TASK_DESC_THIRD);

        tasks = new ArrayList<>();
        tasks.add(testTaskFirst);
        tasks.add(testTaskSecond);
        tasks.add(testTaskThird);

        taskDao.removeAllTasks();
        for (Task task : tasks) {
            taskDao.addTask(task);
        }
    }

    @AfterClass
    public static void tearDownGlobal() throws Exception {
        taskDao.removeAllTasks();
        for (Task task : tasksInitialList) {
            taskDao.addTask(task);
        }
    }

    @Test
    public void getTask() throws Exception {
        Task testTask = taskDao.getTask(testTaskFirst.getId());
        assertEquals("Tasks are not equal", testTask, testTaskFirst);
    }


    @Test
    public void getAllTasks() throws Exception {
        List<Task> testTasks = taskDao.getAllTasks();
        assertFalse("Returned empty list", testTasks.isEmpty());
        assertEquals("1st task is not on board", testTasks.get(0), testTaskFirst);
        assertEquals("1st task is not on board", testTasks.get(1), testTaskSecond);
        assertEquals("1st task is not on board", testTasks.get(2), testTaskThird);
    }

    @Test
    public void addTask() throws Exception {
        Task task = new Task(TEST_TASK_DESC_FOURTH);
        Integer addedId = taskDao.addTask(task);
        assertEquals("Ids are not equal", addedId, new Integer(4));
    }

    @Test
    public void removeTask() throws Exception {
        List<Task> listBefore = taskDao.getAllTasks();
        assertTrue("Task was not deleted!", taskDao.removeTask(testTaskFirst.getId()));
        List<Task> listAfter = taskDao.getAllTasks();
        assertTrue("Lists are equal!", listBefore.size() - listAfter.size() == 1);
    }

    @Test
    public void removeAllTasks() throws Exception {
        assertTrue("Tasks were not deleted!", taskDao.removeAllTasks());
        List<Task> listAfter = taskDao.getAllTasks();
        assertTrue("List was not empty", listAfter.isEmpty());
    }

}