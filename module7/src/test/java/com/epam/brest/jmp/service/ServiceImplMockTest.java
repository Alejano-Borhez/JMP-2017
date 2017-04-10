package com.epam.brest.jmp.service;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.epam.brest.jmp.config.AppTestConfig;
import com.epam.brest.jmp.dao.DAO;
import com.epam.brest.jmp.dao.TaskDao;
import com.epam.brest.jmp.dao.UserDao;
import com.epam.brest.jmp.model.Task;
import com.epam.brest.jmp.model.User;
import com.epam.brest.jmp.model.exceptions.ServiceException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Unit tests with mocked {@link DAO}'s
 *
 * Created by alexander_borohov on 10.2.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppTestConfig.class)
@ActiveProfiles("ServiceTest")
@DirtiesContext
public class ServiceImplMockTest {
    @Autowired
    private TaskDao taskMockDao;
    @Autowired
    private UserDao userMockDao;

    @Autowired
    private ServiceFacade taskService;

    private User testUser;
    @Autowired
    private List<Task> tasks;
    private final Integer TEST_USER_ID = 1;
    private final Integer TEST_TASK_ID_FIRST = 1;
    private final Integer TEST_TASK_ID = 4;
    private final String TEST_TASK_DESC_FIRST = "test task 1 description";
    private final String TEST_USER_NAME = "test username";
    private final String TEST_USER_SURNAME = "test surname";
    private final String TEST_USER_EMAIL = "test email";

    @Before
    public void setUp() throws Exception {
        testUser = new User(TEST_USER_NAME, TEST_USER_SURNAME, TEST_USER_EMAIL);
    }

    @After
    public void tearDown() throws Exception {
        verify(taskMockDao, userMockDao);
        reset(taskMockDao, userMockDao);
    }

    @Test
    public void addNewTask() throws Exception {
        Task testTaskFirst = new Task(TEST_TASK_DESC_FIRST, TEST_USER_ID);
        expect(userMockDao.read(TEST_USER_ID)).andReturn(testUser);
        expect(taskMockDao.create(testTaskFirst)).andReturn(TEST_TASK_ID);
        replay(taskMockDao, userMockDao);
        Integer id = taskService.addNewTask(testTaskFirst);
        assertEquals("Ids are not equal", id, TEST_TASK_ID);
    }

    @Test
    public void showAllTasks() throws Exception {
        expect(taskMockDao.readAll()).andReturn(tasks);
        replay(taskMockDao, userMockDao);

        List<Task> testTasks = taskService.showAllTasks();
        assertEquals("Lists are not equal", tasks, testTasks);
    }

    @Test
    public void removeSpecificTask() throws Exception {
        expect(taskMockDao.delete(TEST_TASK_ID_FIRST)).andReturn(true);
        replay(taskMockDao, userMockDao);

        assertTrue("Not deleted!", taskService.removeSpecificTask(TEST_TASK_ID_FIRST));
    }

    @Test(expected = ServiceException.class)
    public void removeSpecificTaskFailMinusOne() throws Exception {
        expect(taskMockDao.delete(-1)).andThrow(new AssertionError("Dao was called")).anyTimes();
        replay(taskMockDao, userMockDao);
        taskService.removeSpecificTask(-1);
    }

    @Test(expected = ServiceException.class)
    public void removeSpecificTaskFailNull() throws Exception {
        expect(taskMockDao.delete(null)).andThrow(new AssertionError("Dao was called")).anyTimes();
        replay(taskMockDao, userMockDao);
        taskService.removeSpecificTask(null);
    }

    @Test
    public void removeAllTasks() throws Exception {
        expect(taskMockDao.deleteAll()).andReturn(true);
        replay(taskMockDao, userMockDao);

        assertTrue("All were not deleted", taskService.removeAllTasks());
    }

    @Test
    public void getUserTest() throws Exception {
        expect(userMockDao.read(TEST_USER_ID)).andReturn(testUser);
        replay(userMockDao, taskMockDao);

        assertEquals("Different users!", testUser, taskService.getUserById(TEST_USER_ID));
    }

    @Test
    public void getTaskOwnerTest() throws Exception {
        expect(taskMockDao.read(TEST_TASK_ID_FIRST)).andReturn(tasks.get(0));
        expect(userMockDao.read(tasks.get(0).getUserId())).andReturn(testUser);

        replay(taskMockDao, userMockDao);

        assertEquals("Different users!", testUser, taskService.getTaskOwner(TEST_USER_ID));

    }
}