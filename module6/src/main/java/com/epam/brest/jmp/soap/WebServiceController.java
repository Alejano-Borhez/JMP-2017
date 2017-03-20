package com.epam.brest.jmp.soap;

import com.epam.brest.jmp.dao.impl.TaskInMemoryDao;
import com.epam.brest.jmp.dao.impl.UserInMemoryDao;
import com.epam.brest.jmp.model.Task;
import com.epam.brest.jmp.model.User;
import com.epam.brest.jmp.model.exceptions.ServiceException;
import com.epam.brest.jmp.service.ServiceFacade;
import com.epam.brest.jmp.service.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;

/**
 * Simple JAX-WS serice
 * Created by alexander_borohov on 20.3.17.
 */
@WebService(endpointInterface = "com.epam.brest.jmp.soap.WebInterface")
public class WebServiceController implements WebInterface {
    private static final ServiceFacade service =
            new ServiceImpl(new TaskInMemoryDao(new ArrayList<>()), new UserInMemoryDao(new ArrayList<>()));


    @Override
    public Task getTask(Integer id) throws ServiceException {
        return service.getTaskById(id);
    }

    @Override
    public Task updateTask(Task task) throws ServiceException {
        return service.updateTask(task);
    }

    @Override
    public Boolean deleteTask(Integer id) throws ServiceException {
        return service.removeSpecificTask(id);
    }

    @Override
    public List<Task> getAllTasks() throws ServiceException {
        return service.showAllTasks();
    }

    @Override
    public List<Task> gettAllTasksOfAUser(Integer id) throws ServiceException {
        return service.getAllTaskOfAUser(id);
    }

    @Override
    public Boolean deleteAllTasks() throws ServiceException {
        return service.removeAllTasks();
    }

    @Override
    public Integer createUser(User user) throws ServiceException {
        return service.addNewUser(user);
    }

    @Override
    public User getUser(Integer id) throws ServiceException {
        return service.getUserById(id);
    }

    @Override
    public User updateUser(User user) throws ServiceException {
        return service.updateUser(user);
    }

    @Override
    public Boolean deleteUser(Integer id) throws ServiceException {
        return service.removeSpecificUser(id);
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        return service.showAllUsers();
    }

    @Override
    public Boolean deleteAllUsers() throws ServiceException {
        return service.removeAllUsers();
    }

    @Override
    public User getTaskOwner(Integer taskId) throws ServiceException {
        return service.getTaskOwner(taskId);
    }

    @Override
    public Integer createTask(Task task) throws ServiceException {
        return service.addNewTask(task);
    }


}
