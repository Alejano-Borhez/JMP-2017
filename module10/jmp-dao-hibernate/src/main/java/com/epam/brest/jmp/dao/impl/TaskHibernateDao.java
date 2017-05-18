package com.epam.brest.jmp.dao.impl;

import com.epam.brest.jmp.dao.HibernateDao;
import com.epam.brest.jmp.dao.TaskDao;
import com.epam.brest.jmp.model.Task;
import com.epam.brest.jmp.model.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Basic DAO implementation with Hibernate ORM under the hood
 * Created by alexander_borohov on 11.5.17.
 */
@Repository
@Profile("hibernate")
public class TaskHibernateDao implements HibernateDao<Task, Integer>, TaskDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Collection<Task> getAllTaskOfAUser(Integer userId) {
        entityManager.flush();
        return entityManager.find(User.class, userId).getUserTasks();
    }

    @Override
    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    public Class<Task> entityType() {
        return Task.class;
    }

    @Override
    public Integer create(Task entity) {
        User user = getEntityManager().find(User.class, entity.getUser().getId());
        List<Task> tasks = user.getUserTasks();
        tasks.add(entity);
        getEntityManager().persist(user);
        getEntityManager().persist(entity);
        getEntityManager().flush();
        return entity.getId();
    }
}
