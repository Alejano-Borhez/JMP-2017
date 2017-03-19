package com.epam.brest.jmp.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Objects;

/**
 * Simple entity to represent a Task in a program
 * Created by alexander_borohov on 9.2.17.
 */
public class Task implements Entity<Integer> {
    private Integer id;
    private Integer userId;
    private String name;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime deadLine;

    /**
     * Simple constructor that created a {@link #name} from {@link #description} (using first word),
     * sets {@link #creationDate} as current {@link LocalDateTime}
     * sets default {@link #deadLine} as 1 day after {@link #creationDate}
     * Also  ensures that any {@link Task} must be given an {@link User#id} of a owner.
     *
     * @param description - Description of a {@link Task}
     * @param userId - {@link User#id} of a task owner {@link User}
     */
    public Task(String description, Integer userId) {
        this.name = description.split("\\s")[0];
        this.description = description;
        this.creationDate = LocalDateTime.now();
        this.deadLine = LocalDateTime.from(creationDate).plusDays(1);
        this.userId = userId;
    }

    /**
     * Complex constructor that excplicitly sets all parameters of a Task.
     * Also  ensures that any {@link Task} must be given an {@link User#id} of a owner.
     *
     * @param description - Description of a {@link Task}
     * @param userId - {@link User#id} of a task owner {@link User}
     *               @param deadLine -
     */
    public Task(String name, String description, LocalDateTime deadLine, Integer userId) {
        this.name = name;
        this.description = description;
        this.deadLine = deadLine;
        this.creationDate = LocalDateTime.now();
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDateTime deadLine) {
        this.deadLine = deadLine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(getId(), task.getId()) &&
                Objects.equals(getUserId(), task.getUserId()) &&
                Objects.equals(getName(), task.getName()) &&
                Objects.equals(getDescription(), task.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getName(), getDescription());
    }

    @Override
    public String toString() {
        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
        builder.appendPattern("YYYY-MM-DD");
        DateTimeFormatter dateTimeFormatter = builder.toFormatter();
        return "Task{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate.format(dateTimeFormatter) +
                ", deadLine=" + deadLine.format(dateTimeFormatter) +
                '}';
    }
}
