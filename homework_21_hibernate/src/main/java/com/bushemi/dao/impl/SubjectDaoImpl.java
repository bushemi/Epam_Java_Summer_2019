package com.bushemi.dao.impl;

import com.bushemi.dao.AbstractCrudDao;
import com.bushemi.dao.SubjectDao;
import com.bushemi.entities.Subject;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class SubjectDaoImpl extends AbstractCrudDao<Subject> implements SubjectDao {

    public SubjectDaoImpl(SessionFactory sessionFactory) {
        super(Subject.class, sessionFactory);
    }


    public List<Subject> findAll() {
        return getCurrentSession().createQuery("from Subject").list();
    }

    @Override
    @Transactional(readOnly = true)
    public Subject findByName(String subjectName) {
        return (Subject) getCurrentSession()
                .createQuery("from Subject where subjectName = :name")
                .setParameter("name", subjectName)
                .uniqueResult();
    }
}
