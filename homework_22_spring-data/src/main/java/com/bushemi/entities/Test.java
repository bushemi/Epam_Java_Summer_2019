package com.bushemi.entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@DynamicInsert
@DynamicUpdate
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 16)
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Subject subject;

    @Column
    private String testName;

    @Column
    private Integer difficulty;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();

    public Test() {
    }

    public Test(UUID id, Subject subject, String testName, List<Question> questions) {
        this.id = id;
        this.subject = subject;
        this.testName = testName;
        this.questions = questions;
    }

    public Test(UUID id, Subject subject, String testName) {
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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Test)) return false;
        Test test = (Test) o;
        return Objects.equals(getId(), test.getId()) &&
                Objects.equals(getSubject(), test.getSubject()) &&
                Objects.equals(getTestName(), test.getTestName()) &&
                Objects.equals(getDifficulty(), test.getDifficulty()) &&
                Objects.equals(getQuestions(), test.getQuestions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSubject(), getTestName(), getDifficulty(), getQuestions());
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", subject=" + subject +
                ", testName='" + testName + '\'' +
                ", difficulty=" + difficulty +
                ", questions=" + questions +
                '}';
    }
}
