package com.epam.brest.jmp.controller;

import static com.epam.brest.jmp.model.Task.DATE_PATTERN;

import com.epam.brest.jmp.model.Task;
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
        try {
            deadLine = formatter.parse(scanner.nextLine());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
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
        System.out.println("3. Remove specific task");
        System.out.println("4. Remove all tasks");
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
