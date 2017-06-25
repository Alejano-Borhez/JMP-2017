package com.epam.brest.jmp.mongo.dao;

import com.epam.brest.jmp.mongo.model.User;

import java.util.List;

/**
 * Dao interface for users
 * Created by alexander_borohov on 25.6.17.
 */
public interface IUsersDao {
    String createUser(User user);
    User readUserById(String id);
    User readUserByName(String name);
    List<User> readAllUsers();
    User updateUser(User newUser);
    Boolean deleteUser(String id);
    User getFileOwner(String fileId);
}
