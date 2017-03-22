package com.epam.brest.jmp.config;

import com.epam.brest.jmp.dao.TaskDao;
import com.epam.brest.jmp.dao.UserDao;
import com.epam.brest.jmp.dao.impl.TaskInMemoryDao;
import com.epam.brest.jmp.dao.impl.UserInMemoryDao;
import com.epam.brest.jmp.model.Task;
import com.epam.brest.jmp.model.User;
import com.epam.brest.jmp.service.ServiceFacade;
import org.easymock.EasyMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Used for Java-based Spring configuration
 * Created by alexander_borohov on 22.3.17.
 */
@Configuration
@ComponentScan(basePackages = {"com.epam.brest.jmp.service", "com.epam.brest.jmp.controller"})
public class AppConfig {
    @Bean
    @Profile("Prod")
    public UserDao userDao() {
        List<User> users = new CopyOnWriteArrayList<>();
        return new UserInMemoryDao(users);
    }

    @Bean
    public boolean setup(@Autowired ServiceFacade service) {
        User owner = new User("TestName", "Surname", "email");

        Date date = Date.from(LocalDate.now().plusDays(2).atStartOfDay(ZoneId.systemDefault()).toInstant());

        owner.setId(service.addNewUser(owner));

        Task taskOne = new Task("Test1", "TestDesc1", date, owner.getId());
        Task taskTwo = new Task("Test2", "TestDesc2", date, owner.getId());
        Task taskThree = new Task("Test3", "TestDesc3", date, owner.getId());

        service.addNewTask(taskOne);
        service.addNewTask(taskTwo);
        service.addNewTask(taskThree);

        return true;
    }

    @Bean
    @Profile("Prod")
    public TaskDao taskDao() {
        List<Task> tasks = new CopyOnWriteArrayList<>();
        return new TaskInMemoryDao(tasks);
    }

    @Bean
    @Profile("Prod")
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    @Profile("ServiceTest")
    public UserDao userDaoMock() {
        return EasyMock.createMock(UserDao.class);
    }

    @Bean
    @Profile("ServiceTest")
    public TaskDao taskDaoMock() {
        return EasyMock.createMock(TaskDao.class);
    }

    @Bean
    @Profile("ServiceTest")
    public List<Task> tasks() {
        final Integer TEST_USER_ID = 1;
        final Integer TEST_TASK_ID_FIRST = 1;
        final Integer TEST_TASK_ID_SECOND = 2;
        final Integer TEST_TASK_ID_THIRD = 3;
        final Integer TEST_TASK_ID = 4;
        final String TEST_TASK_DESC_FIRST = "test task 1 description";
        final String TEST_TASK_DESC_SECOND = "test task 2 description";
        final String TEST_TASK_DESC_THIRD = "test task 3 description";
        final String TEST_USER_NAME = "test username";
        final String TEST_USER_SURNAME = "test surname";
        final String TEST_USER_EMAIL = "test email";

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
