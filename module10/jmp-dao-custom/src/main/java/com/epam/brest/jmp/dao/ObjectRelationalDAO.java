package com.epam.brest.jmp.dao;

import static java.lang.String.format;

import com.epam.brest.jmp.dao.annotations.OrmField;
import com.epam.brest.jmp.dao.annotations.OrmId;
import com.epam.brest.jmp.dao.annotations.OrmTable;
import com.epam.brest.jmp.dao.mapper.EntityRowMapper;
import com.epam.brest.jmp.dao.mapper.ReflectUtils;
import com.epam.brest.jmp.model.Entity;
import com.epam.brest.jmp.model.exceptions.DaoException;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.KeyHolder;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Intermediate and partial {@link DAO} implementation
 * Created by alexander_borohov on 17.3.17.
 */
public interface ObjectRelationalDAO<T extends Entity<ID>, ID> extends DAO<T, ID> {
    NamedParameterJdbcOperations getStorage();
    EntityRowMapper<T> getEntityRowMapper();
    MapSqlParameterSource getParameterSource();

    KeyHolder getKeyHolder();

    @Override
    default ID create(T entity) {
        Map<String, Object> params = ReflectUtils.getStringObjectMap(entity);

        if (params.isEmpty()) throw new DaoException(entity, "Could not read fields");

        MapSqlParameterSource parameterSource = getParameterSource();

        parameterSource.addValues(params);

        String tableName = entity.getClass().getAnnotation(OrmTable.class).value();

        String fieldNames = "";
        String values = "";
        for (String name : params.keySet()) {
            fieldNames = fieldNames.concat(name + ", ");
            values = values.concat(":" + name + ", ");
        }

        fieldNames = fieldNames.replaceAll(",\\s$", "");
        values = values.replaceAll(",\\s$", "");

        String query = format("INSERT INTO %s(%s) VALUES (%s);", tableName, fieldNames, values);

        int rowNumsAffected = getStorage().update(query, parameterSource, getKeyHolder());

        if (rowNumsAffected == 1) return getNewID();

        throw new DaoException(entity, "JDBC returned unexpected result: " + rowNumsAffected);
    }

    @SuppressWarnings("unchecked")
    default Class<T> getEntityType() {
        return (Class<T>) ((ParameterizedTypeImpl) getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
    }

    @Override
    default T read(ID id) {
        String tableName = getEntityType().getAnnotation(OrmTable.class).value();
        String entityIdFieldName = FieldUtils.getFieldsWithAnnotation(getEntityType(), OrmId.class)[0].getAnnotation(OrmId.class).value();
        String entityIdQueryLabel = ":".concat(entityIdFieldName);
        String selectQuery = format("SELECT * FROM %s WHERE %s = %s", tableName, entityIdFieldName, entityIdQueryLabel);

        Map<String, Object> params = new HashMap<>();
        params.put(entityIdFieldName, id);

        MapSqlParameterSource parameterSource = getParameterSource();
        parameterSource.addValues(params);

        try {
         return getStorage().queryForObject(selectQuery, parameterSource, getEntityRowMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new DaoException(id, "not found by ID");
        }
    }

    @Override
    default T update(T entity) {
        java.lang.reflect.Field idField = FieldUtils.getFieldsWithAnnotation(entity.getClass(), OrmId.class)[0];
        String entityIdFieldName = idField.getAnnotation(OrmId.class).value();
        String entityIdQueryLabel = ":".concat(entityIdFieldName);

        String tableName = entity.getClass().getAnnotation(OrmTable.class).value();
        Map<String, Object> params = ReflectUtils.getStringObjectMap(entity);
        try {
            params.put(entityIdFieldName, ReflectUtils.getField(entity, idField));
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            throw new DaoException(idField, "Id field is not present in a model");
        }
        MapSqlParameterSource parameterSource = getParameterSource();
        parameterSource.addValues(params);

        StringBuilder updateStringBuilder = new StringBuilder();

        for (java.lang.reflect.Field field: FieldUtils.getFieldsWithAnnotation(entity.getClass(), OrmField.class)) {
            String columnName = field.getAnnotation(OrmField.class).field();
            updateStringBuilder.append(columnName).append(" = :").append(columnName).append(", ");
        }

        String updateString = updateStringBuilder.toString().replaceAll(",\\s$", "");

        String updateQuery = format("UPDATE %s SET %s WHERE %s = %s", tableName, updateString, entityIdFieldName, entityIdQueryLabel);

        int rowsAffected = getStorage().update(updateQuery, parameterSource);

        if (rowsAffected == 1) return entity;

        throw new DaoException(entity, "Update failed. Numver of roew affected: " + rowsAffected);
    }

    @Override
    default Boolean delete(ID id) {
        String tableName = getEntityType().getAnnotation(OrmTable.class).value();
        String entityIdFieldName = FieldUtils.getFieldsWithAnnotation(getEntityType(), OrmId.class)[0].getAnnotation(OrmId.class).value();
        String entityIdQueryLabel = ":".concat(entityIdFieldName);
        String deleteQuery = format("DELETE FROM %s WHERE %s = %s", tableName, entityIdFieldName, entityIdQueryLabel);

        Map<String, Object> params = new HashMap<>();
        params.put(entityIdFieldName, id);

        MapSqlParameterSource parameterSource = getParameterSource();
        parameterSource.addValues(params);

        int rowsAffected = getStorage().update(deleteQuery, parameterSource);

        return rowsAffected == 1;
    }

    @Override
    default Collection<T> readAll() {
        String tableName = getEntityType().getAnnotation(OrmTable.class).value();

        String getAllQuery = format("SELECT * FROM %s", tableName);

        return getStorage().query(getAllQuery, getParameterSource(), getEntityRowMapper());
    }

    @Override
    default Boolean deleteAll() {

        return true;
    }
}
