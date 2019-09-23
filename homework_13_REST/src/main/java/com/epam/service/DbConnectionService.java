package com.epam.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnectionService {
    private static final Logger LOG = LoggerFactory.getLogger("DbConnectionService");
    private String url;
    private String username;
    private String password;

    public DbConnectionService() {
        init();
    }

    private void init() {
        PropertiesLoaderService propertiesLoaderService = new PropertiesLoaderService();
        Properties props = propertiesLoaderService.getProps();
        String dbDriver = props.getProperty("db.driver");
        if (dbDriver != null) {
            System.setProperty("db.driver", dbDriver);
        }
        url = props.getProperty("db.url");
        username = props.getProperty("db.user");
        password = props.getProperty("db.password");
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e) {
            LOG.error("error with loading class {}. {}", dbDriver, e.getMessage());
            LOG.error(String.valueOf(e));
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
