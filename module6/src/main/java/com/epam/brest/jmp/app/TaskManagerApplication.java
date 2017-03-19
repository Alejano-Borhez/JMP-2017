package com.epam.brest.jmp.app;

import com.epam.brest.jmp.controller.TaskController;
import com.epam.brest.jmp.controller.TaskControllerConsoleImpl;
import com.epam.brest.jmp.dao.DAO;
import com.epam.brest.jmp.dao.TaskDao;
import com.epam.brest.jmp.dao.impl.TaskInMemoryDao;
import com.epam.brest.jmp.dao.impl.UserInMemoryDao;
import com.epam.brest.jmp.model.User;
import com.epam.brest.jmp.service.ServiceImpl;
import com.epam.brest.jmp.service.TaskService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.moxy.xml.MoxyXmlFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import java.util.ArrayList;

/**
 * Created by alexander_borohov on 9.2.17.
 */
public class TaskManagerApplication {
    public static void main(String[] args) throws Exception {
//        Providing dao's CSV file implementation
        TaskDao dao = new TaskInMemoryDao(new ArrayList<>());
        DAO<User, Integer> userDao = new UserInMemoryDao(new ArrayList<>());
//        Providing service's basic implementation
        TaskService service = new ServiceImpl(dao, userDao);
//        Providing controller's Console implementation
        TaskController controller = new TaskControllerConsoleImpl(service);
//        Running an application
        ResourceConfig config = new ResourceConfig();
        config.register(MoxyXmlFeature.class);
        config.register(JacksonFeature.class);
        config.packages("com.epam.brest.jmp.rest");
        ServletHolder servlet = new ServletHolder(new ServletContainer(config));
        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(server, "/*");
        context.addServlet(servlet, "/services/*");
        String resourceBasePath = TaskManagerApplication.class.getResource("/WEB-INF").toExternalForm();
        context.setWelcomeFiles(new String[]{"index.html"});
        context.setResourceBase(resourceBasePath);
        context.addServlet(new ServletHolder(new DefaultServlet()), "/*");
        try {
            server.start();
            server.join();
        } finally {
            server.destroy();
        }
//        controller.runTaskManager();
    }
}
