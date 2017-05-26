package com.epam.brest.jmp.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.epam.brest.jmp.web.config.RootContextConfig;
import com.epam.brest.jmp.web.config.WebContextConfig;
import com.epam.brest.jmp.web.config.WebSecurityConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by alexander_borohov on 26.5.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootContextConfig.class, WebContextConfig.class, WebSecurityConfig.class})
@WebAppConfiguration
@ActiveProfiles("hibernate")
public class HomeControllerTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void goHomeTest() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(view().name("home"));
    }

    @Test
    public void logoutTestFail() throws Exception {
        mockMvc.perform(get("/logout"))
                .andDo(print())
                .andExpect(redirectedUrl("/?logout=false"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
    public void logoutTestSuccess() throws Exception {
        mockMvc.perform(get("/logout").principal(() -> "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/"));
    }
}