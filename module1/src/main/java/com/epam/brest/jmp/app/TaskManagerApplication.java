package com.epam.brest.jmp.app;

import com.epam.brest.jmp.controller.TaskController;
import com.epam.brest.jmp.controller.TaskControllerConsoleImpl;
import com.epam.brest.jmp.dao.TaskDao;
import com.epam.brest.jmp.dao.TaskDaoCSVImpl;
import com.epam.brest.jmp.service.TaskService;
import com.epam.brest.jmp.service.TaskServiceImpl;

/**
 * Created by alexander_borohov on 9.2.17.
 */
public class TaskManagerApplication {
    public static void main(String[] args) {
//        Providing dao's CSV file implementation
        TaskDao dao = new TaskDaoCSVImpl();
//        Providing service's basic implementation
        TaskService service = new TaskServiceImpl(dao);
//        Providing controller's Console implementation
        TaskController controller = new TaskControllerConsoleImpl(service);
//        Running an application
        controller.runTaskManager();
    }
}
