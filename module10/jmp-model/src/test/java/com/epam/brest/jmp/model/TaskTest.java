package com.epam.brest.jmp.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by alexander_borohov on 9.2.17.
 */
public class TaskTest {
    private Task testTask;
    private final Integer TEST_TASK_ID = 1;
    private final String TEST_TASK_DESC = "test task description";
    private final String TEST_TASK_NAME = "test task name";
    private final Date TEST_DEADLINE = Date.from(LocalDate.now().plusDays(2).atStartOfDay(ZoneId.systemDefault()).toInstant());

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
//        assertEquals("Wrong default deadline", testTask.getCreationDate().plusDays(1), testTask.getDeadLine());
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

    @Test
    public void equalsTest() throws Exception {
        Task task = new Task();
        Task task1 = task;

        assertTrue(task.equals(task1));

        Task task2 = new Task();

        assertTrue(task1.equals(task2));
        assertTrue(task.equals(task2));

        Task test3 = new Task(TEST_TASK_NAME, TEST_TASK_DESC, TEST_DEADLINE, 2);
        Task test4 = new Task(TEST_TASK_NAME, TEST_TASK_DESC, TEST_DEADLINE, 2);

        assertTrue(test3.equals(test4));

        assertEquals(test3.hashCode(), test4.hashCode());

        test3 = null;

        assertFalse(test4.equals(test3));
        assertFalse(test4.equals(new User()));

        test4 = null;

        assertEquals(test3, test4);
    }

    @Test
    public void toStringTest() throws Exception {
        Task testTask1 = new Task(TEST_TASK_NAME, TEST_TASK_DESC, TEST_DEADLINE, 2);

        assertEquals("Task{" +
                "id=" + testTask1.getId() +
                ", userId=" + testTask1.getUserId() +
                ", name='" + testTask1.getName() + '\'' +
                ", description='" + testTask1.getDescription() + '\'' +
                ", creationDate=" + ((testTask1.getCreationDate() != null) ? testTask1.getCreationDate().toString() : "") +
                ", deadLine=" + ((testTask1.getDeadLine() != null) ? testTask1.getDeadLine().toString() : "") +
                '}', testTask1.toString());
    }

    @Test
    public void toStringTestNullDates() throws Exception {
        Task testTask1 = new Task(TEST_TASK_NAME, TEST_TASK_DESC, TEST_DEADLINE, 2);

        testTask1.setCreationDate(null);
        testTask1.setDeadLine(null);

        assertEquals("Task{" +
                "id=" + testTask1.getId() +
                ", userId=" + testTask1.getUserId() +
                ", name='" + testTask1.getName() + '\'' +
                ", description='" + testTask1.getDescription() + '\'' +
                ", creationDate=" + ((testTask1.getCreationDate() != null) ? testTask1.getCreationDate().toString() : "") +
                ", deadLine=" + ((testTask1.getDeadLine() != null) ? testTask1.getDeadLine().toString() : "") +
                '}', testTask1.toString());
    }

    @Test
    public void getUserIdTest() throws Exception {
        testTask.setUserId(TEST_TASK_ID);
        assertEquals(TEST_TASK_ID, testTask.getUserId());
    }

    @Test
    public void getUserTest() throws Exception {
        User user = new User();
        testTask.setUser(user);
        assertEquals(user, testTask.getUser());
    }
}