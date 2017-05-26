package com.epam.brest.jmp.dao.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import com.epam.brest.jmp.model.Task;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

/**
 * Tests for custom ReflectUtils
 * Created by alexander_borohov on 25.5.17.
 */
@RunWith(JUnit4.class)
public class ReflectUtilsTest {
    private Task testTask;
    private final Integer TEST_TASK_ID = 1;
    private final String TEST_TASK_DESC = "test task description";
    private final String TEST_TASK_NAME = "test task name";
    private final Date TEST_DEADLINE = Date.from(LocalDate.now().plusDays(2).atStartOfDay(ZoneId.systemDefault()).toInstant());

    @Before
    public void setUp() throws Exception {
        testTask = new Task(TEST_TASK_DESC, TEST_TASK_ID);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getField() throws Exception {
        Field field = testTask.getClass().getDeclaredField("description");
        String taskDescription = (String) ReflectUtils.getField(testTask, field);

        assertEquals("Different results!", taskDescription, testTask.getDescription());
    }

    @Test
    public void setField() throws Exception {
        Field field = testTask.getClass().getDeclaredField("name");
        String newTaskName = "New Task name";
        assertFalse(newTaskName.equals(testTask.getName()));

        ReflectUtils.setField(testTask, field, newTaskName);

        assertEquals("Different results!", newTaskName, testTask.getName());
    }

    @Test
    public void getStringObjectMap() throws Exception {
        Map<String, Object> objectMap = ReflectUtils.getStringObjectMap(testTask);

        String taskName = (String) objectMap.get("task_name");

        assertEquals("Different results!", testTask.getName(), taskName);
    }

}