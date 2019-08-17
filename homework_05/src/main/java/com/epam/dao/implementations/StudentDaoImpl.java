package com.epam.dao.implementations;

import com.epam.dao.entity.Person;
import com.epam.dao.entity.Student;
import com.epam.dao.interfaces.StudentDao;

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
import static java.util.Objects.requireNonNull;

public class StudentDaoImpl implements StudentDao {
    @Override
    public Integer save(Student student) {
        requireNonNull(student.getPerson(), "person can not be null");
        requireNonNull(student.getPerson().getId(), "person id can't be null");
        requireNonNull(student.getId(), "students id can't be null");
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO students (id, person_id) VALUES (?, ?);");
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setInt(2, student.getPerson().getId());
            return preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void saveAll(Collection<Student> students) {
        students.forEach(this::save);
    }

    @Override
    public Student findById(Integer id) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM students where id = ? ;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getStudentFromResultSetRow(resultSet);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Student getStudentFromResultSetRow(ResultSet resultSet) throws SQLException {
        return new Student(resultSet.getInt(1), new Person(resultSet.getInt(2)));
    }

    @Override
    public Collection<Student> findAll() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM students;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                students.add(getStudentFromResultSetRow(resultSet));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public void update(Student student) {
        Objects.requireNonNull(student, "can't update null student");
        Objects.requireNonNull(student.getId(), "Can't update a student with null id. Use save instead.");
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE students SET person_id = ? WHERE id = ?;");
            preparedStatement.setInt(1, student.getPerson().getId());
            preparedStatement.setInt(2, student.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM students WHERE id = ?;");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
