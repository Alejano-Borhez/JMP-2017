package com.epam.brest.jmp.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static java.lang.String.format;

import com.epam.brest.jmp.model.Task;
import com.epam.brest.jmp.model.User;
import com.epam.brest.jmp.model.exceptions.DaoException;
import com.epam.brest.jmp.model.exceptions.RestServiceException;
import com.epam.brest.jmp.model.exceptions.ServiceException;
import com.epam.brest.jmp.service.ServiceFacade;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

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
    @Produces(APPLICATION_JSON)
    public Response getTask(@PathParam("userId") Integer userId, @PathParam("taskId") Integer taskId) {
        User user = null;
        Task task = null;
        try {
            user = serviceFacade.getUserById(userId);
        } catch (ServiceException | DaoException exception) {
            return Response.status(BAD_REQUEST)
                    .entity(new RestServiceException(format("User %d was not found!", userId)))
                    .build();
        }
        try {
            task = serviceFacade.getTaskById(taskId);
        } catch (ServiceException | DaoException exception) {
            return Response.status(BAD_REQUEST)
                    .entity(new RestServiceException(format("Task %d was not found!", userId)))
                    .build();
        }
        return Response.ok().entity(task).build();
    }

    @POST
    @Path("/user/{userId}/task")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Response createTask(@PathParam("userId") Integer userId,
                               Task task) {
        User user = null;
        Integer id = null;
        try {
            user = serviceFacade.getUserById(userId);
        } catch (ServiceException e) {
            return Response.status(BAD_REQUEST)
                    .entity(new RestServiceException(format("User %d was not found!", userId)))
                    .build();
        }

        try {
            id = serviceFacade.addNewTask(task);
        } catch (ServiceException e) {
            return Response.status(BAD_REQUEST)
                    .entity(new RestServiceException(format("Incorrect task data provided! %s", task)))
                    .build();
        }
        return Response.ok().entity(serviceFacade.getTaskById(id)).build();
    }

    @PUT
    @Path("/user/{userId}/task/{taskId}")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Response updateTask(@PathParam("userId") Integer userId,
                               @PathParam("taskId") Integer taskId,
                               Task task) {
        User user = null;
        Task updated = null;
        try {
            user = serviceFacade.getUserById(userId);
        } catch (ServiceException e) {
            return Response.status(BAD_REQUEST)
                    .entity(new RestServiceException(format("User %d was not found!", userId)))
                    .build();
        }

        try {
            updated = serviceFacade.updateTask(task);
        } catch (ServiceException e) {
            return Response.status(BAD_REQUEST)
                    .entity(new RestServiceException(format("Incorrect task data provided! %s", task)))
                    .build();
        }
        return Response.ok().entity(updated).build();
    }

    @DELETE
    @Path("/user/{userId}/task/{taskId}")
    @Produces(APPLICATION_JSON)
    public Response deleteTask(@PathParam("userId") Integer userId,
                               @PathParam("taskId") Integer taskId) {
        User user = null;
        Boolean deleted = false;
        try {
            user = serviceFacade.getUserById(userId);
        } catch (ServiceException e) {
            return Response.status(BAD_REQUEST)
                    .entity(new RestServiceException(format("User %d was not found!", userId)))
                    .build();
        }

        try {
            deleted = serviceFacade.removeSpecificTask(taskId);
        } catch (ServiceException e) {
            return Response.status(BAD_REQUEST)
                    .entity(new RestServiceException(format("Incorrect task data provided! %s", taskId)))
                    .build();
        }
        return Response.accepted().entity(deleted).build();
    }

}
