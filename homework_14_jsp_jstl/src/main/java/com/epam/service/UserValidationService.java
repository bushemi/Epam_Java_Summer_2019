package com.epam.service;

import com.epam.RegistrationValidationException;
import com.epam.dao.UserCreating;
import com.epam.web.servlets.RegistrationJspFieldNames;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;

public class UserValidationService {
    private static final Pattern PATTERN_FOR_STRINGS = Pattern.compile("[^\\w\\d\\s\\-]");
    private static final String LOGIN_FIELD_NAME = RegistrationJspFieldNames.LOGIN.getFieldName();
    private static final String PASSWORD_FIELD_NAME = RegistrationJspFieldNames.PASSWORD.getFieldName();
    private static final String FIRST_NAME_FIELD_NAME = RegistrationJspFieldNames.FIRST_NAME.getFieldName();
    private static final String LAST_NAME_FIELD_NAME = RegistrationJspFieldNames.LAST_NAME.getFieldName();
    private static final String AGE_FIELD_NAME = RegistrationJspFieldNames.AGE.getFieldName();
    private static final String NO_SPECIAL_CHARACTERS_MESSAGE = "Никаких спец символов. Только буквы, цифры, пробелы и дефис";
    private static final int LONGEST_CONFIRMED_AGE = 122;

    public void validateUser(UserCreating user) {
        Map<String, String> errorsMap = new HashMap<>();
        validateLogin(user.getLogin(), errorsMap);
        validatePassword(user.getPassword(), errorsMap);
        validateFirstName(user.getFirstName(), errorsMap);
        validateLastName(user.getLastName(), errorsMap);
        validateAge(user.getAge(), errorsMap);
        if (!errorsMap.isEmpty()) throw new RegistrationValidationException(errorsMap);
    }

    private void validateLogin(String login, Map<String, String> errors) {
        if (isNull(login) || login.isEmpty()) {
            errors.put(LOGIN_FIELD_NAME, "Заполните поле Логин.");
            return;
        }
        if (login.length() > 255) {
            errors.put(LOGIN_FIELD_NAME, "Логин должен быть не длинее 255 символов.");
            return;
        }
        if (validateAsString(login)) {
            errors.put(LOGIN_FIELD_NAME, NO_SPECIAL_CHARACTERS_MESSAGE);
        }
    }

    private void validatePassword(String password, Map<String, String> errors) {
        if (isNull(password) || password.isEmpty()) {
            errors.put(PASSWORD_FIELD_NAME, "Заполните поле Пароль.");
            return;
        }
        if (password.length() > 255) {
            errors.put(PASSWORD_FIELD_NAME, "Пароль должен быть не длинее 64 символов.");
            return;
        }
        if (validateAsString(password)) {
            errors.put(PASSWORD_FIELD_NAME, NO_SPECIAL_CHARACTERS_MESSAGE);
        }
    }

    private void validateFirstName(String firstName, Map<String, String> errors) {
        if (isNull(firstName)) return;
        if (firstName.length() > 255) {
            errors.put(FIRST_NAME_FIELD_NAME, "Имя можеть быть сохранено не длинее 255 символов.");
            return;
        }
        if (validateAsString(firstName)) {
            errors.put(FIRST_NAME_FIELD_NAME, NO_SPECIAL_CHARACTERS_MESSAGE);
        }
    }

    private void validateLastName(String lastName, Map<String, String> errors) {
        if (isNull(lastName)) return;
        if (lastName.length() > 255) {
            errors.put(LAST_NAME_FIELD_NAME, "Фамилия можеть быть сохранена не длинее 255 символов.");
            return;
        }
        if (validateAsString(lastName)) {
            errors.put(LAST_NAME_FIELD_NAME, NO_SPECIAL_CHARACTERS_MESSAGE);
        }
    }

    private void validateAge(Integer age, Map<String, String> errors) {
        if (isNull(age)) return;
        if (age > LONGEST_CONFIRMED_AGE) {
            errors.put(AGE_FIELD_NAME, String.format("Если ваш возраст действительно выше чем %d, вы самый старый человек планеты.", LONGEST_CONFIRMED_AGE));
            return;
        }
        if (age < 0) {
            errors.put(AGE_FIELD_NAME, "Возраст не может быть меньше нуля. Даже если вы сегодня родились.");
        }
    }

    private boolean validateAsString(String text) {
        Matcher matcher = PATTERN_FOR_STRINGS.matcher(text);
        return matcher.find();
    }
}
