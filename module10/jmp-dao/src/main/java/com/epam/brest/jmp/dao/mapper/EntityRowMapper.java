package com.epam.brest.jmp.dao.mapper;

import static com.epam.brest.jmp.dao.mapper.ReflectUtils.setField;

import com.epam.brest.jmp.dao.annotations.OrmId;
import com.epam.brest.jmp.dao.annotations.OrmField;
import com.epam.brest.jmp.model.Entity;
import com.epam.brest.jmp.model.exceptions.DaoException;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.jdbc.core.RowMapper;

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
    private final Class<T> entityClass;

    public EntityRowMapper(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T mapRow(ResultSet rs, int rowNum) throws SQLException {
        T entity;
        try {
            entity = getEntityType().newInstance();

            List<Field> fields = FieldUtils.getFieldsListWithAnnotation(getEntityType(), OrmField.class);

            for (Field field : fields) {
                OrmField annotation
                        = field.getAnnotation(OrmField.class);
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

            Field idField = FieldUtils.getFieldsWithAnnotation(getEntityType(), OrmId.class)[0];

            setField(entity, idField, rs.getObject(idField.getAnnotation(OrmId.class).value()));

        } catch (IllegalAccessException | InstantiationException e) {
            throw new DaoException(e.getMessage(), "Mapping an entity");
        } catch (NoSuchMethodException | InvocationTargetException e) {
            throw new DaoException(e.getMessage(), "Could not set field");
        }
        return entity;
    }


    @SuppressWarnings("unchecked")
    private Class<T> getEntityType() {
        return this.entityClass;

    }
}
