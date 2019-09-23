package com.epam.dao.interfaces;

import com.epam.model.TestEntity;

public interface TestEntityDao extends CrudOperationsInterface<TestEntity> {
    boolean isTestExist(Long id);
}
