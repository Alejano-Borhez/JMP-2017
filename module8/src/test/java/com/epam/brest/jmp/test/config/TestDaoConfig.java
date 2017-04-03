package com.epam.brest.jmp.test.config;

import com.epam.brest.jmp.dao.mapper.EntityRowMapper;
import com.epam.brest.jmp.model.Task;
import com.epam.brest.jmp.model.User;
import org.easymock.EasyMock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 * Test confic that uses singleton scoped beans
 * Created by alexander_borohov on 31.3.17.
 */
@Configuration
@ComponentScan(basePackages = {"com.epam.brest.jmp.dao"})
public class TestDaoConfig {

    @Bean
    NamedParameterJdbcOperations namedParameterJdbcOperations() {
        return EasyMock.createMock(NamedParameterJdbcTemplate.class);
    }

    @Bean
    MapSqlParameterSource mapSqlParameterSource() {
        return new MapSqlParameterSource();
    }

    @Bean
    KeyHolder keyHolder() {
        return EasyMock.createMock(GeneratedKeyHolder.class);
    }

    @Bean
    EntityRowMapper<Task> taskEntityRowMapper() {
        return new EntityRowMapper<>();
    }

    @Bean
    EntityRowMapper<User> userEntityRowMapper() {
        return new EntityRowMapper<>();
    }


}
