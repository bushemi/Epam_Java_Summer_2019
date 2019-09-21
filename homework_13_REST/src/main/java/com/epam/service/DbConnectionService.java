package com.epam.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnectionService {
    private static final Logger LOG = LoggerFactory.getLogger("DbConnectionService");

    public DbConnectionService() {
    }

    public Connection getConnection() throws SQLException, IOException {
        Properties props = new Properties();
        try (InputStream in =
                     DbConnectionService.class.getClassLoader().getResourceAsStream("db/db.properties")) {
            props.load(in);
        }

        String dbDriver = props.getProperty("db.driver");
        if (dbDriver != null) {
            System.setProperty("db.driver", dbDriver);
        }
        String url = props.getProperty("db.url");
        String username = props.getProperty("db.user");
        String password = props.getProperty("db.password");
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e) {
            LOG.error("error with loading class {}. {}", dbDriver, e.getMessage());
            LOG.error(String.valueOf(e));
        }
        return DriverManager.getConnection(url, username, password);
    }
}
