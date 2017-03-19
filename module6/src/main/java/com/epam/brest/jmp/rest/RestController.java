package com.epam.brest.jmp.rest;

import static java.lang.String.format;

import com.epam.brest.jmp.model.Task;
import com.epam.brest.jmp.model.User;
import com.epam.brest.jmp.model.exceptions.RestServiceException;
import com.epam.brest.jmp.model.exceptions.ServiceException;
import com.epam.brest.jmp.service.ServiceFacade;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Simple RESTful controller
 * Created by alexander_borohov on 17.3.17.
 */
@Path("/app")

public class RestController {
    private ServiceFacade serviceFacade;

    public RestController(ServiceFacade serviceFacade) {
        this.serviceFacade = serviceFacade;
    }

    @GET
    @Path("/user/{userId}/task/{taskId}")
    @Produces("application/json")
    public Task getTask(@PathParam("userId") Integer userId, @PathParam("taskId") Integer taskId) {
        User user = null;
        Task task = null;
        try {
            user = serviceFacade.getUserById(userId);
            task = serviceFacade.getTaskById(taskId);
            if (serviceFacade.getAllTaskOfAUser(userId).contains(task)) return task;
        } catch (ServiceException exception) {
            throw new RestServiceException(format("User %d was not found!", userId));
        }
        return task;
    }

}
