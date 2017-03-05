package com.epam.brest.jmp.factory;

import com.epam.brest.jmp.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Simple implementation of {@link PersonsFactory} with Database Storage
 * Created by alexander_borohov on 6.3.17.
 */
public class PersonsDatabaseFactory implements PersonsFactory {
    private List<Person> people;

    public PersonsDatabaseFactory() {
        this.people = new ArrayList<>();
    }

    @Override
    public void writePerson(Person person) {
        databaseOperations("write");
        if (!people.contains(person)) people.add(person);
    }

    private void databaseOperations(String operation) {
        System.out.printf("Loading driver and creating connection to %s a Person...\n", operation);
        if ("write".equals(operation)) {
            System.out.println("Serializing a Person...");
        } else {
            System.out.println("Deserializing a Person...");
        }
        System.out.printf("%sing a Person...\n", operation.toLowerCase().replace(operation.substring(0, 1), operation.substring(0, 1).toUpperCase()));
    }

    @Override
    public Person readPerson() {
        databaseOperations("read");
        if (!people.isEmpty()) {
            if (people.size() > 1) {
                return people.get(new Random().nextInt(people.size() - 1));
            } else {
                return people.get(0);
            }
        } else {
            System.out.println("No Persons persisted!");
            return null;
        }
    }

    @Override
    public Person readPerson(String name) {
        databaseOperations("read");
        if (!people.isEmpty() && name != null) {
            for (Person p : people) {
                if (name.equals(p.getName())) {
                    return p;
                }
            }
            System.out.printf("No Persons with name %s persisted!\n", name);
            return null;
        } else {
            System.out.println("No Persons persisted!");
            return null;
        }
    }
}
