package com.epam.brest.jmp.decorator;

import com.epam.brest.jmp.model.Person;
import org.apache.commons.lang3.text.WordUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 * Simple OutputDecorator that lets you to read a Person object from any source using underlying {@link ObjectInputStream}
 * It decorates ObjectInputStream with possibility of capitalizing a {@link Person#name}
 * Created by alexander_borohov on 10.3.17.
 */
public class PersonInputStream extends InputStream {
    private ObjectInputStream inputStream;

    public PersonInputStream(InputStream inputStream) {
        this.inputStream = (ObjectInputStream) inputStream;
    }

    @Override
    public int read() throws IOException {
        return inputStream.read();
    }

    public Person readPerson() throws IOException, ClassNotFoundException {
        ObjectInputStream stream = inputStream;
        Person person = (Person) stream.readObject();
        person.setName(WordUtils.capitalizeFully(person.getName()));
        return person;
    }
}
