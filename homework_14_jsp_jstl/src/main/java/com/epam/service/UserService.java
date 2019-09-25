package com.epam.service;

import com.epam.dao.UserCreating;

public interface UserService {
    void save(UserCreating user);

    boolean isUserExist(String login);
}
