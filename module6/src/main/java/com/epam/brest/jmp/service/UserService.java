package com.epam.brest.jmp.service;

import com.epam.brest.jmp.dao.DAO;
import com.epam.brest.jmp.model.Task;
import com.epam.brest.jmp.model.User;
import com.epam.brest.jmp.model.exceptions.ServiceException;

import java.util.List;

/**
 * Simple interface of a Service to work with Users
 * Created by alexander_borohov on 17.3.17.
 */
public interface UserService {
    /**
     * Method that validates {@link User} and then passes it to {@link DAO}
     *
     * @param user - should be provided a raw {@link User} object.
     * @return ID of created {@link User}
     */
    Integer addNewUser(User user);

    /**
     * Method calls {@link DAO} for getting all users
     *
     * @return List of all {@link User} tasks that are persisted
     */
    List<User> showAllUsers();

    /**
     * Method validates ID to be {@code notNull} and >= 0. After that tries to delete a {@link User}
     * through {@link DAO}
     *
     * @param id - should be a valid ID of deleted {@link User}
     * @return {@code true} if was removed and {@code false} otherwise
     */
    Boolean removeSpecificUser(Integer id);

    /**
     * Methods removes all {@link User} through {@link DAO}
     * @return {@code true} if were removed and {@code false} otherwise
     */
    Boolean removeAllUsers();

    /**
     * Method returns specific {@link User} by its Id
     * @param id - {@link User#id} of a {@link User}
     * @return Task with id or {@link ServiceException} is thrown
     */
    User getUserById(Integer id);

    /**
     * Method returns a {@link User} owner of particular {@link Task}
     * @param id - {@link User#id} of a owner
     * @return owner of a Task
     */
    User getTaskOwner(Integer id);
}
