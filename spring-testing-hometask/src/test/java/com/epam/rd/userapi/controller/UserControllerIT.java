package com.epam.rd.userapi.controller;

import com.epam.rd.userapi.BasicSpringTest;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class UserControllerIT extends BasicSpringTest {

    @Test
    public void getUsers() throws Exception {
        //GIVEN

        //WHEN
        mockMvc.perform(get("/users")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$[0].email", is("Sincere@april.biz")))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].username", is("Bret")));

        //THEN

    }
}