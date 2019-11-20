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


public class TestControllerIT extends BasicSpringTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void addTestShouldFail() throws Exception {
        this.mockMvc.perform(get("/addTest"))
                .andExpect(status().is(302));
    }

    @Test
    @Ignore
    public void editTestShouldFail() throws Exception {
        this.mockMvc.perform(get("/editTest"))
                .andExpect(status().is(302));
    }

    @Test
    public void testsShouldFail() throws Exception {
        this.mockMvc.perform(get("/tests"))
                .andExpect(status().is(302));
    }

    @Test
    public void addTestStudentShouldFail() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "student");
        this.mockMvc.perform(get("/addTest").session(session))
                .andExpect(status().is(302));
    }


    @Test
    @Ignore
    public void editTestStudentShouldFail() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "student");
        this.mockMvc.perform(get("/editTest").session(session))
                .andExpect(status().is(302));
    }

    @Test
    @Ignore
    public void testsStudent() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "student");
        this.mockMvc.perform(get("/tests").session(session))
                .andExpect(view().name("tests"));
    }

    @Test
    @Ignore
    public void addTestAdmin() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "admin");
        this.mockMvc.perform(get("/addTest").session(session))
                .andExpect(view().name("addTest"));
    }

    @Test
    @Ignore
    public void editTestAdmin() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "admin");
        this.mockMvc.perform(get("/editTest").session(session))
                .andExpect(view().name("editTest"));
    }

    @Test
    @Ignore
    public void testsAdmin() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "admin");
        this.mockMvc.perform(get("/tests").session(session))
                .andExpect(view().name("tests"));
    }
}