package com.epam.brest.jmp.service;

import com.epam.brest.jmp.dao.TaskDao;
import com.epam.brest.jmp.model.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Created by alexander_borohov on 9.2.17.
 */
public class TaskServiceImpl implements TaskService {
    private TaskDao taskDao;

    private static final Logger LOGGER = LogManager.getLogger(TaskServiceImpl.class);

    public TaskServiceImpl(TaskDao dao) {
        this.taskDao = dao;
    }

    @Override
    public Integer addNewTask(Task task) {
        LOGGER.debug("Adding new task: {}", task.getDescription());
        Integer id = null;
        if (!task.getDescription().isEmpty()) {
            id = taskDao.addTask(task);
        }
        return id;
    }

    @Override
    public List<Task> showAllTasks() {
        LOGGER.debug("Getting all tasks");
        return taskDao.getAllTasks();
    }

    @Override
    public Boolean removeSpecificTask(Integer id) {
        LOGGER.debug("Removing Task #{}", id);
        if (id != null && id > 0) {
            return taskDao.removeTask(id);
        } else {
            return false;
        }
    }

    @Override
    public Boolean removeAllTasks() {
        LOGGER.debug("Removing all tasks");
        return taskDao.removeAllTasks();
    }
}
