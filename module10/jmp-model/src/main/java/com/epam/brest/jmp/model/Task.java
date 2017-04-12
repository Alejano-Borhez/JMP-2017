package com.epam.brest.jmp.model;

import static com.epam.brest.jmp.dao.annotations.Field.DataType.DATE;
import static com.epam.brest.jmp.dao.annotations.Field.DataType.NUMBER;
import static com.epam.brest.jmp.dao.annotations.Field.DataType.TEXT;
import static java.time.temporal.ChronoUnit.DAYS;

import com.epam.brest.jmp.dao.annotations.Field;
import com.epam.brest.jmp.dao.annotations.Id;
import com.epam.brest.jmp.dao.annotations.Table;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.Objects;

/**
 * Simple entity to represent a Task in a program
 * Created by alexander_borohov on 9.2.17.
 */
@Table("tasks")
public class Task implements Entity<Integer> {
    public static final String DATE_PATTERN = "yyyy-MM-dd";

    @Id("task_id")
    private Integer id;
    @Field(field = "user_id", dataType = NUMBER)
    private Integer userId;
    @Field(field = "task_name", dataType = TEXT)
    private String name;
    @Field(field = "task_desc", dataType = TEXT)
    private String description;
    @Field(field = "task_creation_date", dataType = DATE)
    private Date creationDate;
    @Field(field = "task_deadline_date", dataType = DATE)
    private Date deadLine;

    public Task() {
    }

    /**
     * For test use only!
     *
     * @param description - Description of a {@link Task}
     * @param userId      - {@link User#id} of a task owner {@link User}
     */
    public Task(String description, Integer userId) {
        this(null, description, null, userId);
    }

    /**
     * Complex constructor that excplicitly sets all parameters of a Task.
     * Also  ensures that any {@link Task} must be given an {@link User#id} of a owner.
     *
     * @param description - Description of a {@link Task}
     * @param userId      - {@link User#id} of a task owner {@link User}
     * @param deadLine    -
     */
    public Task(String name,
                String description,
                Date deadLine,
                Integer userId) {
        this.name = (name != null) ? name : description.split("\\s")[0];
        this.description = description;
        this.creationDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.deadLine = (deadLine != null) ? deadLine : Date.from(creationDate.toInstant().plus(1, DAYS));
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

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
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
        builder.appendPattern(DATE_PATTERN);
        DateTimeFormatter dateTimeFormatter = builder.toFormatter();
        return "Task{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creationDate=" + ((creationDate != null) ? creationDate.toString() : "") +
                ", deadLine=" + ((deadLine != null) ? deadLine.toString() : "") +
                '}';
    }
}
