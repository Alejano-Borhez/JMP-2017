package com.epam.brest.jmp.dao.mapper;

import com.epam.brest.jmp.dao.annotations.OrmField;
import com.epam.brest.jmp.model.exceptions.DaoException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Some useful reflection methods are gathered here
 * Created by alexander_borohov on 3.4.17.
 */
public class ReflectUtils {

    public static Object getField(Object entity, Field field) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method getter = MethodUtils.getAccessibleMethod(entity.getClass(), "get" + StringUtils.capitalize(field.getName()));
        return getter.invoke(entity);
    }

    public static void setField(Object entity, Field field, Object value) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method setter = MethodUtils.getAccessibleMethod(entity.getClass(), "set" + StringUtils.capitalize(field.getName()), field.getType());
        setter.invoke(entity, value);
    }

    public static Map<String, Object> getStringObjectMap(Object entity) {
        Map<String, Object> params = new HashMap<>();

        try {
            for (Field field : FieldUtils.getFieldsWithAnnotation(entity.getClass(), OrmField.class)) {
                OrmField annotation = field.getAnnotation(OrmField.class);
                switch (annotation.dataType()) {
                    case DATE:
                        Date date;
                        date = (Date) getField(entity, field);
                        params.put(annotation.field(), date);
                        break;
                    case NUMBER:
                        Integer number;
                        number = (Integer) getField(entity, field);
                        params.put(annotation.field(), number);
                        break;
                    case TEXT:
                        String text;
                        text = (String) getField(entity, field);
                        params.put(annotation.field(), text);
                        break;
                }
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new DaoException(entity, "Creation" + e.getMessage());
        }
        return params;
    }
}
