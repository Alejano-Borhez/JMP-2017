package com.epam.brest.jmp.model;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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
public class Task implements Entity<Integer> {
    public static final String DATE_PATTERN = "yyyy-MM-dd";

    @JsonProperty(required = false, value = "id")
    private Integer id;
    @JsonProperty(required = true, value = "userId")
    private Integer userId;
    @JsonProperty(required = false, value = "name")
    private String name;
    @JsonProperty(required = true, value = "description")
    private String description;
//    @JsonFormat(pattern = DATE_PATTERN, shape = STRING)
//    @JsonSerialize(using = LocalDateSerializer.class)
//    @JsonDeserialize(using = LocalDateDeserializer.class)
//    @JsonProperty(required = false, value = "creationDate")
//    @XmlElement(required = false, type = String.class, name = "creationDate")

    private Date creationDate;
    //    @XmlElement(required = true, type = String.class, name = "deadLine")
//    @JsonFormat(pattern = DATE_PATTERN, shape = STRING)
//    @JsonSerialize(using = LocalDateSerializer.class)
//    @JsonDeserialize(using = LocalDateDeserializer.class)
//    @JsonProperty(required = false, value = "deadLine")
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
    @JsonCreator(mode = PROPERTIES)
    public Task(@JsonProperty("name")
                        String name,
                @JsonProperty("description")
                        String description,
                @JsonProperty("deadLine")
                        Date deadLine,
                @JsonProperty("userId")
                        Integer userId) {
        this.name = (name != null) ? name : description.split("\\s")[0];
        this.description = description;
        this.creationDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.deadLine = (deadLine != null) ? deadLine : (Date.from(LocalDate.from(creationDate.toInstant()).plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
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
