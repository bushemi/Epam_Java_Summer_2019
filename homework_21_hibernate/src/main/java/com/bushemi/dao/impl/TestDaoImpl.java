package com.bushemi.dao.impl;

import com.bushemi.dao.AbstractCrudDao;
import com.bushemi.dao.TestDao;
import com.bushemi.entities.Test;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestDaoImpl extends AbstractCrudDao<Test> implements TestDao {

    public TestDaoImpl(SessionFactory sessionFactory) {
        super(Test.class, sessionFactory);
    }

    public List<Test> findAll() {
        return getCurrentSession().createQuery("from Test").list();
    }

}
