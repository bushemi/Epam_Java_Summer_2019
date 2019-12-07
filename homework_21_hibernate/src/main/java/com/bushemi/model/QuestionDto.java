package com.bushemi.model;

import java.util.Objects;
import java.util.UUID;

public class QuestionDto {

    private String id;

    private String testId;

    private String mainText;

    public QuestionDto() {
    }

    public QuestionDto(String testId, String mainText) {
        this.testId = testId;
        this.mainText = mainText;
    }

    public QuestionDto(String mainText) {
        this.mainText = mainText;
    }

    public QuestionDto(String id, String testId, String mainText) {
        this.id = id;
        this.testId = testId;
        this.mainText = mainText;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuestionDto)) return false;
        QuestionDto that = (QuestionDto) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getTestId(), that.getTestId()) &&
                Objects.equals(getMainText(), that.getMainText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTestId(), getMainText());
    }

    @Override
    public String toString() {
        return "QuestionDto{" +
                "id=" + id +
                ", testId='" + testId + '\'' +
                ", mainText='" + mainText + '\'' +
                '}';
    }
}
