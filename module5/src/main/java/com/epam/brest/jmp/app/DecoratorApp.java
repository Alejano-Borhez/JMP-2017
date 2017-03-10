package com.epam.brest.jmp.app;

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

/**
 * Simple decorator demo
 *
 * Created by alexander_borohov on 10.3.17.
 */
public class DecoratorApp {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//      Decorating our PersonOutputStream with sequence of outputStreams: ObjectOutputStream and FileOutputStream
        PersonOutputStream outputStream = new PersonOutputStream(new ObjectOutputStream(new FileOutputStream("temp.txt")));
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name");
        String name = scanner.nextLine();
        System.out.println("Enter age");
        String ageString = scanner.nextLine();
        System.out.println("Enter sex");
        String sexString = scanner.nextLine();
        Person person = new Person();
        person.setName(name);
        person.setAge(Integer.parseInt(ageString));
        person.setSex(!StringUtils.containsIgnoreCase(sexString,"female"));

        outputStream.writePerson(person);
//      Decorating our PersonInputStream with sequence of outputStreams: ObjectInputStream and FileInputStream
        PersonInputStream inputStream = new PersonInputStream(new ObjectInputStream(new FileInputStream("temp.txt")));
        Person readPerson = inputStream.readPerson();
        System.out.println(readPerson);
    }
}
