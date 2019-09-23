package com.epam.dao;

import com.epam.DBException;
import com.epam.dao.interfaces.TestEntityDao;
import com.epam.model.TestEntity;
import com.epam.service.DbConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TestEntityDaoImpl implements TestEntityDao {
    private static final Logger LOG = LoggerFactory.getLogger("TestEntityDaoImpl");
    private DbConnectionService connections;

    public TestEntityDaoImpl(DbConnectionService connections) {
        this.connections = connections;
    }

    @Override
    public long save(TestEntity entity) {
        LOG.info("save = {}", entity);
        try (Connection connection = connections.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO tests (id, subject, test_name, difficulty, seconds_to_complete) VALUES (null, ?, ?, ?, ?);");
            setFieldsForSavingEntityToDb(entity, preparedStatement);
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {
            LOG.error("Can't save a new entity = {}. {}", entity, e.getMessage());
        }
        return 0;
    }

    @Override
    public void saveAll(List<TestEntity> entities) {
        LOG.info("saveAll = {}", entities);
        entities.forEach(this::save);
    }

    @Override
    public TestEntity findById(Long id) {
        LOG.info("findById = {}", id);
        try (Connection connection = connections.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from tests where id = ?;");
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getTestEntityFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOG.error("Can't find an entity by id = {}. {}", id, e.getMessage());
        }
        return null;
    }

    private TestEntity getTestEntityFromResultSet(ResultSet resultSet) throws SQLException {
        long testId = resultSet.getLong(1);
        String subject = resultSet.getString(2);
        String testName = resultSet.getString(3);
        int difficulty = resultSet.getInt(4);
        int secondsToComplete = resultSet.getInt(5);
        return new TestEntity(testId, subject, testName, difficulty, secondsToComplete);
    }

    @Override
    public List<TestEntity> findAll() {
        LOG.info("findAll");
        List<TestEntity> tests = new ArrayList<>();
        try (Connection connection = connections.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from tests;");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tests.add(getTestEntityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOG.error("Can't find all tests. {}", e.getMessage());
        }
        LOG.info("the result of findAll - {}", tests);
        return tests;
    }

    @Override
    public void update(TestEntity entity) {
        LOG.info("update = {}", entity);
        try (Connection connection = connections.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("update tests set subject=?, test_name=?, difficulty=?, seconds_to_complete=? where id = ?;");
            setFieldsForSavingEntityToDb(entity, preparedStatement);
            preparedStatement.setLong(5, entity.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOG.error("Can't save a new entity = {}. {}", entity, e.getMessage());
        }
    }

    private void setFieldsForSavingEntityToDb(TestEntity entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getSubject());
        preparedStatement.setString(2, entity.getTestName());
        preparedStatement.setInt(3, entity.getDifficulty());
        preparedStatement.setInt(4, entity.getSecondsToComplete());
    }

    @Override
    public void delete(Long id) {
        LOG.info("delete = {}", id);
        try (Connection connection = connections.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("delete * from tests where id = ?;");
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Can't delete an entity with id = {}. {}", id, e.getMessage());
        }
    }

    @Override
    public boolean isTestExist(Long id) {
        LOG.info("isTestExist = {}", id);
        try (Connection connection = connections.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select count(*) from tests where id = ?;");
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) return resultSet.getInt(1) > 0;
            return false;
        } catch (SQLException e) {
            LOG.error("Can't search an entity with id = {}. {}", id, e.getMessage());
        }
        throw new DBException("Что-то пошло не так при работе с базой данных.");
    }
}
