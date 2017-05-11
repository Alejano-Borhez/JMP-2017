package com.epam.brest.jmp.model;

import static com.epam.brest.jmp.dao.annotations.OrmField.DataType.TEXT;

import com.epam.brest.jmp.dao.annotations.OrmField;
import com.epam.brest.jmp.dao.annotations.OrmId;
import com.epam.brest.jmp.dao.annotations.OrmTable;
import org.hibernate.validator.constraints.Email;

import java.util.Objects;
import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Simple entity to represent a User of a system
 * Created by alexander_borohov on 17.3.17.
 */
@OrmTable("users")
public class User implements Entity<Integer> {

    @OrmId("user_id")
    @Min(0)
    @Column(name = "user_id")
    private Integer id;

    @OrmField(field = "user_name", dataType = TEXT)
    @NotNull
    @Size(min = 3, max = 256)
    @Column(name = "user_name")
    private String name;

    @OrmField(field = "user_surname", dataType = TEXT)
    @NotNull
    @Size(min = 3, max = 256)
    @Column(name = "user_surname")
    private String surname;

    @OrmField(field = "user_email", dataType = TEXT)
    @NotNull
    @Email(regexp = "[a-zA-Z0-9._-]+@[a-zA-Z]+\\.[a-zA-Z]{2,4}")
    @Column(name = "user_email")
    private String email;

    /**
     * Default no-args constructor for Hibernate usage
     */
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
