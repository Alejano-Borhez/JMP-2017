package com.epam.brest.jmp.model;

import static com.epam.brest.jmp.dao.annotations.OrmField.DataType.DATE;
import static com.epam.brest.jmp.dao.annotations.OrmField.DataType.NUMBER;
import static com.epam.brest.jmp.dao.annotations.OrmField.DataType.TEXT;
import static java.time.temporal.ChronoUnit.DAYS;

import com.epam.brest.jmp.dao.annotations.OrmField;
import com.epam.brest.jmp.dao.annotations.OrmId;
import com.epam.brest.jmp.dao.annotations.OrmTable;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Simple entity to represent a Task in a program
 * Created by alexander_borohov on 9.2.17.
 */
@OrmTable("tasks")
@javax.persistence.Entity
@Table(name = "tasks")
public class Task implements Entity<Integer> {

    @OrmId("task_id")
    @Min(0)
    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OrmField(field = "user_id", dataType = NUMBER)
    @Min(0)
    @Transient
    private Integer userId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OrmField(field = "task_name", dataType = TEXT)
    @NotNull
    @Size(min = 3, max = 256)
    @Column(name = "task_name")
    private String name;

    @OrmField(field = "task_desc", dataType = TEXT)
    @NotNull
    @Size(min = 3, max = 256)
    @Column(name = "task_desc")
    private String description;

    @OrmField(field = "task_creation_date", dataType = DATE)
    @Column(name = "task_creation_date")
    private Date creationDate;

    @OrmField(field = "task_deadline_date", dataType = DATE)
    @Column(name = "task_deadline_date")
    private Date deadLine;

    /**
     * Default no-args constructor for Hibernate usage
     */
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
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
