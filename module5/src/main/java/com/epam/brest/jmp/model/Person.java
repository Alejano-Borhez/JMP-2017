package com.epam.brest.jmp.model;

import java.io.Serializable;

/**
 * Simple entity to read and write
 * Created by alexander_borohov on 10.3.17.
 */
public class Person implements Serializable {
    private String name;
    private Integer age;
    /**
     * false - female
     * true - male
     */
    private Boolean sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
