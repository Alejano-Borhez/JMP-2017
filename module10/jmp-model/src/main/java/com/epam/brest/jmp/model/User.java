package com.epam.brest.jmp.model;

import static com.epam.brest.jmp.dao.annotations.Field.DataType.TEXT;

import com.epam.brest.jmp.dao.annotations.Field;
import com.epam.brest.jmp.dao.annotations.Id;
import com.epam.brest.jmp.dao.annotations.Table;

import java.util.Objects;

/**
 * Simple entity to represent a User of a system
 * Created by alexander_borohov on 17.3.17.
 */
@Table("users")
public class User implements Entity<Integer> {
    @Id("user_id")
    private Integer id;
    @Field(field = "user_name", dataType = TEXT)
    private String name;
    @Field(field = "user_surname", dataType = TEXT)
    private String surname;
    @Field(field = "user_email", dataType = TEXT)
    private String email;

    public User() {
    }

    public User(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getSurname(), user.getSurname()) &&
                Objects.equals(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSurname(), getEmail());
    }
}
