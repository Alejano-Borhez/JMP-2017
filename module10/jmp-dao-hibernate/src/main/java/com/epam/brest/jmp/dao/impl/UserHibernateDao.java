package com.epam.brest.jmp.dao.impl;

import com.epam.brest.jmp.dao.HibernateDao;
import com.epam.brest.jmp.dao.UserDao;
import com.epam.brest.jmp.model.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Basic DAO implementation with Hibernate ORM under the hood
 * Created by alexander_borohov on 16.5.17.
 */
@Repository
@Profile("hibernate")
public class UserHibernateDao implements HibernateDao<User, Integer>, UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    public Class<User> entityType() {
        return User.class;
    }
}
