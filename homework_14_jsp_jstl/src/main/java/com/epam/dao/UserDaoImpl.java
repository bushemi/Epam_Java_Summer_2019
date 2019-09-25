package com.epam.dao;

import com.epam.service.DbConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    private static final Logger LOG = LoggerFactory.getLogger("UserDaoImpl");
    private final DbConnectionService connectionService;

    public UserDaoImpl(DbConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @Override
    public Long save(UserCreating user) {
        LOG.info("save new user = {}", user);
        try (Connection connection = connectionService.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO users (id, login, password, first_name, last_name, age) VALUES (null, ?, ?, ?, ?, ?);"
                            , PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setInt(5, user.getAge());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            boolean next = resultSet.next();
            if (next) {
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {
            LOG.error("Can't save a new user = {}. {}", user, e.getMessage());
        }
        return 0L;
    }

    @Override
    public boolean isUserExist(String login) {
        LOG.info("isUserExist for login = {}", login);
        try (Connection connection = connectionService.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from users where login = ?;");
            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();
            boolean next = resultSet.next();
            if (next) {
                return true;
            }
        } catch (SQLException e) {
            LOG.error("Can't find a user with same login {}. {}", login, e.getMessage());
        }
        return false;
    }
}
