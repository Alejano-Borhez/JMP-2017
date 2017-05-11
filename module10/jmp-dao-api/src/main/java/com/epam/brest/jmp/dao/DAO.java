package com.epam.brest.jmp.dao;

import com.epam.brest.jmp.model.Entity;

import java.util.Collection;

/**
 * Generic interface for common entities CRUD operations
 * Created by alexander_borohov on 17.3.17.
 */
public interface DAO<T extends Entity, ID> {
    /**
     * Generic method to persist an entity
     *
     * @param entity to be persisted
     * @return id of persisted entity
     */
    ID create(T entity);

    /**
     * Generic method to find entity by id
     *
     * @param id - {@link ID} parameter that represents an id of an entity
     * @return T entity
     */
    T read(ID id);

    /**
     * @param entity to update
     * @return entity that was updated
     */
    T update(T entity);

    /**
     * Generic method to remove an entity
     *
     * @param id of entity to be removed
     * @return true if removed, false otherwise
     */
    Boolean delete(ID id);

    /**
     * Generic method to find all entities
     *
     * @return a collection of entities
     */
    Collection<T> readAll();

    /**
     * Generic method to remove all entities
     *
     * @return true if removed, false otherwise
     */
    Boolean deleteAll();

    /**
     * Used for incrementing id value
     *
     * @return new value of id
     */
    ID getNewID();


}
