package com.epam.service;

import com.epam.dao.UserCreating;
import com.epam.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    @Mock
    UserDao dao;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    public void save() {
        //GIVEN
        UserCreating user = new UserCreating();

        //WHEN
        userService.save(user);

        //THEN
        verify(dao).save(user);
    }
}