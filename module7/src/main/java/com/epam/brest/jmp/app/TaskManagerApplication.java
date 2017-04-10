package com.epam.brest.jmp.app;

import com.epam.brest.jmp.config.AppTestConfig;
import com.epam.brest.jmp.controller.TaskController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Simple class for running application
 * Created by alexander_borohov on 9.2.17.
 */
public class TaskManagerApplication {

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("Prod");
        context.register(AppTestConfig.class);
        context.refresh();

        context.getBean(TaskController.class).runTaskManager();
    }

}
