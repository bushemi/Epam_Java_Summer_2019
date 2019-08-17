package com.epam.dao.interfaces;

import java.util.Collection;

public interface CrudOperations<T> {

    Integer save(T entity);

    void saveAll(Collection<T> entities);

    T findById(Integer id);

    Collection<T> findAll();

    void update(T entity);

    void delete(Integer id);
}
