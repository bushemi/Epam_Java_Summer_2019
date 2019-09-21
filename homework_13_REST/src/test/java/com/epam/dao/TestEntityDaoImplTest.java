package com.epam.dao;

import com.epam.service.DbConnectionService;
import com.epam.model.TestEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TestEntityDaoImplTest {

    private static final String QUERY_FOR_INSERT = "INSERT INTO tests (id, subject, test_name, difficulty, seconds_to_complete) VALUES (null, ?, ?, ?, ?);";
    private static final int DEFAULT_DIFFICULTY = 1;
    private static final int DEFAULT_SECONDS_TO_COMPLETE = 10;
    @Mock
    DbConnectionService service;

    @InjectMocks
    TestEntityDaoImpl testDao;
    private Connection connection = mock(Connection.class);
    private PreparedStatement statement = mock(PreparedStatement.class);
    private ResultSet resultSet = mock(ResultSet.class);

    @Test
    public void save() throws IOException, SQLException {
        //GIVEN
        TestEntity entity = new TestEntity();
        entity.setDifficulty(DEFAULT_DIFFICULTY);
        entity.setSecondsToComplete(DEFAULT_SECONDS_TO_COMPLETE);
        when(service.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(QUERY_FOR_INSERT))
                .thenReturn(statement);
        when(statement.getGeneratedKeys()).thenReturn(resultSet);

        //WHEN
        testDao.save(entity);

        //THEN
        verify(service).getConnection();
        verify(connection).prepareStatement(QUERY_FOR_INSERT);
        verify(statement).getGeneratedKeys();
        verify(resultSet).next();
    }

    @Test
    public void saveAll() throws IOException, SQLException {
        //GIVEN
        TestEntity testEntity1 = new TestEntity();
        testEntity1.setDifficulty(DEFAULT_DIFFICULTY);
        testEntity1.setSecondsToComplete(DEFAULT_SECONDS_TO_COMPLETE);
        TestEntity testEntity2 = new TestEntity();
        testEntity2.setDifficulty(DEFAULT_DIFFICULTY);
        testEntity2.setSecondsToComplete(DEFAULT_SECONDS_TO_COMPLETE);
        TestEntity testEntity3 = new TestEntity();
        testEntity3.setDifficulty(DEFAULT_DIFFICULTY);
        testEntity3.setSecondsToComplete(DEFAULT_SECONDS_TO_COMPLETE);

        when(service.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(QUERY_FOR_INSERT))
                .thenReturn(statement);
        when(statement.getGeneratedKeys()).thenReturn(resultSet);

        //WHEN
        testDao.saveAll(Arrays.asList(testEntity1, testEntity2, testEntity3));

        //THEN
        verify(service, times(3)).getConnection();
        verify(connection, times(3)).prepareStatement(QUERY_FOR_INSERT);
        verify(statement, times(3)).getGeneratedKeys();
        verify(resultSet, times(3)).next();
    }

    @Test
    public void findById() throws SQLException, IOException {
        //GIVEN
        when(service.getConnection()).thenReturn(connection);
        when(connection.prepareStatement("select * from tests where id = ?;"))
                .thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);

        //WHEN
        testDao.findById(1L);

        //THEN
        verify(service).getConnection();
        verify(connection).prepareStatement("select * from tests where id = ?;");
        verify(resultSet).next();
    }

    @Test
    public void findAll() throws IOException, SQLException {
        //GIVEN
        when(service.getConnection()).thenReturn(connection);
        when(connection.prepareStatement("select * from tests;"))
                .thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);

        //WHEN
        testDao.findAll();

        //THEN
        verify(service).getConnection();
        verify(connection).prepareStatement("select * from tests;");
        verify(resultSet).next();
    }

    @Test
    public void update() throws IOException, SQLException {
        //GIVEN
        TestEntity entity = new TestEntity();
        entity.setId(1L);
        entity.setDifficulty(DEFAULT_DIFFICULTY);
        entity.setSecondsToComplete(DEFAULT_SECONDS_TO_COMPLETE);
        when(service.getConnection()).thenReturn(connection);
        when(connection.prepareStatement("update tests set subject=?, test_name=?, difficulty=?, seconds_to_complete=? where id = ?;"))
                .thenReturn(statement);
        //WHEN
        testDao.update(entity);

        //THEN
        verify(service).getConnection();
        verify(connection).prepareStatement("update tests set subject=?, test_name=?, difficulty=?, seconds_to_complete=? where id = ?;");
        verify(statement).executeUpdate();
    }

    @Test
    public void delete() throws IOException, SQLException {
        //GIVEN
        when(service.getConnection()).thenReturn(connection);
        when(connection.prepareStatement("delete * from tests where id = ?;"))
                .thenReturn(statement);

        //WHEN
        testDao.delete(1L);

        //THEN
        verify(service).getConnection();
        verify(connection).prepareStatement("delete * from tests where id = ?;");
        verify(statement).executeUpdate();
    }
}