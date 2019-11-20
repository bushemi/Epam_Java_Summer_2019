package com.bushemi.controllers.pages;

import com.bushemi.BasicSpringTest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


public class TestProcessingControllerTest extends BasicSpringTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    @Ignore
    public void testingShouldFail() throws Exception {
        this.mockMvc.perform(get("/testing"))
                .andExpect(status().is(404));
    }

    @Test
    public void testInProcessShouldFail() throws Exception {
        this.mockMvc.perform(get("/testInProcess"))
                .andExpect(status().is(302));
    }

    @Test
    public void testResultShouldFail() throws Exception {
        this.mockMvc.perform(get("/testResult"))
                .andExpect(status().is(302));
    }

    @Test
    @Ignore
    public void testingStudent() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "student");
        this.mockMvc.perform(get("/testing").session(session))
                .andExpect(view().name("testing"));
    }

    @Test
    @Ignore
    public void testInProcessStudent() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "student");
        this.mockMvc.perform(get("/testInProcess").session(session))
                .andExpect(view().name("testInProcess"));
    }

    @Test
    public void testResultStudent() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "student");
        this.mockMvc.perform(get("/testResult").session(session))
                .andExpect(view().name("testResult"));
    }

    @Test
    @Ignore
    public void testingAdmin() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "admin");
        this.mockMvc.perform(get("/testing").session(session))
                .andExpect(view().name("testing"));
    }

    @Test
    @Ignore
    public void testInProcessAdmin() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "admin");
        this.mockMvc.perform(get("/testInProcess").session(session))
                .andExpect(view().name("testInProcess"));
    }

    @Test
    public void testResultAdmin() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "admin");
        this.mockMvc.perform(get("/testResult").session(session))
                .andExpect(view().name("testResult"));
    }
}