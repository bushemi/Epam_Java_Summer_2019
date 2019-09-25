package com.epam.web.servlets;

public enum RegistrationJspFieldNames {
    LOGIN("login"),
    PASSWORD("password"),
    FIRST_NAME("firstName"),
    LAST_NAME("lastName"),
    AGE("age");
    private String fieldName;

    RegistrationJspFieldNames(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
