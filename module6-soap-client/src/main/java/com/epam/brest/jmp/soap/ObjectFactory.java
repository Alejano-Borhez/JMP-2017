
package com.epam.brest.jmp.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.epam.brest.jmp.soap package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetTaskOwner_QNAME = new QName("http://soap.jmp.brest.epam.com/", "getTaskOwner");
    private final static QName _UpdateUser_QNAME = new QName("http://soap.jmp.brest.epam.com/", "updateUser");
    private final static QName _UpdateTaskResponse_QNAME = new QName("http://soap.jmp.brest.epam.com/", "updateTaskResponse");
    private final static QName _CreateUserResponse_QNAME = new QName("http://soap.jmp.brest.epam.com/", "createUserResponse");
    private final static QName _GetAllUsers_QNAME = new QName("http://soap.jmp.brest.epam.com/", "getAllUsers");
    private final static QName _GettAllTasksOfAUserResponse_QNAME = new QName("http://soap.jmp.brest.epam.com/", "gettAllTasksOfAUserResponse");
    private final static QName _DeleteUserResponse_QNAME = new QName("http://soap.jmp.brest.epam.com/", "deleteUserResponse");
    private final static QName _GetTask_QNAME = new QName("http://soap.jmp.brest.epam.com/", "getTask");
    private final static QName _CreateTask_QNAME = new QName("http://soap.jmp.brest.epam.com/", "createTask");
    private final static QName _DeleteTask_QNAME = new QName("http://soap.jmp.brest.epam.com/", "deleteTask");
    private final static QName _CreateUser_QNAME = new QName("http://soap.jmp.brest.epam.com/", "createUser");
    private final static QName _DeleteAllUsersResponse_QNAME = new QName("http://soap.jmp.brest.epam.com/", "deleteAllUsersResponse");
    private final static QName _DeleteUser_QNAME = new QName("http://soap.jmp.brest.epam.com/", "deleteUser");
    private final static QName _DeleteAllUsers_QNAME = new QName("http://soap.jmp.brest.epam.com/", "deleteAllUsers");
    private final static QName _DeleteAllTasksResponse_QNAME = new QName("http://soap.jmp.brest.epam.com/", "deleteAllTasksResponse");
    private final static QName _GetAllUsersResponse_QNAME = new QName("http://soap.jmp.brest.epam.com/", "getAllUsersResponse");
    private final static QName _UpdateTask_QNAME = new QName("http://soap.jmp.brest.epam.com/", "updateTask");
    private final static QName _GetAllTasksResponse_QNAME = new QName("http://soap.jmp.brest.epam.com/", "getAllTasksResponse");
    private final static QName _CreateTaskResponse_QNAME = new QName("http://soap.jmp.brest.epam.com/", "createTaskResponse");
    private final static QName _GettAllTasksOfAUser_QNAME = new QName("http://soap.jmp.brest.epam.com/", "gettAllTasksOfAUser");
    private final static QName _GetUser_QNAME = new QName("http://soap.jmp.brest.epam.com/", "getUser");
    private final static QName _GetAllTasks_QNAME = new QName("http://soap.jmp.brest.epam.com/", "getAllTasks");
    private final static QName _GetUserResponse_QNAME = new QName("http://soap.jmp.brest.epam.com/", "getUserResponse");
    private final static QName _GetTaskResponse_QNAME = new QName("http://soap.jmp.brest.epam.com/", "getTaskResponse");
    private final static QName _UpdateUserResponse_QNAME = new QName("http://soap.jmp.brest.epam.com/", "updateUserResponse");
    private final static QName _GetTaskOwnerResponse_QNAME = new QName("http://soap.jmp.brest.epam.com/", "getTaskOwnerResponse");
    private final static QName _DeleteTaskResponse_QNAME = new QName("http://soap.jmp.brest.epam.com/", "deleteTaskResponse");
    private final static QName _DeleteAllTasks_QNAME = new QName("http://soap.jmp.brest.epam.com/", "deleteAllTasks");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.epam.brest.jmp.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetUserResponse }
     * 
     */
    public GetUserResponse createGetUserResponse() {
        return new GetUserResponse();
    }

    /**
     * Create an instance of {@link GetAllTasks }
     * 
     */
    public GetAllTasks createGetAllTasks() {
        return new GetAllTasks();
    }

    /**
     * Create an instance of {@link GetUser }
     * 
     */
    public GetUser createGetUser() {
        return new GetUser();
    }

    /**
     * Create an instance of {@link GettAllTasksOfAUser }
     * 
     */
    public GettAllTasksOfAUser createGettAllTasksOfAUser() {
        return new GettAllTasksOfAUser();
    }

    /**
     * Create an instance of {@link CreateTaskResponse }
     * 
     */
    public CreateTaskResponse createCreateTaskResponse() {
        return new CreateTaskResponse();
    }

    /**
     * Create an instance of {@link GetAllTasksResponse }
     * 
     */
    public GetAllTasksResponse createGetAllTasksResponse() {
        return new GetAllTasksResponse();
    }

    /**
     * Create an instance of {@link DeleteAllTasks }
     * 
     */
    public DeleteAllTasks createDeleteAllTasks() {
        return new DeleteAllTasks();
    }

    /**
     * Create an instance of {@link DeleteTaskResponse }
     * 
     */
    public DeleteTaskResponse createDeleteTaskResponse() {
        return new DeleteTaskResponse();
    }

    /**
     * Create an instance of {@link GetTaskOwnerResponse }
     * 
     */
    public GetTaskOwnerResponse createGetTaskOwnerResponse() {
        return new GetTaskOwnerResponse();
    }

    /**
     * Create an instance of {@link UpdateUserResponse }
     * 
     */
    public UpdateUserResponse createUpdateUserResponse() {
        return new UpdateUserResponse();
    }

    /**
     * Create an instance of {@link GetTaskResponse }
     * 
     */
    public GetTaskResponse createGetTaskResponse() {
        return new GetTaskResponse();
    }

    /**
     * Create an instance of {@link GettAllTasksOfAUserResponse }
     * 
     */
    public GettAllTasksOfAUserResponse createGettAllTasksOfAUserResponse() {
        return new GettAllTasksOfAUserResponse();
    }

    /**
     * Create an instance of {@link GetAllUsers }
     * 
     */
    public GetAllUsers createGetAllUsers() {
        return new GetAllUsers();
    }

    /**
     * Create an instance of {@link CreateUserResponse }
     * 
     */
    public CreateUserResponse createCreateUserResponse() {
        return new CreateUserResponse();
    }

    /**
     * Create an instance of {@link UpdateTaskResponse }
     * 
     */
    public UpdateTaskResponse createUpdateTaskResponse() {
        return new UpdateTaskResponse();
    }

    /**
     * Create an instance of {@link UpdateUser }
     * 
     */
    public UpdateUser createUpdateUser() {
        return new UpdateUser();
    }

    /**
     * Create an instance of {@link GetTaskOwner }
     * 
     */
    public GetTaskOwner createGetTaskOwner() {
        return new GetTaskOwner();
    }

    /**
     * Create an instance of {@link GetAllUsersResponse }
     * 
     */
    public GetAllUsersResponse createGetAllUsersResponse() {
        return new GetAllUsersResponse();
    }

    /**
     * Create an instance of {@link UpdateTask }
     * 
     */
    public UpdateTask createUpdateTask() {
        return new UpdateTask();
    }

    /**
     * Create an instance of {@link DeleteAllTasksResponse }
     * 
     */
    public DeleteAllTasksResponse createDeleteAllTasksResponse() {
        return new DeleteAllTasksResponse();
    }

    /**
     * Create an instance of {@link DeleteAllUsers }
     * 
     */
    public DeleteAllUsers createDeleteAllUsers() {
        return new DeleteAllUsers();
    }

    /**
     * Create an instance of {@link DeleteAllUsersResponse }
     * 
     */
    public DeleteAllUsersResponse createDeleteAllUsersResponse() {
        return new DeleteAllUsersResponse();
    }

    /**
     * Create an instance of {@link DeleteUser }
     * 
     */
    public DeleteUser createDeleteUser() {
        return new DeleteUser();
    }

    /**
     * Create an instance of {@link CreateUser }
     * 
     */
    public CreateUser createCreateUser() {
        return new CreateUser();
    }

    /**
     * Create an instance of {@link CreateTask }
     * 
     */
    public CreateTask createCreateTask() {
        return new CreateTask();
    }

    /**
     * Create an instance of {@link DeleteTask }
     * 
     */
    public DeleteTask createDeleteTask() {
        return new DeleteTask();
    }

    /**
     * Create an instance of {@link DeleteUserResponse }
     * 
     */
    public DeleteUserResponse createDeleteUserResponse() {
        return new DeleteUserResponse();
    }

    /**
     * Create an instance of {@link GetTask }
     * 
     */
    public GetTask createGetTask() {
        return new GetTask();
    }

    /**
     * Create an instance of {@link Task }
     * 
     */
    public Task createTask() {
        return new Task();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTaskOwner }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.jmp.brest.epam.com/", name = "getTaskOwner")
    public JAXBElement<GetTaskOwner> createGetTaskOwner(GetTaskOwner value) {
        return new JAXBElement<GetTaskOwner>(_GetTaskOwner_QNAME, GetTaskOwner.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.jmp.brest.epam.com/", name = "updateUser")
    public JAXBElement<UpdateUser> createUpdateUser(UpdateUser value) {
        return new JAXBElement<UpdateUser>(_UpdateUser_QNAME, UpdateUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateTaskResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.jmp.brest.epam.com/", name = "updateTaskResponse")
    public JAXBElement<UpdateTaskResponse> createUpdateTaskResponse(UpdateTaskResponse value) {
        return new JAXBElement<UpdateTaskResponse>(_UpdateTaskResponse_QNAME, UpdateTaskResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.jmp.brest.epam.com/", name = "createUserResponse")
    public JAXBElement<CreateUserResponse> createCreateUserResponse(CreateUserResponse value) {
        return new JAXBElement<CreateUserResponse>(_CreateUserResponse_QNAME, CreateUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllUsers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.jmp.brest.epam.com/", name = "getAllUsers")
    public JAXBElement<GetAllUsers> createGetAllUsers(GetAllUsers value) {
        return new JAXBElement<GetAllUsers>(_GetAllUsers_QNAME, GetAllUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GettAllTasksOfAUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.jmp.brest.epam.com/", name = "gettAllTasksOfAUserResponse")
    public JAXBElement<GettAllTasksOfAUserResponse> createGettAllTasksOfAUserResponse(GettAllTasksOfAUserResponse value) {
        return new JAXBElement<GettAllTasksOfAUserResponse>(_GettAllTasksOfAUserResponse_QNAME, GettAllTasksOfAUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.jmp.brest.epam.com/", name = "deleteUserResponse")
    public JAXBElement<DeleteUserResponse> createDeleteUserResponse(DeleteUserResponse value) {
        return new JAXBElement<DeleteUserResponse>(_DeleteUserResponse_QNAME, DeleteUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTask }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.jmp.brest.epam.com/", name = "getTask")
    public JAXBElement<GetTask> createGetTask(GetTask value) {
        return new JAXBElement<GetTask>(_GetTask_QNAME, GetTask.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateTask }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.jmp.brest.epam.com/", name = "createTask")
    public JAXBElement<CreateTask> createCreateTask(CreateTask value) {
        return new JAXBElement<CreateTask>(_CreateTask_QNAME, CreateTask.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteTask }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.jmp.brest.epam.com/", name = "deleteTask")
    public JAXBElement<DeleteTask> createDeleteTask(DeleteTask value) {
        return new JAXBElement<DeleteTask>(_DeleteTask_QNAME, DeleteTask.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.jmp.brest.epam.com/", name = "createUser")
    public JAXBElement<CreateUser> createCreateUser(CreateUser value) {
        return new JAXBElement<CreateUser>(_CreateUser_QNAME, CreateUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteAllUsersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.jmp.brest.epam.com/", name = "deleteAllUsersResponse")
    public JAXBElement<DeleteAllUsersResponse> createDeleteAllUsersResponse(DeleteAllUsersResponse value) {
        return new JAXBElement<DeleteAllUsersResponse>(_DeleteAllUsersResponse_QNAME, DeleteAllUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.jmp.brest.epam.com/", name = "deleteUser")
    public JAXBElement<DeleteUser> createDeleteUser(DeleteUser value) {
        return new JAXBElement<DeleteUser>(_DeleteUser_QNAME, DeleteUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteAllUsers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.jmp.brest.epam.com/", name = "deleteAllUsers")
    public JAXBElement<DeleteAllUsers> createDeleteAllUsers(DeleteAllUsers value) {
        return new JAXBElement<DeleteAllUsers>(_DeleteAllUsers_QNAME, DeleteAllUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteAllTasksResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.jmp.brest.epam.com/", name = "deleteAllTasksResponse")
    public JAXBElement<DeleteAllTasksResponse> createDeleteAllTasksResponse(DeleteAllTasksResponse value) {
        return new JAXBElement<DeleteAllTasksResponse>(_DeleteAllTasksResponse_QNAME, DeleteAllTasksResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllUsersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.jmp.brest.epam.com/", name = "getAllUsersResponse")
    public JAXBElement<GetAllUsersResponse> createGetAllUsersResponse(GetAllUsersResponse value) {
        return new JAXBElement<GetAllUsersResponse>(_GetAllUsersResponse_QNAME, GetAllUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateTask }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.jmp.brest.epam.com/", name = "updateTask")
    public JAXBElement<UpdateTask> createUpdateTask(UpdateTask value) {
        return new JAXBElement<UpdateTask>(_UpdateTask_QNAME, UpdateTask.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllTasksResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.jmp.brest.epam.com/", name = "getAllTasksResponse")
    public JAXBElement<GetAllTasksResponse> createGetAllTasksResponse(GetAllTasksResponse value) {
        return new JAXBElement<GetAllTasksResponse>(_GetAllTasksResponse_QNAME, GetAllTasksResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateTaskResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.jmp.brest.epam.com/", name = "createTaskResponse")
    public JAXBElement<CreateTaskResponse> createCreateTaskResponse(CreateTaskResponse value) {
        return new JAXBElement<CreateTaskResponse>(_CreateTaskResponse_QNAME, CreateTaskResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GettAllTasksOfAUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.jmp.brest.epam.com/", name = "gettAllTasksOfAUser")
    public JAXBElement<GettAllTasksOfAUser> createGettAllTasksOfAUser(GettAllTasksOfAUser value) {
        return new JAXBElement<GettAllTasksOfAUser>(_GettAllTasksOfAUser_QNAME, GettAllTasksOfAUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.jmp.brest.epam.com/", name = "getUser")
    public JAXBElement<GetUser> createGetUser(GetUser value) {
        return new JAXBElement<GetUser>(_GetUser_QNAME, GetUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllTasks }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.jmp.brest.epam.com/", name = "getAllTasks")
    public JAXBElement<GetAllTasks> createGetAllTasks(GetAllTasks value) {
        return new JAXBElement<GetAllTasks>(_GetAllTasks_QNAME, GetAllTasks.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.jmp.brest.epam.com/", name = "getUserResponse")
    public JAXBElement<GetUserResponse> createGetUserResponse(GetUserResponse value) {
        return new JAXBElement<GetUserResponse>(_GetUserResponse_QNAME, GetUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTaskResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.jmp.brest.epam.com/", name = "getTaskResponse")
    public JAXBElement<GetTaskResponse> createGetTaskResponse(GetTaskResponse value) {
        return new JAXBElement<GetTaskResponse>(_GetTaskResponse_QNAME, GetTaskResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.jmp.brest.epam.com/", name = "updateUserResponse")
    public JAXBElement<UpdateUserResponse> createUpdateUserResponse(UpdateUserResponse value) {
        return new JAXBElement<UpdateUserResponse>(_UpdateUserResponse_QNAME, UpdateUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTaskOwnerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.jmp.brest.epam.com/", name = "getTaskOwnerResponse")
    public JAXBElement<GetTaskOwnerResponse> createGetTaskOwnerResponse(GetTaskOwnerResponse value) {
        return new JAXBElement<GetTaskOwnerResponse>(_GetTaskOwnerResponse_QNAME, GetTaskOwnerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteTaskResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.jmp.brest.epam.com/", name = "deleteTaskResponse")
    public JAXBElement<DeleteTaskResponse> createDeleteTaskResponse(DeleteTaskResponse value) {
        return new JAXBElement<DeleteTaskResponse>(_DeleteTaskResponse_QNAME, DeleteTaskResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteAllTasks }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.jmp.brest.epam.com/", name = "deleteAllTasks")
    public JAXBElement<DeleteAllTasks> createDeleteAllTasks(DeleteAllTasks value) {
        return new JAXBElement<DeleteAllTasks>(_DeleteAllTasks_QNAME, DeleteAllTasks.class, null, value);
    }

}
