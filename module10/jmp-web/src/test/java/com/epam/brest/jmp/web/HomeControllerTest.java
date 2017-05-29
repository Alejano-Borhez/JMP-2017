package com.epam.brest.jmp.web;

import static org.easymock.EasyMock.replay;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

/**
 * Unit tests for controllers with MockMVC
 * Created by alexander_borohov on 26.5.17.
 */
public class HomeControllerTest extends BaseWebAppTest {

    @Test
    public void goHomeTest() throws Exception {
        replay(mockService);
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(view().name("home"));
    }

    @Test
    public void logoutTestFail() throws Exception {
        replay(mockService);
        mockMvc.perform(get("/logout"))
                .andDo(print())
                .andExpect(redirectedUrlPattern("http://*/login"));
    }

    @Test
    public void logoutTestSuccess() throws Exception {
        replay(mockService);
        mockMvc.perform(get("/logout").with(admin()))
                .andDo(print())
                .andExpect(redirectedUrl("/"));
    }
}