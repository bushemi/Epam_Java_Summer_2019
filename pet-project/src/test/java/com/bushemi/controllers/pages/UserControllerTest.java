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
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void userProfileShouldFail() throws Exception {
        this.mockMvc.perform(get("/userProfile"))
                .andExpect(status().is(302));
    }

    @Test
    public void allUsersShouldFail() throws Exception {
        this.mockMvc.perform(get("/allUsers"))
                .andExpect(status().is(302));
    }

    @Test
    public void userProfileStudent() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "student");
        this.mockMvc.perform(get("/userProfile").session(session))
                .andExpect(view().name("userProfile"));
    }

    @Test
    public void allUsersStudentShouldFail() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "student");
        this.mockMvc.perform(get("/allUsers").session(session))
                .andExpect(status().is(302));
    }

    @Test
    public void userProfileAdmin() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "admin");
        this.mockMvc.perform(get("/userProfile").session(session))
                .andExpect(view().name("userProfile"));
    }

    @Test
    public void allUsersAdmin() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "admin");
        this.mockMvc.perform(get("/allUsers").session(session))
                .andExpect(view().name("allUsers"));
    }
}