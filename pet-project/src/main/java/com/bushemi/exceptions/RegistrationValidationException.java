package com.bushemi.exceptions;

import java.util.Map;

public class RegistrationValidationException extends RuntimeException {
    private Map<String, String> errorsMap;

    public RegistrationValidationException(Map<String, String> errorsMap) {
        this.errorsMap = errorsMap;
    }

    public Map<String, String> getErrorsMap() {
        return errorsMap;
    }
}
