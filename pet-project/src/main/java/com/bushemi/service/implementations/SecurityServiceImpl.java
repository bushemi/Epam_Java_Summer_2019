package com.bushemi.service.implementations;

import com.bushemi.converters.UserConverter;
import com.bushemi.dao.entity.User;
import com.bushemi.dao.interfaces.UserDao;
import com.bushemi.exceptions.WrongPasswordException;
import com.bushemi.model.UserCreatingDto;
import com.bushemi.model.UserForSessionDto;
import com.bushemi.service.interfaces.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class SecurityServiceImpl implements SecurityService {
    private static final Logger LOG = LoggerFactory.getLogger("SecurityServiceImpl");
    private UserDao userDao;
    private UserConverter userConverter;

    public SecurityServiceImpl(UserDao userDao,
                               UserConverter userConverter) {
        this.userDao = userDao;
        this.userConverter = userConverter;
    }

    @Override
    public UserForSessionDto login(UserCreatingDto user) {
        LOG.info("Try to login with user = {}", user);
        User byLogin = userDao.findByLogin(user.getLogin());
        if (nonNull(byLogin) && byLogin.getPassword().equals(user.getPassword())) {
            LOG.info("users successfully logged in");
            return userConverter.userToUserForSessionDto(byLogin);
        }
        LOG.error("Wrong login or password.");
        throw new WrongPasswordException();
    }
}
