package com.epam.dao.entity;

import java.util.Collection;
import java.util.Objects;

public class Course {
    private Integer id;

    private String courseName;

    private Collection<Student> students;

    public Course(Integer id, String courseName) {
        this.id = id;
        this.courseName = courseName;
    }

    public Course(Integer id, String courseName, Collection<Student> students) {
        this.id = id;
        this.courseName = courseName;
        this.students = students;
    }

    public Course(String courseName, Collection<Student> students) {
        this.courseName = courseName;
        this.students = students;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return Objects.equals(getId(), course.getId()) &&
                Objects.equals(getCourseName(), course.getCourseName()) &&
                Objects.equals(getStudents(), course.getStudents());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCourseName(), getStudents());
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", students=" + students +
                '}';
    }
}
