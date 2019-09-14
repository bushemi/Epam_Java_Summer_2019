package com.epam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Starter {
    private static final LogsConsoleManipulations main = new LogsConsoleManipulations();
    private static final Logger logger = LoggerFactory.getLogger("Starter");

    public static void main(String[] args) {
        main.handleRequest(args).forEach(logger::info);
    }
}
