package com.epam.brest.jmp.dao.impl;

import com.epam.brest.jmp.dao.DAO;
import com.epam.brest.jmp.dao.ObjectRelationalDAO;
import com.epam.brest.jmp.dao.UserDao;
import com.epam.brest.jmp.dao.mapper.EntityRowMapper;
import com.epam.brest.jmp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link DAO} using in-memory storage
 * Created by alexander_borohov on 17.3.17.
 */
@Component
public class UserOrmDao implements ObjectRelationalDAO<User, Integer>, UserDao {
    @Autowired
    private NamedParameterJdbcOperations namedParameterJdbcOperations;
    @Autowired
    private KeyHolder keyHolder;
    @Autowired
    private MapSqlParameterSource parameterSource;
    @Autowired
    private EntityRowMapper<User> entityRowMapper;

    @Override
    public NamedParameterJdbcOperations getStorage() {
        return this.namedParameterJdbcOperations;
    }

    @Override
    public EntityRowMapper<User> getEntityRowMapper() {
        return this.entityRowMapper;
    }

    @Override
    public MapSqlParameterSource getParameterSource() {
        return this.parameterSource;
    }

    @Override
    public KeyHolder getKeyHolder() {
        return this.keyHolder;
    }

    @Override
    public Integer getNewID() {
        return this.keyHolder.getKey().intValue();
    }
}
