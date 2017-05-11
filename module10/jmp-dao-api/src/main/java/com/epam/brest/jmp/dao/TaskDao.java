package com.epam.brest.jmp.dao;

import com.epam.brest.jmp.model.Task;
import com.epam.brest.jmp.model.User;

import java.util.Collection;

/**
 * Simple DAO interface for {@link Task}
 * Created by alexander_borohov on 9.2.17.
 */
public interface TaskDao extends DAO<Task, Integer> {
    /**
     *
     * @param userId - Id of a {@link User}
     * @return List of {@link Task} that belongs to a particular {@link User} from storage
     */
    Collection<Task> getAllTaskOfAUser(Integer userId);
}
