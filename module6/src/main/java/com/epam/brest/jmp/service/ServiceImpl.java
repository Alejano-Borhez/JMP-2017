package com.epam.brest.jmp.service;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.epam.brest.jmp.dao.DAO;
import com.epam.brest.jmp.dao.TaskDao;
import com.epam.brest.jmp.model.Task;
import com.epam.brest.jmp.model.User;
import com.epam.brest.jmp.model.exceptions.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Basic implementation with basic data validation
 * Created by alexander_borohov on 9.2.17.
 */
public class ServiceImpl implements ServiceFacade {
    private TaskDao taskDao;
    private DAO<User, Integer> userDao;

    private static final Logger LOGGER = LogManager.getLogger(ServiceImpl.class);

    public ServiceImpl(TaskDao dao, DAO<User, Integer> userDao) {
        this.taskDao = dao;
        this.userDao = userDao;
    }

    @Override
    public Integer addNewTask(Task task) {
        assertCorrectTask(task);
        LOGGER.debug("Adding new task: {}", task.getDescription());
        Integer id = null;
        if (!task.getDescription().isEmpty()) {
            id = taskDao.create(task);
        }
        return id;
    }

    @Override
    public List<Task> showAllTasks() {
        LOGGER.debug("Getting all tasks");
        List<Task> tasks = (List<Task>) taskDao.readAll();
        assertCorrectList(tasks);
        return tasks;
    }

    @Override
    public Boolean removeSpecificTask(Integer id) {
        assertCorrectId(id);
        LOGGER.debug("Removing Task #{}", id);
        if (id != null && id >= 0) {
            return taskDao.delete(id);
        } else {
            return false;
        }
    }

    @Override
    public Boolean removeAllTasks() {
        LOGGER.debug("Removing all tasks");
        return taskDao.deleteAll();
    }

    @Override
    public Task getTaskById(Integer id) {
        assertCorrectId(id);
        LOGGER.debug("Getting a task by id: {}", id);
        return taskDao.read(id);
    }

    @Override
    public List<Task> getAllTaskOfAUser(Integer userId) {
        assertCorrectId(userId);
        List<Task> tasks = (List<Task>) taskDao.getAllTaskOfAUser(userId);
        assertCorrectList(tasks);
        return tasks;
    }

    @Override
    public Integer addNewUser(User user) {
        assertCorrectUser(user);
        Integer id = userDao.create(user);
        assertCorrectId(id);
        return id;
    }

    @Override
    public List<User> showAllUsers() {
        List<User> users = (List<User>) userDao.readAll();
        assertCorrectList(users);
        return users;
    }

    @Override
    public Boolean removeSpecificUser(Integer id) {
        assertCorrectId(id);
        return userDao.delete(id);
    }

    @Override
    public Boolean removeAllUsers() {
        return userDao.deleteAll();
    }

    @Override
    public User getUserById(Integer id) {
        assertCorrectId(id);
        User user = userDao.read(id);
        assertCorrectUser(user);
        return user;
    }

    @Override
    public User getTaskOwner(Integer id) {
        assertCorrectId(id);
        Task task = taskDao.read(id);
        assertCorrectTask(task);
        Integer userId = task.getUserId();
        assertCorrectId(userId);
        User user = userDao.read(userId);
        assertCorrectUser(user);
        return user;
    }

    private void assertCorrectId(Integer id) {
        try {
            assertNotNull("Null id provided", id);
            assertTrue("Negative id provided", id >= 0);
        } catch (AssertionError error) {
            throw new ServiceException(id, error.getMessage());
        }
    }

    private void assertCorrectTask(Task task) {
        try {
            assertNotNull("Null task!", task);
            assertNotNull("Null description", task.getDescription());
            assertNotNull("Null name", task.getName());
            assertNotNull("Null userId", task.getUserId());
            assertNotNull("Null creationDate", task.getCreationDate());
            assertNotNull("Null deadLine", task.getDeadLine());
            assertNull("Not Null id", task.getId());
        } catch (AssertionError error) {
            throw new ServiceException(task, error.getMessage());
        }
    }

    private void assertCorrectUser(User user) {
        try {
            assertNotNull("Null user!", user);
            assertNull("Not null id", user.getId());
            assertNull("Null name", user.getName());
            assertNull("Null surname", user.getSurname());
            assertNull("Null email", user.getEmail());
        } catch (AssertionError error) {
            throw new ServiceException(user, error.getMessage());
        }
    }


    private void assertCorrectList(List list) {
        try {
            assertNotNull("Got null list!", list);
        } catch (AssertionError error) {
            throw new ServiceException(list, error.getMessage());
        }
    }
}
