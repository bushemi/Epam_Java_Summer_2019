package com.epam.rd.userapi.controller;

import com.epam.rd.userapi.pojo.UserResponse;
import com.epam.rd.userapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public @ResponseBody Collection<UserResponse> getUsers() {
        return userService.getAllUsers();
    }
}
