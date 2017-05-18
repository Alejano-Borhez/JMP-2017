package com.epam.brest.jmp.web.config;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

import com.epam.brest.jmp.dao.mapper.EntityRowMapper;
import com.epam.brest.jmp.model.Task;
import com.epam.brest.jmp.model.User;
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
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Main components of application
 * Created by alexander_borohov on 14.4.17.
 */
@Configuration
@PropertySource({"classpath:db.properties", "classpath:hibernate.properties"})
@ComponentScan(basePackages = {"com.epam.brest.jmp.service", "com.epam.brest.jmp.dao"})
@EnableTransactionManagement
public class RootContextConfig {
    @Autowired
    private Environment env;

    @Bean
    @Profile("custom")
    EntityRowMapper<Task> taskEntityRowMapper() {
        return new EntityRowMapper<>(Task.class);
    }

    @Bean
    @Profile("custom")
    EntityRowMapper<User> userEntityRowMapper() {
        return new EntityRowMapper<>(User.class);
    }

    @Bean
    DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        return dataSource;
    }

    @Bean
    @Profile("custom")
    PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    @Profile("hibernate")
    EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean lcemf = new LocalContainerEntityManagerFactoryBean();
        lcemf.setPackagesToScan("com.epam.brest.jmp.model");
        lcemf.setDataSource(dataSource());
        lcemf.setJpaVendorAdapter(jpaVendorAdapter());
        lcemf.afterPropertiesSet();
        return lcemf.getObject();
    }

    private JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.HSQL);
        vendorAdapter.setDatabasePlatform(env.getProperty("hibernate.dialect"));
        vendorAdapter.setGenerateDdl(Boolean.parseBoolean(env.getProperty("hibernate.generated_ddl")));
        vendorAdapter.setShowSql(Boolean.parseBoolean(env.getProperty("hibernate.show_sql")));
        return vendorAdapter;
    }

    @Bean
    @Profile("hibernate")
    JpaTransactionManager transactionManagerHibernate() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(dataSource());
        transactionManager.setEntityManagerFactory(entityManagerFactory());
        return transactionManager;
    }

    @Bean
    @Profile("custom")
    NamedParameterJdbcOperations namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    @Scope(scopeName = SCOPE_PROTOTYPE)
    @Profile("custom")
    KeyHolder realKeyHolder() {
        return new GeneratedKeyHolder();
    }

    @Bean
    @Scope(scopeName = SCOPE_PROTOTYPE)
    @Profile("custom")
    MapSqlParameterSource realParameterSource() {
        return new MapSqlParameterSource();
    }

}
