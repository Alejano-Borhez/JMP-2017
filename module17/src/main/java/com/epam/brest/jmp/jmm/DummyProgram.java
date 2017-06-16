package com.epam.brest.jmp.jmm;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

/**
 * Executing endless creation of {@link DummyObject} collection and then releasing memory
 * Created by alexander_borohov on 16.6.17.
 */
public class DummyProgram {
    public static void main(String[] args) {

        Stream.iterate(1, i -> ++i)
                .forEach(
                        i -> {
                            final List<DummyObject> objects = new CopyOnWriteArrayList<>();
                            Stream.iterate("abc", j -> new String(j + new Random(i).nextInt()))
                                    .limit(500)
                                    .forEach(j -> objects.add(new DummyObject(j, i))
                                    );
                            if (i % 10000 == 0) {
                                System.out.println("Iteration #" + i);
                                System.out.println("objects = " + objects);
                            }
                            objects.clear();
                        }
                );
    }
}
