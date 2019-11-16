package com.epam.rd.userapi.service;

import com.epam.rd.userapi.pojo.UserResponse;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private final RestTemplate template = mock(RestTemplate.class);
    private final UserService service = new UserService(template);
    private final ResponseEntity<List<UserResponse>> userResponse = mock(ResponseEntity.class);

    @Test
    public void getAllUsers() {
        //GIVEN
        when(template.exchange(any(), any(), any(),
                eq(new ParameterizedTypeReference<List<UserResponse>>() {
                })))
                .thenReturn(userResponse);
        UserResponse userResponse1 = new UserResponse();
        userResponse1.setName("first");
        userResponse1.setEmail("first@gmail.com");
        UserResponse userResponse2 = new UserResponse();
        userResponse2.setName("second");
        userResponse2.setEmail("second@gmail.com");

        List<UserResponse> responses = Arrays.asList(userResponse1, userResponse2);
        when(userResponse.getBody()).thenReturn(responses);

        //WHEN
        Collection<UserResponse> allUsers = service.getAllUsers();

        //THEN
        assertThat(allUsers, hasSize(2));
        assertThat(allUsers, equalTo(responses));
    }
}