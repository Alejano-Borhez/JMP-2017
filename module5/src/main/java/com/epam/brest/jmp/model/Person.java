package com.epam.brest.jmp.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

/**
 * Simple entity to read and write
 * Created by alexander_borohov on 10.3.17.
 */
public class Person implements Serializable {

    @Length(min = 3, max = 36, message = "Incorrect name size. Should be from 3 to 36")
    private String name;
    @Range(min = 1, max = 100, message = "Age is out of range. Should be from 1 to 100")
    private Integer age;

    /**
     * {@link Sex}
     */
    private Sex sex;

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

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
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

    public enum Sex {
        MALE,
        FEMALE
    }
}
