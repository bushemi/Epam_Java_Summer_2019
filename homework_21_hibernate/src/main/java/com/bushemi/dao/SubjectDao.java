package com.bushemi.dao;

import com.bushemi.entities.Subject;

import java.util.List;
import java.util.UUID;

public interface SubjectDao {
    void create(Subject subject);

    void update(Subject subject);

    Subject read(UUID subjectId);

    Subject findByName(String subjectName);

    void delete(UUID id);

    List<Subject> findAll();
}
