package com.epam.brest.jmp.controller;

import static com.epam.brest.jmp.model.Task.DATE_PATTERN;

import static java.lang.Thread.sleep;

import com.epam.brest.jmp.model.Task;
import com.epam.brest.jmp.model.User;
import com.epam.brest.jmp.service.ServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Simple console client implementation
 * Created by alexander_borohov on 9.2.17.
 */
@Service
public class TaskControllerConsoleImpl implements TaskController {
    private ServiceFacade serviceFacade;
    private Scanner scanner;

    @Autowired
    public void setServiceFacade(ServiceFacade serviceFacade) {
        this.serviceFacade = serviceFacade;
    }

    @Autowired
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    //    Reserved for further usage
//    private static final Logger LOGGER = LogManager.getLogger(TaskControllerConsoleImpl.class);

    @Override
    public void runTaskManager() {
        System.out.println("Welcome to Task Manager!");
        Integer choice;
        do {
            try {
                clearPage();
                helloPage();
                String input = scanner.next();
                if (input.matches("\\d*")) {
                    choice = Integer.parseInt(input);
                } else {
                    choice = -1;
                }
                switch (choice) {
                    case 1:
                        addNewTask();
                        break;
                    case 2:
                        showAllTasks();
                        break;
                    case 3:
                        removeSpecificTask();
                        break;
                    case 4:
                        removeAllTasks();
                        break;
                    case 5:
                        addNewUser();
                        break;
                    case 6:
                        showAllUsers();
                        break;
                    case 7:
                        removeSpecificUser();
                        break;
                    case 8:
                        removeAllUsers();
                        break;
                    case 0:
                        System.out.print("Bye bye!");
                        break;
                    default:
                        choice = incorrectChoice();
                }
            } catch (Exception e) {
                System.out.println("What went wrong: " + e.getMessage());
                choice = -1;
            }
        } while (choice != 0);
        scanner.close();
    }

    /**
     * Removes all users and tasks in cascade
     */
    private void removeAllUsers() throws InterruptedException {
        System.out.print("Are you sure? (Y, N)");
        String answer = scanner.nextLine();
        while (!"Y".equals(answer) && !"N".equals(answer)) {
            System.out.print("Are you sure? (Y, N)");
            answer = scanner.nextLine();
        }
        System.out.println("Deletion of all users and removing of all tasks by cascade begins");
        sleep(3000);

        serviceFacade.removeAllUsers();
    }

    /**
     * Prompts a user with questions for deletion process.
     * Also performs cascade remove of all User's tasks
     */
    private void removeSpecificUser() {
        System.out.println("Starting deletion process...");
        scanner.nextLine();
        System.out.print("Enter id of deleted user: ");
        String sId = scanner.nextLine();
        while (!sId.matches("\\d+")) {
            System.out.print("Incorrect input. Retry: ");
            sId = scanner.nextLine();
        }
        Integer id = Integer.parseInt(sId);

        User user = serviceFacade.getUserById(id);

        serviceFacade.removeSpecificUser(id);
        System.out.println("User deleted");
    }

    /**
     * Shows a list of all users
     */
    private void showAllUsers() {
        System.out.println("List of users: ");
        List<User> users = serviceFacade.showAllUsers();
        if (!users.isEmpty()) {
            for (User user : users) {
                System.out.println(user);
            }
        } else {
            System.out.println("Empty users list.");
        }
    }

    /**
     * Provides user dialog to create a new {@link User}
     */
    private void addNewUser() {
        System.out.println("Adding a New User.");
        scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        while (!email.matches("(.*)(@)(.*)(\\.)(.{1,3})")) {
            System.out.print("You've entered incorrect email. Please retry: ");
            email = scanner.nextLine();
        }

        User user = new User(name, surname, email);

        System.out.printf("Added user with id: %d\n", serviceFacade.addNewUser(user));
    }

    /**
     * Prompts user with dialog to ensure deletion of all tasks.
     * Returns to main menu in any case, providing a result message
     */
    private void removeAllTasks() {
        System.out.println("Are you sure? (Enter 'y' if yes, and 'n' if no)");
        String answer = scanner.next();
        if (answer.equals("y")) {
            System.out.println("Removing all tasks!");
            if (serviceFacade.removeAllTasks()) {
                System.out.println("All tasks were deleted.");
            } else {
                System.out.println("Tasks were not deleted as error occured!");
            }
        } else {
            System.out.println("Tasks were not deleted.");
        }
    }

    /**
     * Prompts user with a dialog to delete a certain task from TaskList.
     * Returns to main menu in any case, providing a result message
     */
    private void removeSpecificTask() {
        System.out.print("Enter id of removed task: ");
        try {
            Integer id = scanner.nextInt();
            if (serviceFacade.removeSpecificTask(id)) {
                System.out.printf("Task #%d was successfully removed.\n", id);
            } else {
                System.out.printf("Task #%d was not removed as it probably does not exist.\n", id);
            }
        } catch (InputMismatchException e) {
            System.out.println("You made incorrect choice. Please try again.");
            removeSpecificTask();
        }
    }

    /**
     * Prints out a full list of tasks or prints a special message in case of empty result.
     * Returns to main menu after execution
     */
    private void showAllTasks() {
        List<Task> tasks = serviceFacade.showAllTasks();
        if (!tasks.isEmpty()) {
            System.out.println("Showing all tasks:");
            for (Task task : tasks) {
                System.out.println(task);
            }
        } else {
            System.out.println("You have no tasks!");
        }
    }

    /**
     * Prompts user with a dialog for creation of a new {@link Task}.
     * Returns to main menu after printing out an ID of a {@link Task} or an error message
     */
    private void addNewTask() {
        System.out.println("Adding a New Task.");
        scanner.nextLine();
        System.out.print("Enter Task description: ");
        String description = scanner.nextLine().replaceAll(",", "").trim();
        System.out.print("Enter Task name: ");
        String name = scanner.nextLine().replaceAll(",", "").trim();
        System.out.print("Enter Task owner: ");
        Integer userId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Task deadline (e.g. 2017-03-21): ");
        DateFormat formatter = new SimpleDateFormat(DATE_PATTERN);
        Date deadLine = null;
        while (deadLine == null) {
            try {
                deadLine = formatter.parse(scanner.nextLine());
            } catch (ParseException e) {
                System.out.println(e.getMessage() + ". Used deafault deadline.");
            }

        }
        Task task = new Task(name, description, deadLine, userId);
        Integer id = serviceFacade.addNewTask(task);
        if (id != null) {
            System.out.printf("You have added new task. ID: %d \n", id);
        } else {
            System.out.println("Task was not added!");
        }
    }

    /**
     * Prints out main menu with available options
     */
    private void helloPage() {
        System.out.println("Choose an option:");
        System.out.println("1. Add new task");
        System.out.println("2. Show all tasks");
        System.out.println("3. Remove specific Task");
        System.out.println("4. Remove all tasks");
        System.out.println("5. Add new user");
        System.out.println("6. Show all users");
        System.out.println("7. Remove specific User");
        System.out.println("8. Remove all users");
        System.out.println("0. Quit");
    }

    /**
     * Clears all current console output
     */
    private void clearPage() {
    }

    /**
     * Prints out an error message in case of any violations of input type
     *
     * @return special "-1" value
     */
    private Integer incorrectChoice() {
        System.out.println("You made incorrect choice. Please try again.");
        return -1;
    }


}
