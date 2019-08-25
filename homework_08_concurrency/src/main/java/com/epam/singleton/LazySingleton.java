package com.epam.singleton;

import static java.util.Objects.isNull;

public class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton() {
    }

    public static synchronized LazySingleton getInstance() {
        if (isNull(instance)) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
