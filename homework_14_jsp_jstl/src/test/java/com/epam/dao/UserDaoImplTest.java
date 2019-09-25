package com.epam.dao;

import com.epam.service.DbConnectionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class UserDaoImplTest {
    private static final String SQL_QUERY_FOR_INSERT = "INSERT INTO users (id, login, password, first_name, last_name, age) VALUES (null, ?, ?, ?, ?, ?);";
    @Mock
    DbConnectionService connectors;

    @InjectMocks
    UserDaoImpl userDao;

    private Connection connection = mock(Connection.class);
    private PreparedStatement statement = mock(PreparedStatement.class);
    private ResultSet resultSet = mock(ResultSet.class);

    @Test
    public void save() throws SQLException {
        //GIVEN
        when(connectors.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(SQL_QUERY_FOR_INSERT, PreparedStatement.RETURN_GENERATED_KEYS))
                .thenReturn(statement);
        when(statement.getGeneratedKeys()).thenReturn(resultSet);
        UserCreating user = new UserCreating();
        user.setAge(12);

        //WHEN
        userDao.save(user);

        //THEN
        verify(connectors).getConnection();
        verify(connection).prepareStatement(SQL_QUERY_FOR_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
        verify(statement).getGeneratedKeys();
        verify(resultSet).next();
    }

    @Test
    public void isUserExist() throws SQLException {
        //GIVEN
        when(connectors.getConnection()).thenReturn(connection);
        when(connection.prepareStatement("select * from users where login = ?;"))
                .thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);

        //WHEN
        userDao.isUserExist("user");

        //THEN
        verify(connectors).getConnection();
        verify(connection).prepareStatement("select * from users where login = ?;");
        verify(statement).executeQuery();
        verify(resultSet).next();
    }
}