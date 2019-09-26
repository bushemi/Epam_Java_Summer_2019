package com.epam.service;

import com.epam.RegistrationValidationException;
import com.epam.dao.UserCreating;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class UserValidationServiceTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private UserValidationService userValidationService = new UserValidationService();

    @Test
    public void validateUser() {
        //GIVEN
        expectedException.expect(RegistrationValidationException.class);

        UserCreating user = new UserCreating();
        user.setLogin("root%21%21%21%21;~!2#$%^&*");
        user.setPassword("!");

        //WHEN
        try {
            userValidationService.validateUser(user);
        } catch (RegistrationValidationException e) {
            Map<String, String> errorsMap = e.getErrorsMap();

            //THEN
            assertNotNull(errorsMap.get("password"));
            assertNotNull(errorsMap.get("login"));
            assertNull(errorsMap.get("age"));
            assertNull(errorsMap.get("lastName"));
            assertNull(errorsMap.get("firstName"));

            throw e;
        }
    }
}