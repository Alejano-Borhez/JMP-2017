package com.epam.brest.jmp.web;

import static org.apache.logging.log4j.LogManager.getLogger;

import com.epam.brest.jmp.model.Task;
import com.epam.brest.jmp.service.ServiceFacade;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.Principal;
import java.util.Date;
import javax.validation.Valid;

/**
 * Simple taskController
 * Created by alexander_borohov on 14.4.17.
 */
@Controller
public class TaskController {
    private static final Logger LOGGER = getLogger(TaskController.class);
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

    @PostMapping(value = "/task/new")
    public String createTask(@Valid @ModelAttribute("task") Task task,
                             BindingResult bindingResult,
                             Principal principal,
                             Model model) {
        if (bindingResult.hasErrors()) {
            LOGGER.debug("Got errors: {}", bindingResult.getAllErrors());
            return "task_new";
        }
        task.setCreationDate(new Date());
        serviceFacade.addNewTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/task/new")
    public String createTaskPage(Model model) {
        model.addAttribute("task", new Task());
        return "task_new";
    }
}
