package com.epam.brest.jmp.dao;

import com.epam.brest.jmp.model.Entity;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Basic generic default DAO implementation for hibernate persistence layer
 * Created by alexander_borohov on 18.5.17.
 */
public interface HibernateDao<T extends Entity<ID>, ID> extends DAO<T, ID> {
    EntityManager getEntityManager();
    Class<T> entityType();

    @Override
    default ID create(T entity) {
        getEntityManager().persist(entity);
        return entity.getId();
    }

    @Override
    default T read(ID id) {
        return getEntityManager().find(entityType(), id);
    }

    @Override
    default T update(T entity) {
        return getEntityManager().merge(entity);
    }

    @Override
    default Boolean delete(ID id) {
        try {
            getEntityManager().remove(read(id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    default Collection<T> readAll() {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(entityType());
        Root<T> rootEntry = query.from(entityType());
        CriteriaQuery<T> all = query.select(rootEntry);
        TypedQuery<T> allQuery = getEntityManager().createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    default Boolean deleteAll() {
        throw new UnsupportedOperationException("Cannot perform truncation of " + getClass().getName());
    }

    @Override
    default ID getNewID() {
        return null;
    }
}
