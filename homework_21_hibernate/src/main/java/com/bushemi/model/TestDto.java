package com.bushemi.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class TestDto {

    private UUID id = UUID.randomUUID();

    private SubjectDto subject;

    private String testName;

    private List<QuestionDto> questionDtos = new ArrayList<>();

    public TestDto() {
    }

    public TestDto(UUID id, SubjectDto subject, String testName, List<QuestionDto> questionDtos) {
        this.id = id;
        this.subject = subject;
        this.testName = testName;
        this.questionDtos = questionDtos;
    }

    public TestDto(UUID id, SubjectDto subject, String testName) {
        this.id = id;
        this.subject = subject;
        this.testName = testName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public SubjectDto getSubject() {
        return subject;
    }

    public void setSubject(SubjectDto subject) {
        this.subject = subject;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public List<QuestionDto> getQuestionDtos() {
        return questionDtos;
    }

    public void setQuestionDtos(List<QuestionDto> questionDtos) {
        this.questionDtos = questionDtos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestDto)) return false;
        TestDto test = (TestDto) o;
        return Objects.equals(id, test.id) &&
                Objects.equals(subject, test.subject) &&
                Objects.equals(testName, test.testName) &&
                Objects.equals(questionDtos, test.questionDtos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subject, testName, questionDtos);
    }

    @Override
    public String toString() {
        return "TestDto{" +
                "id=" + id +
                ", subject=" + subject +
                ", testName='" + testName + '\'' +
                ", questionDtos=" + questionDtos +
                '}';
    }
}
