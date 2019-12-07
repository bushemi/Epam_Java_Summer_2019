package com.bushemi.dao;

import com.bushemi.entities.Question;

import java.util.List;
import java.util.UUID;

public interface QuestionDao {
    void create(Question question);

    void update(Question question);

    Question read(UUID question);

    void delete(UUID id);

    List<Question> findAll();
}
