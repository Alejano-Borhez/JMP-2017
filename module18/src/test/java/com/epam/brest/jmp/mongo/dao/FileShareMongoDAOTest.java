package com.epam.brest.jmp.mongo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static java.time.temporal.ChronoUnit.DAYS;

import com.epam.brest.jmp.mongo.model.File;
import com.epam.brest.jmp.mongo.model.User;
import com.github.fakemongo.Fongo;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * Created by alexander_borohov on 25.6.17.
 */
public class FileShareMongoDAOTest {
    private final Fongo fongo = new Fongo("file_share");
    private final FileShareMongoDAO dao = new FileShareMongoDAO(fongo.getMongo());

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testCreateAndReadFile_success() throws Exception {
        File file = new File("Test");
        String newId = dao.createFile(file);
        assertNotNull("Got null ID!", newId);
        File newFile = dao.downloadFile(newId);
        assertNotNull("Not stored in DB", dao.downloadFile(newId));
        assertEquals("Not equal results!", file, newFile);
    }

    @Test
    public void testGetDownloadedTimesCount() throws Exception {
        Integer timesToDownload = 5;
        File file = new File("Test");
        String newId = dao.createFile(file);
        assertNotNull("Got null ID!", newId);
        File downloaded = new File();
        for (int i = 0; i < timesToDownload; i++) {
            downloaded = dao.downloadFile(newId);
        }
        assertEquals(timesToDownload, downloaded.getTimes());
    }

    @Test
    public void testPutThreeNewFilesAndReadAll_success() throws Exception {
        assertNotNull("Could not add new File!", dao.createFile(new File("Test1")));
        assertNotNull("Could not add new File!", dao.createFile(new File("Test2")));
        assertNotNull("Could not add new File!", dao.createFile(new File("Test3")));
        List<File> files = dao.readAllFiles();
        assertNotNull(files);
        assertTrue("Different collections sizes!", files.size() == 3);
    }

    @Test
    public void testPutNewFileAndThenDelete_success() throws Exception {
        String id = dao.createFile(new File("Test1"));
        assertNotNull("Could not add new File!", id);
        List<File> files = dao.readAllFiles();
        assertTrue("Different collections sizes!", files.size() == 1);
        assertTrue("Did no delete!", dao.deleteFile(id));
        files = dao.readAllFiles();
        assertTrue("Different collections sizes!", files.size() == 0);
    }

    @Test
    public void updateExistingFile_success() throws Exception {
        File old = new File("Test1");
        String id = dao.createFile(old);
        assertNotNull("Could not add new File!", id);
        File toUpdate = new File("Test2");
        toUpdate.setId(id);
        File updated = dao.updateFile(toUpdate);
        assertNotNull("Could not update!", updated);
        assertEquals("Did not update!", updated, toUpdate);
        assertNotEquals("Did not update!", updated, old);
    }

    @Test
    public void updateExistingFile_fails() throws Exception {
        File old = new File("Test1");
        String id = dao.createFile(old);
        assertNotNull("Could not add new File!", id);
        File toUpdate = new File("Test2");
        toUpdate.setId(new ObjectId().toString());
        File updated = dao.updateFile(toUpdate);
        assertNull("Could not update!", updated);
    }

    @Test
    public void testGetExpiredFiles() throws Exception {
        File expiredOne = new File("expired 1");
        expiredOne.setCreationDateTime();
        expiredOne.setCreationDateTime(LocalDateTime.from(expiredOne.getCreationDateTime()).minus(4, DAYS));

        File expiredTwo = new File("expired 2");
        expiredTwo.setCreationDateTime();
        expiredTwo.setCreationDateTime(LocalDateTime.from(expiredTwo.getCreationDateTime()).minus(4, DAYS));

        File notExpired = new File("not expired");
        notExpired.setCreationDateTime();

        assertNotNull(dao.createFile(expiredOne));
        assertNotNull(dao.createFile(expiredTwo));
        assertNotNull(dao.createFile(notExpired));

        assertEquals(dao.readAllFiles().size(), 3);
        assertEquals(dao.readExpiredFiles().size(), 2);
    }

    @Test
    public void testSaveAndReadUser_success() throws Exception {
        User newUser = new User("Test 1");
        String id = dao.createUser(newUser);
        assertNotNull(id);
        User readUser = dao.readUserById(id);
        assertEquals(newUser, readUser);
    }

    @Test
    public void testReadUser_fails() throws Exception {
        User readUser = dao.readUserById(new ObjectId().toString());
        assertNull(readUser);
    }

    @Test
    public void testAddThreeUsersAndReadThem() throws Exception {
        User one = new User("one");
        User two = new User("two");
        User three = new User("three");

        assertNotNull(dao.createUser(one));
        assertNotNull(dao.createUser(two));
        assertNotNull(dao.createUser(three));

        List<User> users = dao.readAllUsers();

        assertTrue(users.size() == 3);
    }

    @Test
    public void testAddUsersDeleteOne_susccess() throws Exception {
        User one = new User("one");
        User two = new User("two");
        User three = new User("three");

        assertNotNull(dao.createUser(one));
        String deletedId = dao.createUser(two);
        assertNotNull(deletedId);
        assertNotNull(dao.createUser(three));

        List<User> users = dao.readAllUsers();

        assertTrue(users.size() == 3);

        assertTrue(dao.deleteUser(deletedId));

        users = dao.readAllUsers();

        assertTrue(users.size() == 2);
    }

    @Test
    public void testUserUpdate() throws Exception {
        User old = new User("Test1");
        String id = dao.createUser(old);
        assertNotNull("Could not add new User!", id);
        User toUpdate = new User("Test2");
        toUpdate.setId(id);
        User updated = dao.updateUser(toUpdate);
        assertNotNull("Could not update!", updated);
        assertEquals("Did not update!", updated, toUpdate);
        assertNotEquals("Did not update!", updated, old);
    }

    @Test
    public void testGetAllUsersTasks() throws Exception {
        User owner = new User("Owner");
        File one = new File("File 1");
        File two = new File("File 2");
        File three = new File("File 3");

        owner.addFiles(one, two, three);
        owner.getFiles().forEach(dao::createFile);

        String userId = dao.createUser(owner);

        assertNotNull(userId);

        Set<File> files = dao.readUserById(userId).getFiles();

        assertTrue(files.size() == 3);

        Set<File> anotherFiles = dao.readAllFilesOfAUser(userId);

        assertEquals(files, anotherFiles);
    }

    @Test
    public void testGetFileOwner() throws Exception {
        User owner = new User("Owner");
        File one = new File("File 1");
        owner.addFiles(one);
        String fileId = dao.createFile(one);
        String id = dao.createUser(owner);
        String ownerId = dao.getFileOwner(fileId).getId();
        assertEquals(id, ownerId);
    }

    @Test
    public void testGetUserByName() throws Exception {
        String name = "Test Name";
        User namedUser = new User(name);
        assertNotNull(dao.createUser(namedUser));

        User gotUser = dao.readUserByName(name);

        assertEquals(namedUser, gotUser);
    }

    @Test
    public void testGetFileByName() throws Exception {
        String name = "Test";
        File one = new File (name);

        assertNotNull(dao.createFile(one));
        assertEquals(one, dao.readFileByName(name));
    }

    @Test
    public void aggregationsTest() throws Exception {
        User first = new User("First");
        User second = new User("Second");

        File one = new File("File 1");
        File two = new File("File 2");
        File three = new File("File 3");

        first.addFiles(one, two, three);

        first.getFiles().forEach(dao::createFile);

        dao.createUser(first);
        dao.createUser(second);

        one = dao.readFileByName(one.getFileName());

        dao.downloadFile(one.getId());
        dao.downloadFile(one.getId());
        dao.downloadFile(one.getId());

        two = dao.readFileByName(two.getFileName());

        dao.downloadFile(two.getId());
        dao.downloadFile(two.getId());
        dao.downloadFile(two.getId());

        assertEquals(1, dao.countActiveUsers());
        assertEquals(3, dao.countAllFiles());
        assertEquals(2, dao.countDownloadableFiles());
        assertEquals(6, dao.countTotalDownloads());
    }


}