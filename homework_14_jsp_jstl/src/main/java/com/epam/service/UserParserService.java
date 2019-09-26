package com.epam.service;

import com.epam.dao.UserCreating;

import static java.util.Objects.isNull;

public class UserParserService {

    private static final String DELIMITER_FOR_PARAMETERS = "&";
    private static final String DELIMITER_FOR_PAIRS = "=";

    public UserCreating fromString(String text) {
        UserCreating user = new UserCreating();
        String[] pairs = text.split(DELIMITER_FOR_PARAMETERS);
        for (String pair : pairs) {
            recognizeParameterAndIncludeItToUser(user, pair);
        }
        return user;
    }

    private void recognizeParameterAndIncludeItToUser(UserCreating user, String pair) {
        String[] strings = pair.split(DELIMITER_FOR_PAIRS);
        String value = strings.length > 1 ? strings[1].trim() : null;
        if (pair.contains("login")) {
            user.setLogin(valueIfPresentOrNull(value));
            return;
        }
        if (pair.contains("password")) {
            user.setPassword(valueIfPresentOrNull(value));
            return;
        }
        if (pair.contains("firstName")) {
            user.setFirstName(valueIfPresentOrNull(value));
            return;
        }
        if (pair.contains("lastName")) {
            user.setLastName(valueIfPresentOrNull(value));
            return;
        }
        if (pair.contains("age")) {
            includeAgeFromValueToUser(user, value);
        }
    }

    private String valueIfPresentOrNull(String value) {
        return isValueNull(value) ? null : value;
    }

    private void includeAgeFromValueToUser(UserCreating user, String value) {
        if (isValueNull(value)) {
            user.setAge(0);
        } else {
            int age;
            try {
                age = Integer.parseInt(value);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("\"age\" должно быть числом.");
            }
            user.setAge(age);
        }
    }

    private boolean isValueNull(String value) {
        return isNull(value) || value.equalsIgnoreCase("null") || value.isEmpty();
    }
}
