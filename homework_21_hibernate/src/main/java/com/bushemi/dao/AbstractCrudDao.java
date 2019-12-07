package com.bushemi.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.UUID;

public abstract class AbstractCrudDao<T> {
    private final SessionFactory sessionFactory;
    private final Class<T> clazz;

    public AbstractCrudDao(Class<T> clazz, SessionFactory sessionFactory) {
        this.clazz = clazz;
        this.sessionFactory = sessionFactory;
    }

    public void create(T test) {
        getCurrentSession().save(test);
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void update(T test) {
        getCurrentSession().clear();
        getCurrentSession().update(test);
    }

    public T read(UUID testId) {
        return getCurrentSession().find(clazz, testId);
    }


    public void delete(UUID id) {
        getCurrentSession().delete(read(id));
    }
}
