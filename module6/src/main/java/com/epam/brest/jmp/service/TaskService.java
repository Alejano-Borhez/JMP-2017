package com.epam.brest.jmp.service;

import com.epam.brest.jmp.dao.TaskDao;
import com.epam.brest.jmp.model.Task;
import com.epam.brest.jmp.model.User;
import com.epam.brest.jmp.model.exceptions.ServiceException;

import java.util.List;

/**
 * Created by alexander_borohov on 9.2.17.
 */
public interface TaskService {
    /**
     * Method that validates Task and then passes it to {@link TaskDao}
     *
     * @param task - should be provided a raw Task object.
     * @return ID of created {@link Task} task returns or null otherwise
     */
    Integer addNewTask(Task task);

    /**
     * Method calls {@link TaskDao} for getting all tasks
     *
     * @return List of all {@link Task} tasks that are persisted
     */
    List<Task> showAllTasks();

    /**
     * Method validates ID to be {@code notNull} and > 0. After that tries to delete a {@link Task}
     * through {@link TaskDao}
     *
     * @param id - should be a valid ID of deleted {@link Task} Task
     * @return {@code true} if was removed and {@code false} otherwise
     */
    Boolean removeSpecificTask(Integer id);

    /**
     * Methods removes all {@link Task} through {@link TaskDao}
     * @return {@code true} if were removed and {@code false} otherwise
     */
    Boolean removeAllTasks();

    /**
     * Method returns specific {@link Task} by its Id
     * @param id - {@link Task#id} of a {@link Task}
     * @return Task with id or {@link ServiceException} is thrown
     */
    Task getTaskById(Integer id);

    /**
     * Method returns List of {@link Task} of particular {@link User}
     * @param userId - {@link User#id} of a {@link User}
     * @return List of User's tasks
     */
    List<Task> getAllTaskOfAUser(Integer userId);

    Task updateTask(Task task);
}
