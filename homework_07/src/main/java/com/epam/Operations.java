package com.epam;

public enum Operations {
    ZIP("zip"), UNZIP("unzip");
    private final String value;

    Operations(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Operations findByString(String text) {
        for (Operations operation : Operations.values()) {
            if (operation.getValue().equals(text.toLowerCase())) {
                return operation;
            }
        }
        throw new UnsupportedOperationException("use zip or unzip!");
    }
}
