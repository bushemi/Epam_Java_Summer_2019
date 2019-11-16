package com.epam.rd.userapi.controller;

import com.epam.rd.userapi.pojo.UserResponse;
import com.epam.rd.userapi.service.UserService;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {
    private final UserService service = mock(UserService.class);
    private final UserController userController = new UserController(service);

    private MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();


    @Test
    public void getUsers() throws Exception {
        //GIVEN
        UserResponse userResponse = new UserResponse();
        userResponse.setName("first");
        userResponse.setEmail("first@gmail.com");
        UserResponse.Address address1 = new UserResponse.Address();
        address1.setCity("Dnipro");
        address1.setStreet("first");
        address1.setZipCode("49000");
        address1.setSuite("first");
        userResponse.setAddress(address1);
        UserResponse userResponse2 = new UserResponse();
        userResponse2.setName("second");
        userResponse2.setEmail("second@gmail.com");
        UserResponse.Address address2 = new UserResponse.Address();
        address2.setCity("Dnipro");
        address2.setStreet("second");
        address2.setZipCode("49002");
        address2.setSuite("second");
        userResponse2.setAddress(address2);
        List<UserResponse> responses = asList(userResponse, userResponse2);
        when(service.getAllUsers()).thenReturn(responses);

        //WHEN
        mockMvc.perform(get("/users")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].email", is(responses.get(0).getEmail())))
                .andExpect(jsonPath("$[1].email", is(responses.get(1).getEmail())))
                .andExpect(jsonPath("$[0].name", is(responses.get(0).getName())))
                .andExpect(jsonPath("$[1].name", is(responses.get(1).getName())))
                .andExpect(jsonPath("$[0].address.street", is(responses.get(0).getAddress().getStreet())))
                .andExpect(jsonPath("$[1].address.street", is(responses.get(1).getAddress().getStreet())));

        //THEN
        verify(service).getAllUsers();
    }


}