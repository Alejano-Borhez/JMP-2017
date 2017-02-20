package com.epam.brest.jmp.leaks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by alexander_borohov on 17.2.17.
 */
public class FileLineByLineReader {


    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        String exit = null;
        while (exit == null || !exit.equals("exit")) {
            List<String> lines = new ArrayList<String>();
            System.out.println("Started");
            InputStream inputStream = FileLineByLineReader.class.getClassLoader().getResourceAsStream("data.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line.substring(0, 3));
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(lines.get(i));
            }
            System.out.println("Finished. Type 'exit' to quit");
            Scanner scanner = new Scanner(System.in);
            exit = scanner.nextLine();
        }

    }

}
