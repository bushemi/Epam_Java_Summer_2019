package com.epam.service;

import com.epam.dao.TestEntityDaoImpl;
import com.epam.dao.interfaces.TestEntityDao;
import com.epam.model.TestEntity;

import java.util.List;

public class TestEntityServiceImpl implements TestEntityService {

    private DbConnectionService connector = new DbConnectionService();
    private TestEntityDao dao = new TestEntityDaoImpl(connector);

    public TestEntityServiceImpl() {
    }

    public TestEntityServiceImpl(TestEntityDao dao) {
        this.dao = dao;
    }

    @Override
    public TestEntity findById(Long testId) {
        return dao.findById(testId);
    }

    @Override
    public long save(TestEntity entity) {
        return dao.save(entity);
    }

    @Override
    public void saveAll(List<TestEntity> entities) {
        dao.saveAll(entities);
    }

    @Override
    public List<TestEntity> findAll() {
        return dao.findAll();
    }

    @Override
    public void update(TestEntity entity) {
        dao.update(entity);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public void updateAll(List<TestEntity> entities) {
        entities.forEach(dao::update);
    }

    @Override
    public boolean isTestExist(Long id) {
        return dao.isTestExist(id);
    }
}
