package com.epam.brest.jmp.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.epam.brest.jmp.dao.TaskDao;
import com.epam.brest.jmp.dao.UserDao;
import com.epam.brest.jmp.model.Task;
import com.epam.brest.jmp.model.User;
import com.epam.brest.jmp.model.exceptions.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Basic implementation with basic data validation
 * Created by alexander_borohov on 9.2.17.
 */
@Service
public class ServiceImpl implements ServiceFacade {
    private TaskDao taskDao;
    private UserDao userDao;

    private static final Logger LOGGER = LogManager.getLogger(ServiceImpl.class);

    @Autowired
    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Integer addNewTask(Task task) {
        assertCorrectTask(task);
        User user = userDao.read(task.getUserId());
        assertCorrectUser(user);
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
    public Task updateTask(Task task) {
        assertCorrectTask(task);
        Task updated = taskDao.update(task);
        assertCorrectTask(updated);
        return updated;
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

    @Override
    public User updateUser(User user) {
        assertCorrectUser(user);
        User updated = userDao.read(user.getId());
        assertCorrectUser(updated);
        updated = userDao.update(user);
        assertCorrectUser(updated);
        return updated;
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
//            assertNotNull("Null creationDate", task.getCreationDate());
//            assertNotNull("Null deadLine", task.getDeadLine());
        } catch (AssertionError error) {
            throw new ServiceException(task, error.getMessage());
        }
    }

    private void assertCorrectUser(User user) {
        try {
            assertNotNull("Null user!", user);
            assertNotNull("Null name", user.getName());
            assertNotNull("Null surname", user.getSurname());
            assertNotNull("Null email", user.getEmail());
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
