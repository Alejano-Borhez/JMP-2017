package com.epam.brest.jmp.service;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.epam.brest.jmp.dao.DAO;
import com.epam.brest.jmp.dao.TaskDao;
import com.epam.brest.jmp.model.Task;
import com.epam.brest.jmp.model.exceptions.ServiceException;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexander_borohov on 10.2.17.
 */
public class ServiceImplMockTest {
    private TaskDao mockDao = EasyMock.createMock(TaskDao.class);
    private DAO userMockDao = EasyMock.createMock(DAO.class);

    private ServiceFacade taskService;
    private Task testTaskFirst;
    private Task testTaskSecond;
    private Task testTaskThird;
    private List<Task> tasks;
    private final Integer TEST_USER_ID = 1;
    private final Integer TEST_TASK_ID_FIRST = 1;
    private final Integer TEST_TASK_ID_SECOND = 2;
    private final Integer TEST_TASK_ID_THIRD = 3;
    private final Integer TEST_TASK_ID = 4;
    private final String TEST_TASK_DESC_FIRST = "test task 1 description";
    private final String TEST_TASK_DESC_SECOND = "test task 2 description";
    private final String TEST_TASK_DESC_THIRD = "test task 3 description";

    @Before
    public void setUp() throws Exception {
        taskService = new ServiceImpl(mockDao, userMockDao);
    }

    @After
    public void tearDown() throws Exception {
        verify(mockDao);
        reset(mockDao);
    }

    @Test
    public void addNewTask() throws Exception {
        testTaskFirst = new Task(TEST_TASK_DESC_FIRST, TEST_USER_ID);
        expect(mockDao.create(testTaskFirst)).andReturn(TEST_TASK_ID);
        replay(mockDao);
        Integer id = taskService.addNewTask(testTaskFirst);
        assertEquals("Ids are not equal", id, TEST_TASK_ID);
    }

    @Test(expected = ServiceException.class)
    public void addNewTaskFail() throws Exception {
        testTaskFirst = new Task(TEST_TASK_DESC_FIRST, TEST_USER_ID);
        testTaskFirst.setId(TEST_TASK_ID);
        expect(mockDao.create(testTaskFirst)).andThrow(new AssertionError("Dao was called")).anyTimes();
        replay(mockDao);
        taskService.addNewTask(testTaskFirst);
    }

    @Test
    public void showAllTasks() throws Exception {
        testTaskFirst = new Task(TEST_TASK_DESC_FIRST, TEST_USER_ID);
        testTaskSecond = new Task(TEST_TASK_DESC_SECOND, TEST_USER_ID);
        testTaskThird = new Task(TEST_TASK_DESC_THIRD, TEST_USER_ID);
        testTaskFirst.setId(TEST_TASK_ID_FIRST);
        testTaskSecond.setId(TEST_TASK_ID_SECOND);
        testTaskThird.setId(TEST_TASK_ID_THIRD);

        tasks = new ArrayList<Task>();
        tasks.add(testTaskFirst);
        tasks.add(testTaskSecond);
        tasks.add(testTaskThird);

        expect(mockDao.readAll()).andReturn(tasks);
        replay(mockDao);

        List<Task> testTasks = taskService.showAllTasks();
        assertEquals("Lists are not equal", tasks, testTasks);
    }

    @Test
    public void removeSpecificTask() throws Exception {
        expect(mockDao.delete(TEST_TASK_ID_FIRST)).andReturn(true);
        replay(mockDao);

        assertTrue("Not deleted!", taskService.removeSpecificTask(TEST_TASK_ID_FIRST));
    }

    @Test(expected = ServiceException.class)
    public void removeSpecificTaskFailMinusOne() throws Exception {
        expect(mockDao.delete(-1)).andThrow(new AssertionError("Dao was called")).anyTimes();
        replay(mockDao);
        taskService.removeSpecificTask(-1);
    }

    @Test(expected = ServiceException.class)
    public void removeSpecificTaskFailNull() throws Exception {
        expect(mockDao.delete(null)).andThrow(new AssertionError("Dao was called")).anyTimes();
        replay(mockDao);
        taskService.removeSpecificTask(null);
    }

    @Test
    public void removeAllTasks() throws Exception {
        expect(mockDao.deleteAll()).andReturn(true);
        replay(mockDao);

        assertTrue("All were not deleted", taskService.removeAllTasks());
    }

}