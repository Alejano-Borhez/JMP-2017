package com.epam.brest.jmp.dao.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Table's fields should be annotated with it
 * Created by alexander_borohov on 30.3.17.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Field {
    /**
     * Name of column in a table
     * @return
     */
    String field();

    /**
     * Simple mapping to DB's datatype
     * @return
     */
    DataType dataType();

    /**
     * Simple representation of possible database types used for mapping
     * Created by alexander_borohov on 30.3.17.
     */
    enum DataType {
        TEXT, DATE, NUMBER
    }
}
