package com.epam.brest.jmp.mongo.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.PrePersist;
import org.mongodb.morphia.annotations.Property;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Simple file entity
 * Created by alexander_borohov on 25.6.17.
 */
@Entity("files")
@Indexes(
        @Index(fields = @Field("fileName")
                , unique = true)
)
public class File {
    private String fileName;
    @Property("creation_time")
    private LocalDateTime creationDateTime;
    @Id
    private ObjectId id;

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    @Property("times_downloaded")
    private Integer times = 0;

    public File() {
    }

    public File(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    @PrePersist
    public void setCreationDateTime() {
        if (this.creationDateTime == null) {
            this.creationDateTime = LocalDateTime.now();
        }
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getId() {
        return id.toString();
    }

    public void setId(String id) {
        this.id = new ObjectId(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return Objects.equals(getFileName(), file.getFileName()) &&
                Objects.equals(getCreationDateTime(), file.getCreationDateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFileName(), getCreationDateTime());
    }

    @Override
    public String toString() {
        return "File{" +
                "fileName='" + fileName + '\'' +
                ", creationDateTime=" + creationDateTime +
                ", downloaded=" + times +
                '}';
    }
}
