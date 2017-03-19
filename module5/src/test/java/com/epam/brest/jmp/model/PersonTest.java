package com.epam.brest.jmp.model;

import static com.epam.brest.jmp.model.Person.Sex.FEMALE;
import static com.epam.brest.jmp.model.Person.Sex.MALE;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * Created by alexander_borohov on 13.3.17.
 */
public class PersonTest {
    private static String testName1 = "";
    private static String testName2 = "Test";
    private static String testName3 = "TestTestTestTestTestTestTestTestTestTest";
    private static Integer testAge1 = -100;
    private static Integer testAge2 = 0;
    private static Integer testAge3 = 101;
    private static Integer testAge4 = 30;
    private static Person.Sex testSex1 = Person.Sex.valueOf("MALE");
    private static Person.Sex testSex2 = Person.Sex.valueOf("FEMALE");
    private static Person.Sex testSex3 = MALE;
    private static Person.Sex testSex4 = FEMALE;

    private static Validator validator;
    private static Person testPerson = new Person();

    @BeforeClass
    public static void setUp() {
        testPerson.setAge(testAge4);
        testPerson.setName(testName2);
        testPerson.setSex(MALE);

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @After
    public void tearDown() {
        testPerson.setAge(testAge4);
        testPerson.setName(testName2);
        testPerson.setSex(MALE);
    }

    @Test
    public void getNameTest1() throws Exception {
        testPerson.setName(testName1);
        Set<ConstraintViolation<Person>> violations = validator.validate(testPerson);
        assertEquals(1, violations.size());
        for (ConstraintViolation<Person> violation: violations) {
            assertEquals("", "Incorrect name size. Should be from 3 to 36", violation.getMessage());
        }
        assertEquals("NotEqual", testName1, testPerson.getName());
    }

    @Test
    public void getNameTest2() throws Exception {
        testPerson.setName(testName2);
        Set<ConstraintViolation<Person>> violations = validator.validate(testPerson);
        assertEquals(0, violations.size());
        assertEquals("NotEqual", testName2, testPerson.getName());
    }

    @Test
    public void getNameTest3() throws Exception {
        testPerson.setName(testName3);
        Set<ConstraintViolation<Person>> violations = validator.validate(testPerson);
        assertEquals(1, violations.size());
        for (ConstraintViolation<Person> violation: violations) {
            assertEquals("", "Incorrect name size. Should be from 3 to 36", violation.getMessage());
        }
        assertEquals("NotEqual", testName3, testPerson.getName());
    }


    @Test
    public void getAgeTest1() throws Exception {
        testPerson.setAge(testAge1);
        assertEquals("NotEqual", testAge1, testPerson.getAge());
        Set<ConstraintViolation<Person>> violations = validator.validate(testPerson);
        assertEquals(1, violations.size());
        for (ConstraintViolation<Person> violation: violations) {
            assertEquals("", "Age is out of range. Should be from 1 to 100", violation.getMessage());
        }
    }

    @Test
    public void getAgeTest2() throws Exception {
        testPerson.setAge(testAge2);
        assertEquals("NotEqual", testAge2, testPerson.getAge());
        Set<ConstraintViolation<Person>> violations = validator.validate(testPerson);
        assertEquals(1, violations.size());
        for (ConstraintViolation<Person> violation: violations) {
            assertEquals("", "Age is out of range. Should be from 1 to 100", violation.getMessage());
        }
    }

    @Test
    public void getAgeTest3() throws Exception {
        testPerson.setAge(testAge3);
        assertEquals("NotEqual", testAge3, testPerson.getAge());
        Set<ConstraintViolation<Person>> violations = validator.validate(testPerson);
        assertEquals(1, violations.size());
        for (ConstraintViolation<Person> violation: violations) {
            assertEquals("", "Age is out of range. Should be from 1 to 100", violation.getMessage());
        }
    }

    @Test
    public void getAgeTest4() throws Exception {
        testPerson.setAge(testAge4);
        assertEquals("NotEqual", testAge4, testPerson.getAge());
        Set<ConstraintViolation<Person>> violations = validator.validate(testPerson);
        assertEquals(0, violations.size());
    }

    @Test
    public void getSexTest() throws Exception {
        testPerson.setSex(testSex1);
        assertEquals("NotEqual", testSex1, testPerson.getSex());
        testPerson.setSex(testSex2);
        assertEquals("NotEqual", testSex2, testPerson.getSex());
        testPerson.setSex(testSex3);
        assertEquals("NotEqual", testSex3, testPerson.getSex());
        testPerson.setSex(testSex4);
        assertEquals("NotEqual", testSex4, testPerson.getSex());
    }

    @Test
    public void toStringTest() throws Exception {

    }

}