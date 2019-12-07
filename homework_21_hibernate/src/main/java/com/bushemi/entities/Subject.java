package com.bushemi.entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@DynamicInsert
@DynamicUpdate
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 16)
    private UUID id;

    @Column(unique = true)
    private String subjectName;

    public Subject() {
    }

    public Subject(UUID id, String subjectName) {
        this.id = id;
        this.subjectName = subjectName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject)) return false;
        Subject subject = (Subject) o;
        return Objects.equals(getId(), subject.getId()) &&
                Objects.equals(getSubjectName(), subject.getSubjectName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSubjectName());
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", subjectName='" + subjectName + '\'' +
                '}';
    }
}
