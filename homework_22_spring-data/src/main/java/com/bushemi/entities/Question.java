package com.bushemi.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.isNull;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 16)
    private UUID id;

    @ManyToOne
    private Test test;

    @Column
    private String mainText;

    public Question() {
    }

    public Question(UUID id, Test test, String mainText) {
        this.id = id;
        this.test = test;
        this.mainText = mainText;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
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
        if (!(o instanceof Question)) return false;
        Question question = (Question) o;
        return Objects.equals(getId(), question.getId()) &&
                Objects.equals(getTest(), question.getTest()) &&
                Objects.equals(getMainText(), question.getMainText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTest(), getMainText());
    }

    @Override
    public String toString() {
        String test = isNull(getTest()) ? "null" : this.test.getId().toString();
        return "QuestionDto{" +
                "id=" + id +
                ", test=" + test +
                ", mainText='" + mainText + '\'' +
                '}';
    }
}
