package com.epam.brest.jmp.test.config;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

import com.epam.brest.jmp.dao.mapper.EntityRowMapper;
import com.epam.brest.jmp.model.Task;
import com.epam.brest.jmp.model.User;
import org.easymock.EasyMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Test confic that uses singleton scoped beans
 * Created by alexander_borohov on 31.3.17.
 */
@Configuration
@ComponentScan(basePackages = {"com.epam.brest.jmp.dao"})
@PropertySource("classpath:test-db.properties")
public class TestDaoConfig {
    @Autowired
    private Environment env;

    @Bean
    @Profile("MOCK")
    NamedParameterJdbcOperations namedParameterJdbcOperations() {
        return EasyMock.createMock(NamedParameterJdbcTemplate.class);
    }

    @Bean
    @Profile("MOCK")
    MapSqlParameterSource mapSqlParameterSource() {
        return new MapSqlParameterSource();
    }

    @Bean
    @Profile("MOCK")
    KeyHolder keyHolder() {
        return EasyMock.createMock(GeneratedKeyHolder.class);
    }

    @Bean
    @Profile({"INMEMORY", "MOCK"})
    EntityRowMapper<Task> taskEntityRowMapper() {
        return new EntityRowMapper<>(Task.class);
    }

    @Bean
    @Profile({"INMEMORY", "MOCK"})
    EntityRowMapper<User> userEntityRowMapper() {
        return new EntityRowMapper<>(User.class);
    }

    @Bean
    @Profile("INMEMORY")
    DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        return dataSource;
    }

    @Bean
    @Profile("INMEMORY")
    PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    @Profile("INMEMORY")
    NamedParameterJdbcOperations namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    @Scope(scopeName = SCOPE_PROTOTYPE)
    @Profile("INMEMORY")
    KeyHolder realKeyHolder() {
        return new GeneratedKeyHolder();
    }

    @Bean
    @Scope(scopeName = SCOPE_PROTOTYPE)
    @Profile("INMEMORY")
    MapSqlParameterSource realParameterSource() {
        return new MapSqlParameterSource();
    }


}
