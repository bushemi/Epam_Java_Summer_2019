package com.epam.dao.implementations;

import com.epam.dao.entity.Person;
import com.epam.dao.interfaces.PersonDao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static com.epam.dao.ConnectionService.getConnection;

public class PersonDaoImpl implements PersonDao {


    @Override
    public Integer save(Person person) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO persons (id, first_name, last_name) VALUES (?, ?, ?);");
            preparedStatement.setInt(1, person.getId());
            preparedStatement.setString(2, person.getFirstName());
            preparedStatement.setString(3, person.getLastName());
            return preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void saveAll(Collection<Person> persons) {
        persons.forEach(this::save);
    }

    @Override
    public Person findById(Integer id) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM persons where id = ? ;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getPersonFromResultSetRow(resultSet);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Person> findAll() {
        List<Person> people = new ArrayList<>();
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM persons;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                people.add(getPersonFromResultSetRow(resultSet));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return people;
    }

    private Person getPersonFromResultSetRow(ResultSet result) throws SQLException {
        return new Person(result.getInt(1), result.getString(2), result.getString(3));
    }


    @Override
    public void update(Person person) {
        Objects.requireNonNull(person, "can't update null person");
        Objects.requireNonNull(person.getId(), "Can't update a person with null id. Use save instead.");
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE persons SET first_name = ?, last_name = ? WHERE id = ?;");
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setInt(3, person.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM persons WHERE id = ?;");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

}
