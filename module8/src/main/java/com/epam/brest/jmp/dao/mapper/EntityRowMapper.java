package com.epam.brest.jmp.dao.mapper;

import static com.epam.brest.jmp.dao.mapper.ReflectUtils.setField;

import com.epam.brest.jmp.model.Entity;
import com.epam.brest.jmp.model.exceptions.DaoException;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.jdbc.core.RowMapper;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Used to map fetched results into classes of model package
 * Created by alexander_borohov on 3.4.17.
 */
public class EntityRowMapper<T extends Entity> implements RowMapper<T> {

    @Override
    public T mapRow(ResultSet rs, int rowNum) throws SQLException {
        T entity = null;
        try {
            entity = getEntityType().newInstance();

            List<Field> fields = FieldUtils.getFieldsListWithAnnotation(getEntityType(), com.epam.brest.jmp.dao.annotations.Field.class);

            for (Field field : fields) {
                com.epam.brest.jmp.dao.annotations.Field annotation
                        = field.getAnnotation(com.epam.brest.jmp.dao.annotations.Field.class);
                switch (annotation.dataType()) {
                    case TEXT:
                        setField(entity, field, rs.getString(annotation.field()));
                    break;
                    case NUMBER:
                        setField(entity, field, rs.getInt(annotation.field()));
                        break;
                    case DATE:
                        setField(entity, field, rs.getDate(annotation.field()));
                }
            }

        } catch (IllegalAccessException | InstantiationException e) {
            throw new DaoException(e.getMessage(), "Mapping an entity");
        } catch (NoSuchMethodException | InvocationTargetException e) {
            throw new DaoException(e.getMessage(), "Could not set field");
        }
        return entity;
    }


    @SuppressWarnings("unchecked")
    private Class<T> getEntityType() {
        return (Class<T>) ((ParameterizedTypeImpl) getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
    }
}
