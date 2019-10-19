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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
public class DefaultControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void index() throws Exception {
        this.mockMvc.perform(get("/index"))
                .andExpect(view().name("index"));
    }

    @Test
    public void pageNotFound() throws Exception {
        this.mockMvc.perform(get("/404"))
                .andExpect(view().name("404"));
    }

    @Test
    public void navigationStudent() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "student");
        this.mockMvc.perform(get("/navigation").session(session))
                .andDo(print())
                .andExpect(view().name("navigation"));
    }

    @Test
    public void navigationAdmin() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "admin");
        this.mockMvc.perform(get("/navigation").session(session))
                .andDo(print())
                .andExpect(view().name("navigation"));
    }

    @Test
    public void login() throws Exception {
        this.mockMvc.perform(get("/login"))
                .andDo(print())
                .andExpect(view().name("login"));
    }
}