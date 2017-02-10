package com.epam.brest.jmp.service;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.epam.brest.jmp.dao.TaskDao;
import com.epam.brest.jmp.model.Task;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexander_borohov on 10.2.17.
 */
public class TaskServiceImplMockTest {
    private TaskDao mockDao = EasyMock.createMock(TaskDao.class);

    private TaskService taskService;
    private Task testTaskFirst;
    private Task testTaskSecond;
    private Task testTaskThird;
    private List<Task> tasks;
    private final Integer TEST_TASK_ID_FIRST = 1;
    private final Integer TEST_TASK_ID_SECOND = 2;
    private final Integer TEST_TASK_ID_THIRD = 3;
    private final Integer TEST_TASK_ID = 4;
    private final String TEST_TASK_DESC_FIRST = "test task 1 description";
    private final String TEST_TASK_DESC_SECOND = "test task 2 description";
    private final String TEST_TASK_DESC_THIRD = "test task 3 description";

    @Before
    public void setUp() throws Exception {
        taskService = new TaskServiceImpl(mockDao);
    }

    @After
    public void tearDown() throws Exception {
        verify(mockDao);
        reset(mockDao);
    }

    @Test
    public void addNewTask() throws Exception {
        testTaskFirst = new Task(TEST_TASK_DESC_FIRST);
        expect(mockDao.addTask(testTaskFirst)).andReturn(TEST_TASK_ID);
        replay(mockDao);
        Integer id = taskService.addNewTask(testTaskFirst);
        assertEquals("Ids are not equal", id, TEST_TASK_ID);
    }

    @Test
    public void addNewTaskFail() throws Exception {
        testTaskFirst = new Task("");
        expect(mockDao.addTask(testTaskFirst)).andThrow(new AssertionError("Dao was called")).anyTimes();
        replay(mockDao);
        Integer id = taskService.addNewTask(testTaskFirst);
        assertNull("Id was not null", id);
    }

    @Test
    public void showAllTasks() throws Exception {
        testTaskFirst = new Task(TEST_TASK_ID_FIRST, TEST_TASK_DESC_FIRST);
        testTaskSecond = new Task(TEST_TASK_ID_SECOND, TEST_TASK_DESC_SECOND);
        testTaskThird = new Task(TEST_TASK_ID_THIRD, TEST_TASK_DESC_THIRD);

        tasks = new ArrayList<Task>();
        tasks.add(testTaskFirst);
        tasks.add(testTaskSecond);
        tasks.add(testTaskThird);

        expect(mockDao.getAllTasks()).andReturn(tasks);
        replay(mockDao);

        List<Task> testTasks = taskService.showAllTasks();
        assertEquals("Lists are not equal", tasks, testTasks);
    }

    @Test
    public void removeSpecificTask() throws Exception {
        expect(mockDao.removeTask(TEST_TASK_ID_FIRST)).andReturn(true);
        replay(mockDao);

        assertTrue("Not deleted!", taskService.removeSpecificTask(TEST_TASK_ID_FIRST));
    }

    @Test
    public void removeSpecificTaskFailMinusOne() throws Exception {
        expect(mockDao.removeTask(-1)).andThrow(new AssertionError("Dao was called")).anyTimes();
        replay(mockDao);
        assertFalse("Not deleted!", taskService.removeSpecificTask(-1));
    }

    @Test
    public void removeSpecificTaskFailNull() throws Exception {
        expect(mockDao.removeTask(null)).andThrow(new AssertionError("Dao was called")).anyTimes();
        replay(mockDao);
        assertFalse("Not deleted!", taskService.removeSpecificTask(null));
    }

    @Test
    public void removeSpecificTaskFailZero() throws Exception {
        expect(mockDao.removeTask(0)).andThrow(new AssertionError("Dao was called")).anyTimes();
        replay(mockDao);
        assertFalse("Not deleted!", taskService.removeSpecificTask(0));
    }

    @Test
    public void removeAllTasks() throws Exception {
        expect(mockDao.removeAllTasks()).andReturn(true);
        replay(mockDao);

        assertTrue("All were not deleted", taskService.removeAllTasks());
    }

}