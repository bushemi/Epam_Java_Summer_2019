package com.epam.dao.entity;

import java.util.Collection;
import java.util.Objects;

public class Course {
    private Integer id;

    private String courseName;

    private Collection<Integer> studentIds;

    public Course(Integer id, String courseName) {
        this.id = id;
        this.courseName = courseName;
    }

    public Course(Integer id, String courseName, Collection<Integer> studentIds) {
        this.id = id;
        this.courseName = courseName;
        this.studentIds = studentIds;
    }

    public Course(String courseName, Collection<Integer> studentIds) {
        this.courseName = courseName;
        this.studentIds = studentIds;
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

    public Collection<Integer> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(Collection<Integer> studentIds) {
        this.studentIds = studentIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return Objects.equals(getId(), course.getId()) &&
                Objects.equals(getCourseName(), course.getCourseName()) &&
                Objects.equals(getStudentIds(), course.getStudentIds());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCourseName(), getStudentIds());
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", studentIds=" + studentIds +
                '}';
    }
}
