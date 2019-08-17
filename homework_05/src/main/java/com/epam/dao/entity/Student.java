package com.epam.dao.entity;

import java.util.Collection;
import java.util.Objects;

public class Student {

    private Integer id;
    private Person person;

    private Collection<Course> courses;

    public Student(Integer id, Person person) {
        this.id = id;
        this.person = person;
    }

    public Student(Integer id, Person person, Collection<Course> courses) {
        this.id = id;
        this.person = person;
        this.courses = courses;
    }

    public Student(Person person, Collection<Course> courses) {
        this.person = person;
        this.courses = courses;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Collection<Course> getCourses() {
        return courses;
    }

    public void setCourses(Collection<Course> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(getId(), student.getId()) &&
                Objects.equals(getPerson(), student.getPerson()) &&
                Objects.equals(getCourses(), student.getCourses());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPerson(), getCourses());
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", person=" + person +
                ", courses=" + courses +
                '}';
    }
}
