package com.epam.dao.implementations;

import com.epam.dao.entity.StudentCourse;
import com.epam.dao.interfaces.StudentsCoursesDao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.epam.dao.ConnectionService.getConnection;
import static java.util.Objects.requireNonNull;

public class StudentsCoursesDaoImpl implements StudentsCoursesDao {

    @Override
    public Integer save(StudentCourse entity) {
        requireNonNull(entity, "studentCourse can not be null");
        requireNonNull(entity.getStudentId(), "student's id can't be null");
        requireNonNull(entity.getCourseId(), "course's id can't be null");
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO students_courses (student_id, course_id) VALUES (?, ?);");
            preparedStatement.setInt(1, entity.getStudentId());
            preparedStatement.setInt(2, entity.getCourseId());
            return preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveAll(Collection<StudentCourse> entities) {
        entities.forEach(this::save);
    }

    @Override
    public Collection<StudentCourse> findAll() {
        List<StudentCourse> courses = new ArrayList<>();
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM students_courses;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                courses.add(getStudentCourseFromResultSet(resultSet));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return courses;
    }

    private StudentCourse getStudentCourseFromResultSet(ResultSet resultSet) throws SQLException {
        return new StudentCourse(resultSet.getInt(1), resultSet.getInt(2));
    }

    @Override
    public Collection<StudentCourse> findByStudentId(Integer studentId) {
        List<StudentCourse> courses = new ArrayList<>();
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM students_courses WHERE student_id = ?;");
            preparedStatement.setInt(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                courses.add(getStudentCourseFromResultSet(resultSet));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return courses;
    }

    @Override
    public Collection<StudentCourse> findByCourseId(Integer courseId) {
        List<StudentCourse> courses = new ArrayList<>();
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM students_courses WHERE course_id = ?;");
            preparedStatement.setInt(1, courseId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                courses.add(getStudentCourseFromResultSet(resultSet));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return courses;
    }

    @Override
    public void delete(Integer studentId, Integer courseId) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM students_courses WHERE student_id = ? AND course_id = ?;");
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, courseId);
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
