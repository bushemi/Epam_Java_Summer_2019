package com.epam.dao.interfaces;

import com.epam.dao.entity.StudentCourse;

import java.util.Collection;

public interface StudentsCoursesDao {

    Integer save(StudentCourse entity);

    void saveAll(Collection<StudentCourse> entities);

    Collection<StudentCourse> findAll();

    Collection<StudentCourse> findByStudentId(Integer studentId);

    Collection<StudentCourse> findByCourseId(Integer courseId);

    void delete(Integer studentId, Integer courseId);
}
