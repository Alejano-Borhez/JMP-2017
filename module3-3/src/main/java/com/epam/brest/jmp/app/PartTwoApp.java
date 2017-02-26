package com.epam.brest.jmp.app;

import com.epam.brest.jmp.classloader.CustomLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Scanner;

/**
 * Part2 of this task is implemented
 * During execution user is prompted with class source path (either 'old' or 'new' should be used)
 * Reflection allows user to choose a method to execute and pass parameters
 * Created by alexander_borohov on 25.2.17.
 */
public class PartTwoApp {
    public static void main(String[] args) {
        System.out.println("Hello! Starting to work");
        String input = "";
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.printf("Enter a path to load class from:\n");
            input = scanner.nextLine();
            if ("old".equals(input) || "new".equals(input)) {
                System.setProperty("cp", input);
            } else {
                continue;
            }
//            Getting new instance of classLoader to reload a class
            ClassLoader classLoader = new CustomLoader(PartTwoApp.class.getClassLoader());
            try {
                Class<?> clazz = classLoader.loadClass("Semaphore");
//            Instantiating Semaphore object (with no reference to object's class)
                Object semaphore = clazz.newInstance();
                System.out.printf("Loaded a class %s\n", clazz.getName());
                Method[] methods = clazz.getMethods();
//              Asking user to choose a method to invoke
                System.out.printf("Choose a method number of %s class to invoke:\n", clazz.getName());
                int i = 0;
                for (Method method : methods) {
                    i++;
                    System.out.printf("%d. %s\n", i, method);
                }
                input = scanner.nextLine();
                int methodNum = Integer.parseInt(input);
                Method method = methods[methodNum - 1];
                Parameter[] methodParameters = method.getParameters();
                if (methodParameters.length == 0) {
                    try {
                        Object result = method.invoke(semaphore);
                        i++;
                        if (result != null) System.out.printf("Result: %s\n", result.toString());
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        System.out.printf("Could not invoke %s method\n", method.getName());
                    }
                } else {
                    Object[] params = new Object[methodParameters.length];
                    for (int j = 0; j < methodParameters.length; j++) {
                        Parameter methodParameter = methodParameters[j];
                        Object parameter = null;
                        Class<?> type = methodParameter.getType();
                        System.out.printf("Enter %dth parameter of %s type", j, type.getTypeName());
                        input = scanner.nextLine();
                        if (type.getTypeName().equals("int")) {
                            parameter = Integer.parseInt(input);
                        } else if (type.getTypeName().equals("long")) {
                            parameter = Long.parseLong(input);
                        } else {
                            parameter = input;
                        }
                        params[j] = parameter;
                    }
                    Object result = method.invoke(semaphore, params);
                    i++;
                    if (result != null) System.out.printf("Result: %s\n", result.toString());
                }
                if (i == 0) System.out.printf("There is no method %s\n", input);
            } catch (ClassNotFoundException e) {
                System.out.println("Could not load a class Semaphore");
                e.printStackTrace();
            } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }

        } while (!"exit".equals(input));
    }
}