package com.bushemi.services;

import com.bushemi.model.TestDto;

import java.util.List;

public interface TestService {

    void save(TestDto testDto);

    void update(TestDto testDto);

    TestDto findById(String id);

    List<TestDto> findAll();

    void deleteById(String id);
}
