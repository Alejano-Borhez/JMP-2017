package com.epam.brest.jmp.service;

import com.epam.brest.jmp.model.Task;
import com.epam.brest.jmp.model.User;
import com.epam.brest.jmp.model.exceptions.ServiceException;

/**
 * Facade to interact with DAO
 * Created by alexander_borohov on 19.3.17.
 */
public interface ServiceFacade extends UserService, TaskService {
    /**
     * Method returns distinct task of a user by given user's and task's id
     * @param userId - ID of an existing {@link User}
     * @param taskId - ID of an existing {@link Task} that is owned by a {@link User}
     * @return Task if all parameters match
     * @throws ServiceException - in either cases: user is not found, task is not found, task is not owned by a user
     */
    Task getUsersTask(Integer userId, Integer taskId);
    /**
     * Method returns a {@link User} owner of particular {@link Task}
     * @param id - {@link User#id} of a owner
     * @return owner of a Task
     */
    User getTaskOwner(Integer id);
}
