package com.epam.brest.jmp.dao.impl;

import static java.util.stream.Collectors.toCollection;

import com.epam.brest.jmp.dao.ObjectRelationalDAO;
import com.epam.brest.jmp.dao.TaskDao;
import com.epam.brest.jmp.dao.mapper.EntityRowMapper;
import com.epam.brest.jmp.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Implementation of {@link TaskDao} using in-memory storage
 * Created by alexander_borohov on 17.3.17.
 */
@Component
@Profile("custom")
public class TaskOrmDao implements ObjectRelationalDAO<Task, Integer>, TaskDao {

    @Autowired
    private NamedParameterJdbcOperations jbdc;
    @Autowired
    private MapSqlParameterSource parameterSource;
    @Autowired
    private KeyHolder holder;
    @Autowired
    private EntityRowMapper<Task> taskEntityRowMapper;

    @Override
    public Collection<Task> getAllTaskOfAUser(Integer userId) {
        return readAll().stream().filter(task -> Objects.equals(task.getUserId(), userId)).collect(toCollection(ArrayList::new));
    }

    @Override
    public NamedParameterJdbcOperations getStorage() {
        return this.jbdc;
    }

    @Override
    public EntityRowMapper<Task> getEntityRowMapper() {
        return this.taskEntityRowMapper;
    }

    @Override
    public KeyHolder getKeyHolder() {
        return this.holder;
    }

    @Override
    public MapSqlParameterSource getParameterSource() {
        return this.parameterSource;
    }

    @Override
    public Integer getNewID() {
        return this.holder.getKey().intValue();
    }
}
