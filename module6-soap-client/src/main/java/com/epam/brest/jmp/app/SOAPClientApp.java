package com.epam.brest.jmp.app;

import com.epam.brest.jmp.soap.Task;
import com.epam.brest.jmp.soap.User;
import com.epam.brest.jmp.soap.WebInterface;
import com.epam.brest.jmp.soap.WebServiceControllerService;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexander_borohov on 20.3.17.
 */
public class SOAPClientApp {
    private static int countUsers = 0;
    private static int countTasks = 0;
    public static void main(String[] args) {
        WebServiceControllerService controllerService = new WebServiceControllerService();

        WebInterface webInterface = controllerService.getWebServiceControllerPort();

        User first = newUser();
        User second = newUser();
        User third = newUser();


        List<Integer> userIds = new ArrayList<>();
        List<Integer> taskIds = new ArrayList<>();

        first.setId(webInterface.createUser(first));
        userIds.add(first.getId());

        second.setId(webInterface.createUser(second));
        userIds.add(second.getId());

        third.setId(webInterface.createUser(third));
        userIds.add(third.getId());

        Task first_first = newTask(first);
        Task second_first = newTask(first);
        Task third_first = newTask(first);

        Task first_second = newTask(second);
        Task second_second = newTask(second);
        Task third_second = newTask(second);

        Task first_third = newTask(third);
        Task second_third = newTask(third);
        Task third_third = newTask(third);

        taskIds.add(webInterface.createTask(first_first));
        taskIds.add(webInterface.createTask(second_first));
        taskIds.add(webInterface.createTask(third_first));
        taskIds.add(webInterface.createTask(first_second));
        taskIds.add(webInterface.createTask(second_second));
        taskIds.add(webInterface.createTask(third_second));
        taskIds.add(webInterface.createTask(first_third));
        taskIds.add(webInterface.createTask(second_third));
        taskIds.add(webInterface.createTask(third_third));
        System.out.println("List of created users:");
        for (Integer i: userIds) {
            System.out.println(webInterface.getUser(i));
        }
        System.out.println("List of created tasks:");
        for (Integer i: taskIds) {
            System.out.println(webInterface.getTask(i));
        }
    }

    public static User newUser() {
        User newUser = new User();
        newUser.setName("TestName" + countUsers);
        newUser.setSurname("TestSurname" + countUsers);
        newUser.setEmail("test" + countUsers + "@gmail.com");
        countUsers++;
        return newUser;
    }

    public static Task newTask(User user) {
        Task newTask = new Task();
        newTask.setUserId(user.getId());
        newTask.setName("TestTask" + countTasks);
        newTask.setDescription("TestDesc" + countTasks);
        newTask.setDeadLine(new XMLGregorianCalendarImpl());
        countTasks++;
        return newTask;
    }
}
