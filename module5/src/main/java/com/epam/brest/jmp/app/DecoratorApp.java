package com.epam.brest.jmp.app;

import static com.epam.brest.jmp.model.Person.Sex.FEMALE;
import static com.epam.brest.jmp.model.Person.Sex.MALE;

import static java.lang.Thread.sleep;

import com.epam.brest.jmp.decorator.PersonInputStream;
import com.epam.brest.jmp.decorator.PersonOutputStream;
import com.epam.brest.jmp.model.Person;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Simple decorator demo
 * <p>
 * Created by alexander_borohov on 10.3.17.
 */
public class DecoratorApp {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        DecoratorApp app = new DecoratorApp();
//      Decorating our PersonOutputStream with sequence of outputStreams: ObjectOutputStream and FileOutputStream
        PersonOutputStream outputStream = new PersonOutputStream(new ObjectOutputStream(new FileOutputStream("temp.txt")));
        Scanner scanner = new Scanner(System.in);
        Boolean isValidPerson;
//        Trying to persist a Person until we get all fields valid without any violations
        do {
            System.out.println("Enter name");
            String name = scanner.nextLine();
            System.out.println("Enter age");
            String ageString = scanner.nextLine();
            System.out.println("Enter sex");
            String sexString = scanner.nextLine();
            Person person = new Person();
            person.setName(name);
            person.setAge(Integer.parseInt(ageString));
            person.setSex((StringUtils.containsIgnoreCase(sexString, "female")) ? FEMALE : MALE);
//          Validating constructed Person
            isValidPerson = app.validatePerson(person);

            if (isValidPerson) {
                System.out.println("Writing a person to a file...");
                sleep(2000);
                outputStream.writePerson(person);
                System.out.println(person);
//      Decorating our PersonInputStream with sequence of outputStreams: ObjectInputStream and FileInputStream
                PersonInputStream inputStream = new PersonInputStream(new ObjectInputStream(new FileInputStream("temp.txt")));
                Person readPerson = inputStream.readPerson();
                System.out.println("Reading a person from a file...");
                sleep(2000);
                System.out.println(readPerson);
            } else {
                System.out.println("You've entered incorrect data. Read beyond.");
            }
        } while (!isValidPerson);
    }

    private Boolean validatePerson(Person person) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        if (violations.size() == 0) {
            return true;
        } else {
            for (ConstraintViolation<Person> violation : violations) {
                System.out.printf("Incorrect value %s: %s!\n", violation.getInvalidValue(), violation.getMessage());
            }
            return false;
        }
    }
}
