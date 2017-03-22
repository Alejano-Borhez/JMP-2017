package com.epam.brest.jmp.dao.impl;

import com.epam.brest.jmp.dao.InMemoryDAO;
import com.epam.brest.jmp.dao.TaskDao;
import com.epam.brest.jmp.model.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Implementation of {@link TaskDao} using in-memory storage
 * Created by alexander_borohov on 17.3.17.
 */
@Component
public class TaskInMemoryDao implements InMemoryDAO<Task, Integer>, TaskDao {
    private Integer keySet = 0;
    private List<Task> storage;

    public TaskInMemoryDao(List<Task> storage) {
        this.storage = storage;
    }

    @Override
    public Collection<Task> getStorage() {
        return this.storage;
    }

    @Override
    public Collection<Task> getAllTaskOfAUser(Integer userId) {
        List<Task> tasksOfAUser = new ArrayList<>();
        for (Task task : getStorage()) {
            if (task.getId().equals(userId)) tasksOfAUser.add(task);
        }
        return tasksOfAUser;
    }

    @Override
    public Integer getNewID() {
        return keySet++;
    }

}
