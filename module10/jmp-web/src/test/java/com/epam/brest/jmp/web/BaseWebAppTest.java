package com.epam.brest.jmp.web;

import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import com.epam.brest.jmp.model.Task;
import com.epam.brest.jmp.model.User;
import com.epam.brest.jmp.service.ServiceFacade;
import com.epam.brest.jmp.web.config.RootContextConfig;
import com.epam.brest.jmp.web.config.WebAppMockBeansConfig;
import com.epam.brest.jmp.web.config.WebContextConfig;
import com.epam.brest.jmp.web.config.WebSecurityConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import javax.servlet.Filter;

/**
 * Common test methods are gathered here
 * Created by alexander_borohov on 29.5.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        RootContextConfig.class,
        WebContextConfig.class,
        WebSecurityConfig.class,
        WebAppMockBeansConfig.class
})
@WebAppConfiguration
@ActiveProfiles("mock")
public abstract class BaseWebAppTest {
    protected static final Integer TEST_USER_ID = 1;
    protected static final Integer TEST_TASK_ID = 1;
    protected static User testUser;
    protected static Task testTask;
    private static final String TEST_USER_NAME = "test username";
    private static final String TEST_USER_SURNAME = "test surname";
    private static final String TEST_USER_EMAIL = "test email";
    private final String TEST_TASK_DESC_FIRST = "test task 1 description";

    @Autowired
    private Filter springSecurityFilterChain;
    @Autowired
    protected List<Task> tasks;
    @Autowired
    protected List<User> users;

    @Autowired
    private WebApplicationContext context;
    @Autowired
    protected ServiceFacade mockService;

    protected MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        testUser = new User(TEST_USER_NAME, TEST_USER_SURNAME, TEST_USER_EMAIL);
        testUser.setId(TEST_USER_ID);
        testUser.setUserTasks(tasks);
        testTask = new Task(TEST_TASK_DESC_FIRST, TEST_USER_ID);
        testTask.setUser(testUser);

        mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilters(springSecurityFilterChain).build();
    }

    @After
    public void tearDown() throws Exception {
        verify(mockService);
        reset(mockService);
    }

    public static RequestPostProcessor admin() {
        return user("admin").roles("USER","ADMIN");
    }

    public static RequestPostProcessor notAllowedUser() {
        return user("user").roles("CUSTOMER");
    }


}
