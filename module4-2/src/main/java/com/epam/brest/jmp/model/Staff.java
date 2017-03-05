package com.epam.brest.jmp.model;

/**
 * Simple implementation of {@link Person}
 * Created by alexander_borohov on 6.3.17.
 */
public class Staff implements Person {
    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "name='" + name + '\'' +
                '}';
    }
}
