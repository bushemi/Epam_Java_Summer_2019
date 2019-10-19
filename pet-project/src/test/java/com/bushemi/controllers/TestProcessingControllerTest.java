package com.bushemi.controllers;

import com.bushemi.AppConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TestProcessingControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testing() throws Exception {
        this.mockMvc.perform(get("/testing"))
                .andExpect(status().isOk())
                .andExpect(view().name("testing"));
    }

    @Test
    public void testInProcess() throws Exception {
        this.mockMvc.perform(get("/testInProcess"))
                .andExpect(view().name("testInProcess"));
    }

    @Test
    public void testResult() throws Exception {
        this.mockMvc.perform(get("/testResult"))
                .andExpect(view().name("testResult"));
    }
}