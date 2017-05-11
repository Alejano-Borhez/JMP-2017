package com.epam.brest.jmp.dao.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A model class should be annotated by this annotation
 * Created by alexander_borohov on 30.3.17.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface OrmTable {
    /**
     * Represents a name of a table in a database
     * @return name
     */
    String value();
}
