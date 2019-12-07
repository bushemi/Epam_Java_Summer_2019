package com.bushemi.dao.impl;

import com.bushemi.dao.AbstractCrudDao;
import com.bushemi.dao.QuestionDao;
import com.bushemi.entities.Question;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionDaoImpl extends AbstractCrudDao<Question> implements QuestionDao {
    public QuestionDaoImpl(SessionFactory sessionFactory) {
        super(Question.class, sessionFactory);
    }

    public List<Question> findAll() {
        return getCurrentSession().createQuery("from Question").list();
    }
}
