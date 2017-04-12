package com.epam.brest.jmp.config;

import com.epam.brest.jmp.dao.TaskDao;
import com.epam.brest.jmp.dao.UserDao;
import com.epam.brest.jmp.model.Task;
import org.easymock.EasyMock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Used for Java-based Spring configuration
 * Created by alexander_borohov on 22.3.17.
 */
@Configuration
@ComponentScan(basePackages = {"com.epam.brest.jmp.service"})
@Profile("MOCK")
public class AppTestConfig {
    @Bean
    public UserDao userDaoMock() {
        return EasyMock.createMock(UserDao.class);
    }

    @Bean
    public TaskDao taskDaoMock() {
        return EasyMock.createMock(TaskDao.class);
    }

    @Bean
    public List<Task> tasks() {
        final Integer TEST_USER_ID = 1;
        final Integer TEST_TASK_ID_FIRST = 1;
        final Integer TEST_TASK_ID_SECOND = 2;
        final Integer TEST_TASK_ID_THIRD = 3;
        final String TEST_TASK_DESC_FIRST = "test task 1 description";
        final String TEST_TASK_DESC_SECOND = "test task 2 description";
        final String TEST_TASK_DESC_THIRD = "test task 3 description";

        Task testTaskFirst = new Task(TEST_TASK_DESC_FIRST, TEST_USER_ID);
        Task testTaskSecond = new Task(TEST_TASK_DESC_SECOND, TEST_USER_ID);
        Task testTaskThird = new Task(TEST_TASK_DESC_THIRD, TEST_USER_ID);
        testTaskFirst.setId(TEST_TASK_ID_FIRST);
        testTaskSecond.setId(TEST_TASK_ID_SECOND);
        testTaskThird.setId(TEST_TASK_ID_THIRD);

        List<Task> tasks = new ArrayList<>();
        tasks.add(testTaskFirst);
        tasks.add(testTaskSecond);
        tasks.add(testTaskThird);

        return tasks;
    }
}
