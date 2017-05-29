package com.epam.brest.jmp.web;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.epam.brest.jmp.model.exceptions.ServiceException;
import org.junit.Test;

/**
 * Unit tests for controllers with MockMVC
 * Created by alexander_borohov on 26.5.17.
 */
public class TaskControllerTest extends BaseWebAppTest {
    @Test
    public void getTaskTest_positiveResult() throws Exception {
        expect(mockService.getUserById(TEST_USER_ID)).andReturn(testUser);
        expect(mockService.getTaskById(TEST_TASK_ID)).andReturn(testTask);
        replay(mockService);
        mockMvc.perform(get("/user/" + TEST_USER_ID + "/task/" + TEST_TASK_ID)
                .with(admin()))
                .andDo(print())
                .andExpect(view().name("task"))
                .andExpect(model().attribute("user", testUser))
                .andExpect(model().attribute("task", testTask));
    }

    @Test
    public void getTaskTest_negativeResult() throws Exception {
        Throwable exception = new ServiceException(testUser, "Could not read");
        expect(mockService.getUserById(TEST_USER_ID)).andThrow(exception);
        replay(mockService);
        mockMvc.perform(get("/user/" + TEST_USER_ID + "/task/" + TEST_TASK_ID)
                .with(admin()))
                .andDo(print())
                .andExpect(view().name("error"))
                .andExpect(model().attribute("error", exception))
                .andExpect(content().string(containsString("Could not read")));
    }

    @Test
    public void getTaskTest_notAuthorized() throws Exception {
        replay(mockService);
        mockMvc.perform(get("/user/" + TEST_USER_ID + "/task/" + TEST_TASK_ID))
                .andDo(print())
                .andExpect(redirectedUrlPattern("http://*/login"));
    }
}