package com.epam.brest.jmp.app;

import com.epam.brest.jmp.classloader.CustomLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Part1 of this task is implemented
 * Pass -Dcp system property while running it
 * Created by alexander_borohov on 25.2.17.
 */
public class PartOneApp {
    public static void main(String[] args) {
        System.out.println("Hello! Starting to work");
        ClassLoader classLoader = new CustomLoader(PartOneApp.class.getClassLoader());
        try {
            Class<?> clazz = classLoader.loadClass("Semaphore");
//            Instantiating Semaphore object (with no reference to object's class)
            Object semaphore = clazz.newInstance();
            System.out.printf("Loaded a class %s", clazz.getName());
            Method[] methods = clazz.getMethods();
//            Trying to invoke lever() method
            for (Method method : methods) {
                if (method.getName().equals("lever")) {
                    try {
                        method.invoke(semaphore);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        System.out.println("Could not invoke lever method");
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load a class Semaphore");
            e.printStackTrace();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
