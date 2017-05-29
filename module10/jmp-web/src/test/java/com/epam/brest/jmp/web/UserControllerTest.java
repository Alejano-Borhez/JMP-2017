package com.epam.brest.jmp.web;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.epam.brest.jmp.model.User;
import org.junit.Test;

/**
 * Unit tests for controllers with MockMVC
 * Created by alexander_borohov on 29.5.17.
 */
public class UserControllerTest extends BaseWebAppTest {
    @Test
    public void getUsersTest() throws Exception {
        expect(mockService.showAllUsers()).andReturn(users);
        replay(mockService);
        mockMvc.perform(get("/users").with(admin()))
                .andDo(print())
                .andExpect(view().name("users"))
                .andExpect(model().attribute("users", users));
    }

    @Test
    public void getUserTest() throws Exception {
        expect(mockService.getUserById(TEST_USER_ID)).andReturn(testUser);
        expect(mockService.getAllTaskOfAUser(TEST_USER_ID)).andReturn(tasks);
        replay(mockService);
        mockMvc.perform(get("/user/" + TEST_USER_ID).with(admin()))
                .andDo(print())
                .andExpect(view().name("user"))
                .andExpect(model().attribute("user", testUser))
                .andExpect(model().attribute("tasks", tasks));
    }

    @Test
    public void createUserPageTest() throws Exception {
        replay(mockService);
        mockMvc.perform(get("/user/new").with(admin()))
                .andDo(print())
                .andExpect(model().attribute("user", new User()))
                .andExpect(view().name("user_new"));
    }

}
