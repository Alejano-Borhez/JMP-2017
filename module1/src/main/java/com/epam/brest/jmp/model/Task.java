package com.epam.brest.jmp.model;

import java.util.Objects;

/**
 * Created by alexander_borohov on 9.2.17.
 */
public class Task {
    private Integer id;
    private String description;

    public Task(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Task(String description) {
        this.description = description;
    }

    public Task() {
        this.id = null;
        this.description = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(getId(), task.getId()) &&
                Objects.equals(getDescription(), task.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription());
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
