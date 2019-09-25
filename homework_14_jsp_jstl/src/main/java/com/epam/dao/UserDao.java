package com.epam.dao;

public interface UserDao {
    Long save(UserCreating user);

    boolean isUserExist(String login);
}
