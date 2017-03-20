package com.epam.brest.jmp.soap;

import static javax.jws.soap.SOAPBinding.ParameterStyle.WRAPPED;
import static javax.jws.soap.SOAPBinding.Style.DOCUMENT;
import static javax.jws.soap.SOAPBinding.Use.LITERAL;

import com.epam.brest.jmp.model.Task;
import com.epam.brest.jmp.model.User;
import com.epam.brest.jmp.model.exceptions.ServiceException;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by alexander_borohov on 20.3.17.
 */
@WebService
@SOAPBinding(style = DOCUMENT, parameterStyle = WRAPPED, use = LITERAL)
public interface WebInterface {
    @WebMethod
    Integer createTask(Task task) throws ServiceException;
    @WebMethod
    Task getTask(Integer id) throws ServiceException;
    @WebMethod
    Task updateTask(Task task) throws ServiceException;
    @WebMethod
    Boolean deleteTask(Integer id) throws ServiceException;
    @WebMethod
    List<Task> getAllTasks() throws ServiceException;
    @WebMethod
    List<Task> gettAllTasksOfAUser(Integer id) throws ServiceException;
    @WebMethod
    Boolean deleteAllTasks() throws ServiceException;
    @WebMethod
    Integer createUser(User user) throws ServiceException;
    @WebMethod
    User getUser(Integer id) throws ServiceException;
    @WebMethod
    User updateUser(User user) throws ServiceException;
    @WebMethod
    Boolean deleteUser(Integer id) throws ServiceException;
    @WebMethod
    List<User> getAllUsers() throws ServiceException;
    @WebMethod
    Boolean deleteAllUsers() throws ServiceException;
    @WebMethod
    User getTaskOwner(Integer taskId) throws ServiceException;
}
