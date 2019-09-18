package com.epam.model;

import java.util.Objects;

public class TestEntity {

    Long id;
    String subject;
    String testName;
    Integer difficulty;
    Integer secondsToComplete;

    public TestEntity() {
    }

    public TestEntity(Long id, String subject, String testName, Integer difficulty, Integer secondsToComplete) {
        this.id = id;
        this.subject = subject;
        this.testName = testName;
        this.difficulty = difficulty;
        this.secondsToComplete = secondsToComplete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getSecondsToComplete() {
        return secondsToComplete;
    }

    public void setSecondsToComplete(Integer secondsToComplete) {
        this.secondsToComplete = secondsToComplete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestEntity)) return false;
        TestEntity test = (TestEntity) o;
        return Objects.equals(getId(), test.getId()) &&
                Objects.equals(getSubject(), test.getSubject()) &&
                Objects.equals(getTestName(), test.getTestName()) &&
                Objects.equals(getDifficulty(), test.getDifficulty()) &&
                Objects.equals(getSecondsToComplete(), test.getSecondsToComplete());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSubject(), getTestName(), getDifficulty(), getSecondsToComplete());
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", testName='" + testName + '\'' +
                ", difficulty=" + difficulty +
                ", secondsToComplete=" + secondsToComplete +
                '}';
    }
}
