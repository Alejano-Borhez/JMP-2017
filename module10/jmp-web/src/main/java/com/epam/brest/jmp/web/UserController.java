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
public class UserController {
    @Autowired
    private ServiceFacade serviceFacade;

    @RequestMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String getUser(@PathVariable("userId") Integer userId,
                          Model model) {
        model.addAttribute("user", serviceFacade.getUserById(userId));
        model.addAttribute("tasks", serviceFacade.getAllTaskOfAUser(userId));
        return "user";
    }

    @RequestMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", serviceFacade.showAllUsers());
        return "users";
    }
}
