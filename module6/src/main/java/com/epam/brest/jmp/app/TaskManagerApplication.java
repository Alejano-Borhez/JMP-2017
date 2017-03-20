package com.epam.brest.jmp.app;

import com.epam.brest.jmp.dao.DAO;
import com.epam.brest.jmp.dao.TaskDao;
import com.epam.brest.jmp.dao.impl.TaskInMemoryDao;
import com.epam.brest.jmp.dao.impl.UserInMemoryDao;
import com.epam.brest.jmp.model.Task;
import com.epam.brest.jmp.model.User;
import com.epam.brest.jmp.rest.RestController;
import com.epam.brest.jmp.service.ServiceFacade;
import com.epam.brest.jmp.service.ServiceImpl;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.moxy.xml.MoxyXmlFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by alexander_borohov on 9.2.17.
 */
public class TaskManagerApplication {
    public static void main(String[] args) throws Exception {
        ResourceConfig config = new ResourceConfig();
        setServletContextBeans(config);

        ServletHolder servlet = new ServletHolder(new ServletContainer(config));
        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(server, "/*");
        context.addServlet(servlet, "/services/*");

        String resourceBasePath = TaskManagerApplication.class.getResource("/WEB-INF").toExternalForm();
//        context.setWelcomeFiles(new String[]{"index.html"});
        context.setResourceBase(resourceBasePath);


//        context.addServlet(new ServletHolder(new DefaultServlet()), "/*");
        try {
            server.start();
            server.join();
        } finally {
            server.destroy();
        }
//        controller.runTaskManager();
    }

    private static void setServletContextBeans(ResourceConfig config) {
        TaskDao taskDao = new TaskInMemoryDao(new ArrayList<>());
        DAO<User, Integer> userDao = new UserInMemoryDao(new ArrayList<>());
        initUserStorage(userDao);
        initTaskStorage(taskDao, userDao.read(0));

        ServiceFacade serviceFacade = new ServiceImpl(taskDao, userDao);

        RestController controller = new RestController(serviceFacade);

        config.register(MoxyXmlFeature.class);
        config.register(JacksonFeature.class);

        config.registerInstances(controller);
    }

    private static void initUserStorage(DAO<User, Integer> userDao) {
        User userOne = new User("TestName", "Surname", "email");
        userDao.create(userOne);
    }

    private static void initTaskStorage(TaskDao taskDao, User owner) {
        Date date = Date.from(LocalDate.now().plusDays(2).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Task taskOne = new Task("Test1", "TestDesc1", date, owner.getId());
        Task taskTwo = new Task("Test2", "TestDesc2", date, owner.getId());
        Task taskThree = new Task("Test3", "TestDesc3", date, owner.getId());

        taskDao.create(taskOne);
        taskDao.create(taskTwo);
        taskDao.create(taskThree);
    }
}
