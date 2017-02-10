package com.epam.brest.jmp.dao;

import com.epam.brest.jmp.model.Task;

import java.util.List;

/**
 * Created by alexander_borohov on 9.2.17.
 */
public interface TaskDao {
    /**
     * @param id - validated by {@link com.epam.brest.jmp.service.TaskService} ID of a {@link Task}
     * @return {@link Task} if found in a repository or {@code null} otherwise
     */
    Task getTask(Integer id);

    /**
     * @return Full list of {@link Task} if any persisted in a repository or empty List otherwise
     */
    List<Task> getAllTasks();

    /**
     * Persists new {@link Task} in a repository
     *
     * @param task - fully validated by {@link com.epam.brest.jmp.service.TaskService} {@link Task}
     * @return ID of a created {@link Task} if success or or {@code null} otherwise
     */
    Integer addTask(Task task);

    /**
     * Deletes a {@link Task} if it is already persisted in a repository
     *
     * @param id - validated by {@link com.epam.brest.jmp.service.TaskService} ID of a {@link Task}
     * @return {@code true} if was removed and {@code false} otherwise
     */
    Boolean removeTask(Integer id);

    /**
     * Deletes all {@link Task} persisted in a repository
     * @return {@code true} if were removed and {@code false} otherwise
     */
    Boolean removeAllTasks();
}
