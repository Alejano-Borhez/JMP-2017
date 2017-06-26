package com.epam.brest.jmp.mongo.dao;

import static org.mongodb.morphia.query.Sort.descending;
import static java.time.temporal.ChronoUnit.DAYS;

import com.epam.brest.jmp.mongo.model.File;
import com.epam.brest.jmp.mongo.model.User;
import com.mongodb.MongoClient;
import com.mongodb.MongoCommandException;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * SImple mongo-driver-backed implementation
 * Created by alexander_borohov on 25.6.17.
 */
public class FileShareMongoDAO implements IFilesDao, IUsersDao, IAggregateDao {
    private static final long EXPIRES_AFTER_DAYS = 3;
    private Datastore datastore;

    public FileShareMongoDAO(MongoClient mongo) {
        final Morphia morphia = new Morphia();
        morphia.mapPackage("com.epam.brest.jmp.mongo.model");
        datastore = morphia.createDatastore(mongo, "file_share");
        datastore.ensureIndexes();
    }

    @Override
    public String createFile(File file) {
        try {
            return datastore.save(file).getId().toString();
        } catch (MongoCommandException e) {
            return null;
        }
    }

    @Override
    public File downloadFile(String id) {
        UpdateOperations<File> updateNumberDownloaded = datastore
                .createUpdateOperations(File.class)
                .inc("times_downloaded", 1);
        datastore.update(findOneFileByIdQuery(id), updateNumberDownloaded, false);
        return findOneFileByIdQuery(id).get();
    }

    @Override
    public File readFileByName(String fileName) {
        return datastore.createQuery(File.class).field("fileName").equalIgnoreCase(fileName).get();
    }

    @Override
    public List<File> readAllFiles() {
        return datastore.find(File.class).asList();
    }

    @Override
    public File updateFile(File newFile) {
        UpdateResults results = datastore.updateFirst(findOneFileByIdQuery(newFile.getId()), newFile, false);
        return (results.getWriteResult().getN() == 1) ? downloadFile(newFile.getId()) : null;
    }

    @Override
    public Boolean deleteFile(String id) {
        return datastore.delete(findOneFileByIdQuery(id)).getN() == 1;
    }

    @Override
    public List<File> readExpiredFiles() {
        return datastore.createQuery(File.class).field("creation_time")
                .lessThan(LocalDateTime.now().minus(EXPIRES_AFTER_DAYS, DAYS))
                .asList();
    }

    @Override
    public String createUser(User user) {
        try {
            return datastore.save(user).getId().toString();
        } catch (MongoCommandException e) {
            return null;
        }
    }

    @Override
    public User readUserById(String id) {
        return findOneUserByIdQuery(id).get();
    }

    @Override
    public User readUserByName(String name) {
        return datastore.createQuery(User.class).field("name").equalIgnoreCase(name).get();
    }

    @Override
    public List<User> readAllUsers() {
        return datastore.createQuery(User.class).asList();
    }

    @Override
    public User updateUser(User newUser) {
        UpdateResults results = datastore
                .updateFirst(findOneUserByIdQuery(newUser.getId()), newUser, false);
        return (results.getWriteResult().getN() == 1) ? readUserById(newUser.getId()) : null;
    }

    @Override
    public Boolean deleteUser(String id) {
        return datastore.delete(findOneUserByIdQuery(id)).getN() == 1;
    }

    @Override
    public Set<File> readAllFilesOfAUser(String userId) {
        User owner = readUserById(userId);
        return (owner != null) ? owner.getFiles() : Collections.emptySet();
    }

    @Override
    public List<File> readAllFilesByName(String fileName) {
        return datastore.createQuery(File.class).field("fileName").equal(fileName).asList();
    }

    @Override
    public List<File> listFilesDownloadTimesDescending() {
        return datastore.createQuery(File.class)
                .order(descending("times_downloaded"))
                .asList();
    }

    @Override
    public User getFileOwner(String fileId) {
        return datastore.createQuery(User.class)
                .field("files").hasThisOne(findOneFileByIdQuery(fileId).get()).get();

    }

    private Query<File> findOneFileByIdQuery(String id) {
        return datastore.createQuery(File.class).field("id").equal(new ObjectId(id));
    }

    private Query<User> findOneUserByIdQuery(String id) {
        return datastore.createQuery(User.class).field("id").equal(new ObjectId(id));
    }

    @Override
    public long countAllFiles() {
        return datastore.getCount(File.class);
    }

    @Override
    public long countActiveUsers() {
        return datastore.find(User.class).filter("files exists", true).count();
    }

    @Override
    public long countDownloadableFiles() {
        return datastore.createQuery(File.class).field("times_downloaded").greaterThan(0).count();
    }

    @Override
    public long countTotalDownloads() {
        return datastore.createQuery(File.class)
                .project("times_downloaded", true).asList()
                .stream().mapToLong(File::getTimes).sum();
    }
}
