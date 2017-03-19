package com.epam.brest.jmp.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Just for tests
 * Created by alexander_borohov on 13.3.17.
 */
public class FakeInputStream extends FileInputStream {

    public FakeInputStream(String name) throws FileNotFoundException {
        super("temp.txt");
    }
}
