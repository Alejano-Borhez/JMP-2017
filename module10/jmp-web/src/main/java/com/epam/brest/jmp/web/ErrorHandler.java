package com.epam.brest.jmp.web;

import com.epam.brest.jmp.model.exceptions.DaoException;
import com.epam.brest.jmp.model.exceptions.ServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Simple error handler
 * Created by alexander_borohov on 29.4.17.
 */
@ControllerAdvice(annotations = {Controller.class}, basePackageClasses = {TaskController.class, UserController.class})
public class ErrorHandler {
    @ExceptionHandler(DaoException.class)
    public String notFound(DaoException daoException, Model model) {
        model.addAttribute("error", daoException);
        return "error";
    }

    @ExceptionHandler(ServiceException.class)
    public String serviceError(ServiceException serviceException, Model model) {
        model.addAttribute("error", serviceException);
        return "error";
    }
}
