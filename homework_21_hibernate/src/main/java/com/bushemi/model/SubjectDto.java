package com.bushemi.model;

import java.util.Objects;
import java.util.UUID;

public class SubjectDto {

    private UUID id = UUID.randomUUID();

    private String subjectName;

    public SubjectDto() {
    }

    public SubjectDto(UUID id, String subjectName) {
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
        if (!(o instanceof SubjectDto)) return false;
        SubjectDto subject = (SubjectDto) o;
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
