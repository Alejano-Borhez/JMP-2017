package com.epam.brest.jmp.dao.impl;

import com.epam.brest.jmp.dao.DAO;
import com.epam.brest.jmp.dao.InMemoryDAO;
import com.epam.brest.jmp.model.User;

import java.util.Collection;
import java.util.List;

/**
 * Implementation of {@link DAO} using in-memory storage
 * Created by alexander_borohov on 17.3.17.
 */
public class UserInMemoryDao implements InMemoryDAO<User, Integer> {
    private Integer keySet = 0;
    private List<User> storage;

    public UserInMemoryDao(List<User> storage) {
        this.storage = storage;
    }

    @Override
    public Collection<User> getStorage() {
        return this.storage;
    }

    @Override
    public Integer getNewID() {
        return keySet++;
    }

}
