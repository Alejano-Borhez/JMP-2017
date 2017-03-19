package com.epam.brest.jmp.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * Created by alexander_borohov on 9.2.17.
 */
public class TaskTest {
    private Task testTask;
    private final Integer TEST_TASK_ID = 1;
    private final String TEST_TASK_DESC = "test task description";
    private final String TEST_TASK_NAME = "test task name";
    private final LocalDateTime TEST_DEADLINE = LocalDateTime.now().plusDays(2);

    @Before
    public void setUp() throws Exception {
        testTask = new Task(TEST_TASK_DESC, TEST_TASK_ID);
    }

    @Test
    public void getId() throws Exception {
        testTask.setId(TEST_TASK_ID);
        assertEquals("Ids are not equal", testTask.getId(), TEST_TASK_ID);
    }

    @Test
    public void getName() throws Exception {
        testTask.setName(TEST_TASK_NAME);
        assertEquals("Names are not equal", testTask.getName(), TEST_TASK_NAME);
    }

    @Test
    public void getDescription() throws Exception {
        testTask.setDescription(TEST_TASK_DESC);
        assertEquals("Descriptions are not equal", testTask.getDescription(), TEST_TASK_DESC);
    }

    @Test
    public void baseConstructorTest() throws Exception {
        assertNull("Base constructor is not working: " + testTask.getId(), testTask.getId());
        assertNotNull("Base constructor is not working" + testTask.getDescription(), testTask.getDescription());
        assertNotNull("Base constructor is not working" + testTask.getCreationDate(), testTask.getCreationDate());
        assertNotNull("Base constructor is not working" + testTask.getDeadLine(), testTask.getDeadLine());
        assertNotNull("Base constructor is not working" + testTask.getUserId(), testTask.getUserId());
        assertEquals("Wrong default deadline", testTask.getCreationDate().plusDays(1), testTask.getDeadLine());
        assertEquals("Wrong description in base constructor", testTask.getDescription(), TEST_TASK_DESC);
        assertEquals("Wrong default name", testTask.getName(), TEST_TASK_DESC.split("\\s")[0]);
        assertEquals("", testTask.getUserId(), TEST_TASK_ID);
    }

    @Test
    public void customConstructorTest() throws Exception {
        testTask = new Task(TEST_TASK_NAME, TEST_TASK_DESC, TEST_DEADLINE, 2);
        assertNull("Base constructor is not working: " + testTask.getId(), testTask.getId());
        assertEquals("Descriptions are not equal", testTask.getDescription(), TEST_TASK_DESC);
        assertEquals("Names are not equal", testTask.getName(), TEST_TASK_NAME);
        assertEquals("Deadlines are not equal", testTask.getDeadLine(), TEST_DEADLINE);
        assertNotNull("Created date not set", testTask.getCreationDate());
    }
}