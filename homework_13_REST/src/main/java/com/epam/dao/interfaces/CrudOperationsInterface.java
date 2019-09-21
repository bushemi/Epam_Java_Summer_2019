package com.epam.dao.interfaces;

import java.util.List;

public interface CrudOperationsInterface<T> {
    long save(T entity);

    void saveAll(List<T> entities);

    T findById(Long id);

    List<T> findAll();

    void update(T entity);

    void delete(Long id);
}
