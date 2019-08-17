package com.epam.dao.implementations;

import com.epam.dao.entity.Course;
import com.epam.dao.interfaces.CourseDao;

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

public class CourseDaoImpl implements CourseDao {
    @Override
    public Integer save(Course course) {
        requireNonNull(course, "person can not be null");
        requireNonNull(course.getId(), "courses id can't be null");
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO courses (id, course_name) VALUES (?, ?);");
            preparedStatement.setInt(1, course.getId());
            preparedStatement.setString(2, course.getCourseName());
            return preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveAll(Collection<Course> courses) {
        courses.forEach(this::save);
    }

    @Override
    public Course findById(Integer id) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM courses where id = ? ;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getCourseFromResultSet(resultSet);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Course getCourseFromResultSet(ResultSet resultSet) throws SQLException {
        return new Course(resultSet.getInt(1), resultSet.getString(2));
    }

    @Override
    public Collection<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM courses;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                courses.add(getCourseFromResultSet(resultSet));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return courses;
    }

    @Override
    public void update(Course course) {
        Objects.requireNonNull(course, "can't update null course");
        Objects.requireNonNull(course.getId(), "Can't update a course with null id. Use save instead.");
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE courses SET course_name = ? WHERE id = ?;");
            preparedStatement.setString(1, course.getCourseName());
            preparedStatement.setInt(2, course.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM courses WHERE id = ?;");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
