package com.epam.brest.jmp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.epam.brest.jmp.config.ServiceIntegrationTestConfig;
import com.epam.brest.jmp.model.Task;
import com.epam.brest.jmp.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Used real MYSQL connection
 * Created by alexander_borohov on 12.4.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServiceIntegrationTestConfig.class})
@ActiveProfiles({"INTEGRATION", "custom"})
@Transactional
public class ServiceIntegrationTest {
    private static final Integer TEST_USER_ID = 1;
    private static final Integer TEST_TASK_ID_FIRST = 2;
    private static final Integer TEST_TASK_ID = 4;
    private static final String TEST_TASK_DESC_FIRST = "test task 1 description";
    private static final String TEST_USER_NAME = "test username";
    private static final String TEST_USER_SURNAME = "test surname";
    private static final String TEST_USER_EMAIL = "test email";

    @Autowired
    private ServiceFacade service;


    @Test
    public void readUserFromDataBaseTest() throws Exception {
        User testUser = service.getUserById(TEST_USER_ID);
        assertEquals("Got wrong user", TEST_USER_ID, testUser.getId());
    }

    @Test
    public void createNewUserTest() throws Exception {
        User testUser = new User(TEST_USER_NAME, TEST_USER_SURNAME, TEST_USER_EMAIL);
        Integer newId = service.addNewUser(testUser);
        assertNotNull("Did not create a user", newId);
        assertTrue("Wrong id", newId > 1);
    }

    @Test
    public void readTaskFromDatabaseTest() throws Exception {
        Task testTask = service.getTaskById(TEST_TASK_ID_FIRST);
        assertEquals("Got wrong user", TEST_TASK_ID_FIRST, testTask.getId());
    }

    @Test
    public void createNewTaskTest() throws Exception {
        Task testTask = new Task(TEST_TASK_DESC_FIRST, TEST_USER_ID);

        Integer id = service.addNewTask(testTask);
        assertNotNull(id);

        Task createdTask = service.getTaskById(id);
        assertNotNull(createdTask);
        testTask.setId(id);

        assertEquals(testTask, createdTask);
    }

    @Test
    public void deleteTaskTest() throws Exception {

    }
}
