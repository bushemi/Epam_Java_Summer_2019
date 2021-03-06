package com.bushemi.controllers.pages;

import com.bushemi.BasicSpringTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class QuestionControllerIT extends BasicSpringTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void questionAdmin() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "admin");
        this.mockMvc.perform(get("/question").session(session))
                .andExpect(view().name("question"));
    }

    @Test
    public void questionStudentShouldFail() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "student");
        this.mockMvc.perform(get("/question").session(session))
                .andExpect(status().is(302));
    }

    @Test
    public void questionShouldFail() throws Exception {
        this.mockMvc.perform(get("/question"))
                .andExpect(status().is(302));
    }
}