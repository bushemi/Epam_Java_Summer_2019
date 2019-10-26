package com.bushemi.service.implementations;

import com.bushemi.annotations.Timed;
import com.bushemi.converters.TestConverter;
import com.bushemi.dao.entity.Subject;
import com.bushemi.dao.entity.Test;
import com.bushemi.dao.interfaces.SubjectDao;
import com.bushemi.dao.interfaces.TestDao;
import com.bushemi.exceptions.DbException;
import com.bushemi.model.TestForSessionDto;
import com.bushemi.model.TestForTestsPage;
import com.bushemi.service.interfaces.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Timed
public class TestServiceImpl implements TestService {
    @Autowired
    private TestDao testDao;
    @Autowired
    private SubjectDao subjectDao;
    @Autowired
    private TestConverter testConverter;


    @Override
    public List<TestForTestsPage> findAllTests() {
        Map<Long, Subject> subjects = getSubjectMap();
        return testDao.findAll().stream()
                .map(test -> testConverter.fromTestToTestForTestsPage(test, subjects))
                .collect(Collectors.toList());
    }

    private Map<Long, Subject> getSubjectMap() {
        Map<Long, Subject> subjects = new HashMap<>();
        subjectDao.findAll().forEach(subject -> subjects.put(subject.getId(), subject));
        return subjects;
    }

    @Override
    public TestForSessionDto findTestById(Long id) {
        try {
            Test byId = testDao.findById(id);
            return testConverter.fromTestToTestForSessionDto(byId);
        } catch (Exception e) {
            throw new DbException(e);
        }

    }

    @Override
    public Long save(Test test) {
        return testDao.save(test);
    }

    @Override
    public void update(Test test) {
        testDao.update(test);
    }
}
