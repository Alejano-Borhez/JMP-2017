package com.epam.brest.jmp.model;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;
import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Objects;

/**
 * Simple entity to represent a Task in a program
 * Created by alexander_borohov on 9.2.17.
 */
public class Task implements Entity<Integer> {
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    @JsonProperty(required = false, value = "id")
    private Integer id;
    @JsonProperty(required = true, value = "userId")
    private Integer userId;
    @JsonProperty(required = false, value = "name")
    private String name;
    @JsonProperty(required = true, value = "description")
    private String description;
    @JsonFormat(pattern = DATE_PATTERN, shape = STRING)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonProperty(required = false, value = "creationDate")
    private LocalDate creationDate;
    @JsonFormat(pattern = DATE_PATTERN, shape = STRING)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonProperty(required = false, value = "deadLine")
    private LocalDate deadLine;

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
    @JsonCreator(mode = PROPERTIES)
    public Task(@JsonProperty("name")
                        String name,
                @JsonProperty("description")
                        String description,
                @JsonProperty("deadLine")
                        LocalDate deadLine,
                @JsonProperty("userId")
                        Integer userId) {
        this.name = (name != null) ? name : description.split("\\s")[0];
        this.description = description;
        this.creationDate = LocalDate.now();
        this.deadLine = (deadLine != null) ? deadLine : LocalDate.from(creationDate).plusDays(1);
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
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
                ", creationDate=" + ((creationDate != null) ? creationDate.format(dateTimeFormatter) : "") +
                ", deadLine=" + ((deadLine != null) ? deadLine.format(dateTimeFormatter) : "") +
                '}';
    }
}
