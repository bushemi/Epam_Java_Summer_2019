package com.epam.service;

import com.epam.dao.UserCreating;
import com.epam.dao.UserDao;
import com.epam.dao.UserDaoImpl;

public class UserServiceImpl implements UserService {
    private DbConnectionService connectionService = new DbConnectionService();
    private UserDao userDao = new UserDaoImpl(connectionService);

    public UserServiceImpl() {
    }

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save(UserCreating user) {
        userDao.save(user);
    }

    @Override
    public boolean isUserExist(String login) {
        return userDao.isUserExist(login);
    }
}
