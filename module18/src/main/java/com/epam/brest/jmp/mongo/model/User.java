package com.epam.brest.jmp.mongo.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Reference;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Simple User entity
 * Created by alexander_borohov on 25.6.17.
 */
@Entity("users")
public class User {
    @Indexed(unique = true)
    private String name;
    @Id
    private ObjectId id;
    @Reference
    private Set<File> files = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id.toString();
    }

    public void setId(String id) {
        this.id = new ObjectId(id);
    }

    public Set<File> getFiles() {
        return files;
    }

    public void setFiles(Set<File> files) {
        this.files = files;
    }

    public void addFiles(File... file) {
        Collections.addAll(this.files, file);
    }

    public void removeFiles(File... file) {
        for (File file1 : file) {
            this.files.remove(file1);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getName(), user.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public User() {
    }

    public User(String name) {
        this.name = name;
    }
}
