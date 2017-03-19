package com.epam.brest.jmp.decorator;

import com.epam.brest.jmp.model.Person;
import org.apache.commons.lang3.text.WordUtils;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Simple OutputDecorator that lets you to read a Person object from any source using underlying {@link ObjectOutputStream}
 * It decorates ObjectOutputStream with possibility of capitalizing a {@link Person#name}
 * Created by alexander_borohov on 10.3.17.
 */
public class PersonOutputStream extends OutputStream {
    private ObjectOutputStream outputStream;

    public PersonOutputStream(OutputStream outputStream) {
        this.outputStream = (ObjectOutputStream) outputStream;
    }

    @Override
    public void write(int b) throws IOException {
        outputStream.write(b);
    }

    public void writePerson(Person person) throws IOException {
        Person personToWrite = new Person();
        personToWrite.setAge(person.getAge());
        personToWrite.setName(WordUtils.capitalizeFully(person.getName()));
        personToWrite.setSex(person.getSex());

        outputStream.writeObject(personToWrite);
    }
}
