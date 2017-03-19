package com.epam.brest.jmp.dao;

import com.epam.brest.jmp.model.Entity;
import com.epam.brest.jmp.model.exceptions.DaoException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Intermediate and partial {@link DAO} implementation
 * Created by alexander_borohov on 17.3.17.
 */
public interface InMemoryDAO<T extends Entity<ID>, ID> extends DAO<T, ID> {

    Collection<T> getStorage();

    @Override
    default ID create(T entity) {
        if (getStorage().contains(entity)) throw new DaoException(entity, "already exists.");
        ID id = getNewID();
        entity.setId(id);
        getStorage().add(entity);
        return id;
    }

    @Override
    default T read(ID id) {
        for (T entity : getStorage()) {
            if (entity.getId().equals(id)) return entity;
        }
        throw new DaoException(id, "not found by ID");
    }

    @Override
    default T update(T entity) {
        for (T e : getStorage()) {
            if (e.getId().equals(entity.getId())) {
                getStorage().remove(e);
                getStorage().add(entity);
                return entity;
            }
        }
        throw new DaoException(entity, "update");
    }

    @Override
    default Boolean delete(ID id) {
        for (T e : getStorage()) {
            if (e.getId().equals(id)) {
                getStorage().remove(e);
                return true;
            }
        }
        throw new DaoException(id, "delete");
    }

    @Override
    default Collection<T> readAll() {
        List<T> entities = new ArrayList<>();
        for (T e: getStorage()) {
            entities.add(e);
        }
        return entities;
    }

    @Override
    default Boolean deleteAll() {
        getStorage().clear();
        return true;
    }
}
