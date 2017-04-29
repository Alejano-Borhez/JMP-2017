package com.epam.brest.jmp.web;

import com.epam.brest.jmp.service.ServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Simple taskController
 * Created by alexander_borohov on 14.4.17.
 */
@Controller
public class TaskController {
    @Autowired
    private ServiceFacade serviceFacade;

    @RequestMapping("/user/{userId}/task/{taskId}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String getTask(@PathVariable("userId") Integer userId,
                          @PathVariable("taskId") Integer taskId,
                          Model model) {
        model.addAttribute("user", serviceFacade.getUserById(userId));
        model.addAttribute("task", serviceFacade.getTaskById(taskId));
        return "task";
    }

    @RequestMapping("/tasks")
    public String getTasks(Model model) {
        model.addAttribute("tasks", serviceFacade.showAllTasks());
        return "tasks";
    }
}
