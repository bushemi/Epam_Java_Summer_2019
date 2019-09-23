package com.epam.service;

import com.epam.dao.interfaces.CrudOperationsInterface;
import com.epam.model.TestEntity;

import java.util.List;

public interface TestEntityService extends CrudOperationsInterface<TestEntity> {
    void updateAll(List<TestEntity> entities);

    boolean isTestExist(Long id);

}
