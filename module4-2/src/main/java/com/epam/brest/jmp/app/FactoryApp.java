package com.epam.brest.jmp.app;

import com.epam.brest.jmp.factory.PersonsDatabaseFactory;
import com.epam.brest.jmp.factory.PersonsFactory;
import com.epam.brest.jmp.factory.PersonsFileFactory;
import com.epam.brest.jmp.model.Person;
import com.epam.brest.jmp.model.Staff;

import java.util.Scanner;

/**
 * Simple app to interact with {@link PersonsFactory}
 * Created by alexander_borohov on 6.3.17.
 */
public class FactoryApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a source to work with:");
        System.out.println("1. Files in a current file system.");
        System.out.println("2. Database");
        String choice = scanner.nextLine();
        PersonsFactory factory;
        if ("1".equals(choice) || "files".equals(choice)) {
            System.out.println("Files source was chosen");
            factory = new PersonsFileFactory();
        } else if ("2".equals(choice) || "database".equals(choice)) {
            System.out.println("Database was chosen");
            factory = new PersonsDatabaseFactory();
        } else {
            System.out.println("Default was chosen: Files");
            factory = new PersonsFileFactory();
        }
        do {
            System.out.println("Choose what to do:");
            System.out.println("1. Read a person randomly");
            System.out.println("2. Read a person by name");
            System.out.println("3. Write a person");
            System.out.println("Exit");
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println(factory.readPerson());
                    break;
                case "2":
                    System.out.println("Enter a name to read:");
                    String nameToRead = scanner.nextLine();
                    System.out.println(factory.readPerson(nameToRead));
                    break;
                case "3":
                    System.out.println("Enter a name to write:");
                    String nameToWrite = scanner.nextLine();
                    Person person = new Staff();
                    person.setName(nameToWrite);
                    factory.writePerson(person);
                    break;
            }
        } while (!"exit".equals(choice));
    }
}
