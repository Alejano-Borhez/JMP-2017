package com.epam.brest.jmp.decorator;

import static com.epam.brest.jmp.model.Person.Sex.MALE;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.epam.brest.jmp.model.Person;
import org.apache.commons.lang3.text.WordUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Simple tests for {@link PersonInputStream}
 * First we write a {@link Person} using raw {@link ObjectOutputStream}
 * Then read a {@link Person} using {@link PersonInputStream}
 * Created by alexander_borohov on 13.3.17.
 */
public class PersonInputStreamTest {
    private static final String testFileName = "temp.txt";
    private static File testFile;
    private static Path tempFile;

    @Before
    public void setUp() throws Exception {
        tempFile = Files.createTempFile(testFileName, null);
        testFile = tempFile.toFile();
    }

    @After
    public void tearDown() throws Exception {
        Files.deleteIfExists(tempFile);
        verify();
    }

    @Test
    public void writeTest() throws Exception {
        String testNameNotCapitalized = "test automated";
        String testNameCapitalized = WordUtils.capitalizeFully(testNameNotCapitalized);

        FileOutputStream fileOutputStream = new FileOutputStream(testFile);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        Person testPerson = new Person();
        testPerson.setSex(MALE);
        testPerson.setAge(30);
        testPerson.setName(testNameNotCapitalized);

        objectOutputStream.writeObject(testPerson);

        PersonInputStream stream = new PersonInputStream(new ObjectInputStream(new FileInputStream(testFile)));

        assertTrue("Did not create a file", testFile.exists());
        Person newPerson = stream.readPerson();

        assertNotEquals("Did not decorate", newPerson.getName(), testPerson.getName());
        assertEquals("Did not decorate", newPerson.getName(), testNameCapitalized);
    }
}