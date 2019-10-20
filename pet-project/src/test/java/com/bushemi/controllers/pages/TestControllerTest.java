package com.bushemi.controllers.pages;

import com.bushemi.AppConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
public class TestControllerTest {

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
    public void editTestStudentShouldFail() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "student");
        this.mockMvc.perform(get("/editTest").session(session))
                .andExpect(status().is(302));
    }

    @Test
    public void testsStudent() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "student");
        this.mockMvc.perform(get("/tests").session(session))
                .andExpect(view().name("tests"));
    }

    @Test
    public void addTestAdmin() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "admin");
        this.mockMvc.perform(get("/addTest").session(session))
                .andExpect(view().name("addTest"));
    }

    @Test
    public void editTestAdmin() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "admin");
        this.mockMvc.perform(get("/editTest").session(session))
                .andExpect(view().name("editTest"));
    }

    @Test
    public void testsAdmin() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "admin");
        this.mockMvc.perform(get("/tests").session(session))
                .andExpect(view().name("tests"));
    }
}