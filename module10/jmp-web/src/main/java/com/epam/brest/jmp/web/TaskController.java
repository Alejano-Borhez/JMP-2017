package com.epam.brest.jmp.web;

import com.epam.brest.jmp.model.Task;
import com.epam.brest.jmp.model.User;
import com.epam.brest.jmp.model.exceptions.DaoException;
import com.epam.brest.jmp.model.exceptions.ServiceException;
import com.epam.brest.jmp.service.ServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Simple taskController
 * Created by alexander_borohov on 14.4.17.
 */
@Controller
public class TaskController {
    @Autowired
    private ServiceFacade serviceFacade;

    @RequestMapping("/user/{userId}/task/{taskId}")
    public String getTask(@PathVariable("userId") Integer userId,
                          @PathVariable("taskId") Integer taskId,
                          Model model, RedirectAttributes attributes) {
        User user = null;
        Task task = null;
        try {
            user = serviceFacade.getUserById(userId);
            task = serviceFacade.getTaskById(taskId);
        } catch (ServiceException | DaoException exception) {
            attributes.addFlashAttribute("error", exception.getMessage());
            return "redirect:/error";
        }
        model.addAttribute("user", user);
        model.addAttribute("task", task);
        return "task";
    }

    @RequestMapping("/error")
    public ModelAndView errorPage() {


        return new ModelAndView("error");
    }
}
