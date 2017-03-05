package com.epam.brest.jmp.factory;

import com.epam.brest.jmp.model.Person;

/**
 * Simple interface for accessing a {@link Person} repository
 * Created by alexander_borohov on 6.3.17.
 */
public interface PersonsFactory {
    void writePerson(Person person);

    Person readPerson();

    Person readPerson(String name);
}
