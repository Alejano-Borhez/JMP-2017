package com.epam.brest.jmp.leaks;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;

/**
 * Created by alexander_borohov on 20.2.17.
 */
public class FileToReadPreparator {

    public static InputStream prepareFile() throws IOException {
        InputStream inputStream = FileLineByLineReaderWithLeak.class.getClassLoader().getResourceAsStream("data.txt");
        File fileToSave = new File("temp");
        OutputStream outputStream = new FileOutputStream(fileToSave);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        String line;
        Random random = new Random();
        int i = 0;
        while ((line = bufferedReader.readLine()) != null && (i = line.length() - 1) != 0) {
            StringBuilder stringBuilder = new StringBuilder(line);
            Double j = (Math.random() * i);
            if (j > i) {
                j = (double) i;
            } else if (j - 5 < 0) {
                j = 5d;
            }
            stringBuilder.replace(0, 5, stringBuilder.substring(j.intValue() - 5, j.intValue()));
            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
        return new FileInputStream(fileToSave);
    }
}
