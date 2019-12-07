package com.bushemi.dao;

import com.bushemi.entities.Test;

import java.util.List;
import java.util.UUID;

public interface TestDao {
    void create(Test test);

    void update(Test test);

    Test read(UUID testId);

    void delete(UUID id);

    List<Test> findAll();
}
