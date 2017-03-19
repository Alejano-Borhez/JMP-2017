package com.epam.brest.jmp.decorator;

import static com.epam.brest.jmp.model.Person.Sex.MALE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
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
 * Simple tests for {@link PersonOutputStream}
 * First we write a {@link Person} using {@link PersonOutputStream}
 * Then read a {@link Person} using raw {@link ObjectInputStream}
 * Created by alexander_borohov on 13.3.17.
 */
public class PersonOutputStreamTest {
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
    }

    @Test
    public void constructorTest() throws Exception {
        PersonOutputStream stream1 = new PersonOutputStream(new ObjectOutputStream(System.out));

        assertNotNull("Could not instantiate.", stream1);

        PersonOutputStream stream2 = new PersonOutputStream(new ObjectOutputStream(new FileOutputStream(testFile)));

        assertNotNull("Could not instantiate.", stream2);
    }

    @Test
    public void writeTest() throws Exception {
        String testNameNotCapitalized = "test automated";
        String testNameCapitalized = WordUtils.capitalizeFully(testNameNotCapitalized);

        PersonOutputStream stream = new PersonOutputStream(new ObjectOutputStream(new FileOutputStream(testFile)));
        Person testPerson = new Person();
        testPerson.setSex(MALE);
        testPerson.setAge(30);
        testPerson.setName(testNameNotCapitalized);

        stream.writePerson(testPerson);

        FileInputStream fileInputStream = new FileInputStream(testFile);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        assertTrue("Did not create a file", testFile.exists());
        Person newPerson = (Person) objectInputStream.readObject();
        assertNotEquals("Did not decorate", newPerson.getName(), testPerson.getName());
        assertEquals("Did not decorate", newPerson.getName(), testNameCapitalized);
    }
}