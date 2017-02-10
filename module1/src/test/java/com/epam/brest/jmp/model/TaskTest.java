package com.epam.brest.jmp.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by alexander_borohov on 9.2.17.
 */
public class TaskTest {
    private Task testTask;
    private final Integer TEST_TASK_ID = 1;
    private final String TEST_TASK_DESC = "test task description";

    @Before
    public void setUp() throws Exception {
        testTask = new Task();
    }

    @Test
    public void getId() throws Exception {
        testTask.setId(TEST_TASK_ID);
        assertEquals("Ids are not equal", testTask.getId(), TEST_TASK_ID);
    }

    @Test
    public void getDescription() throws Exception {
        testTask.setDescription(TEST_TASK_DESC);
        assertEquals("Descriptions are not equal", testTask.getDescription(), TEST_TASK_DESC);
    }

    @Test
    public void baseConstructorTest() throws Exception {
        assertNull("Base constructor is not working: " + testTask.getId(), testTask.getId());
        assertNull("Base constructor is not working" + testTask.getDescription(), testTask.getDescription());
    }

    @Test
    public void customConstructorTest() throws Exception {
        testTask = new Task(TEST_TASK_DESC);
        assertNull("Base constructor is not working: " + testTask.getId(), testTask.getId());
        assertEquals("Descriptions are not equal", testTask.getDescription(), TEST_TASK_DESC);
    }

    @Test
    public void fullCustomConstructorTest() throws Exception {
        testTask = new Task(TEST_TASK_ID, TEST_TASK_DESC);
        assertEquals("Ids are not equal", testTask.getId(), TEST_TASK_ID);
        assertEquals("Descriptions are not equal", testTask.getDescription(), TEST_TASK_DESC);
    }
}