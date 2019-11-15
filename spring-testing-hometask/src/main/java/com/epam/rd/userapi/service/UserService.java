package com.epam.rd.userapi.service;

import com.epam.rd.userapi.pojo.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class UserService {

    private static final String USER_PATH = "/users";

    @Value("${user.service.baseUrl}")
    private String serviceBaseUrl;

    @Autowired
    private RestTemplate restTemplate;

    public Collection<UserResponse> getAllUsers() {
        URI serviceUri = URI.create(serviceBaseUrl + USER_PATH);

        try {
            ResponseEntity<List<UserResponse>> userResponse =
                    restTemplate.exchange(serviceUri, HttpMethod.GET, null,
                            new ParameterizedTypeReference<List<UserResponse>>() {});

            return userResponse.getBody();
        } catch (RestClientException exc) {
            log.error("Error occurred while fetching user's data", exc);
        }

        return Collections.emptyList();
    }
}
