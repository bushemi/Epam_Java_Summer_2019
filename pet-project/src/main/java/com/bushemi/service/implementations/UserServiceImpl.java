package com.bushemi.service.implementations;

import com.bushemi.converters.UserConverter;
import com.bushemi.dao.entity.User;
import com.bushemi.dao.implementations.UserDaoImpl;
import com.bushemi.dao.interfaces.UserDao;
import com.bushemi.model.UserCreatingDto;
import com.bushemi.model.UserForSessionDto;
import com.bushemi.service.interfaces.DbConnectionService;
import com.bushemi.service.interfaces.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao ;
    private UserConverter userConverter;

    public UserServiceImpl(UserDao userDao, UserConverter userConverter) {
        this.userDao = userDao;
        this.userConverter = userConverter;
    }

    @Override
    public UserForSessionDto save(UserCreatingDto user) {
        User userToSave = userConverter.userCreatingToUser(user);

        long id = userDao.save(userToSave);

        return userConverter.userToUserForSessionDto(userDao.findById(id));
    }

    @Override
    public boolean isUserExist(String login) {
        return userDao.isUserExist(login);
    }
}
