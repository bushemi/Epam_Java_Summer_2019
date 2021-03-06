package com.bushemi.services.impl;

import com.bushemi.dao.SubjectDao;
import com.bushemi.dao.TestDao;
import com.bushemi.entities.Question;
import com.bushemi.entities.Subject;
import com.bushemi.entities.Test;
import com.bushemi.model.TestDto;
import com.bushemi.services.TestAssembler;
import com.bushemi.services.TestService;
import com.bushemi.services.UuidStringConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
public class TestServiceImpl implements TestService {
    private final TestDao testDao;
    private final SubjectDao subjectDao;
    private final TestAssembler testAssembler;
    private final UuidStringConverter converter;

    public TestServiceImpl(TestDao testDao,
                           SubjectDao subjectDao,
                           TestAssembler testAssembler,
                           UuidStringConverter converter) {
        this.testDao = testDao;
        this.subjectDao = subjectDao;
        this.testAssembler = testAssembler;
        this.converter = converter;
    }

    @Override
    @Transactional
    public void save(TestDto testDto) {
        Test test = testAssembler.assemble(testDto);

        testDao.create(test);
    }

    private void saveSubject(Test test) {
        Subject subject = test.getSubject();
        if (nonNull(subject)) {
            if (nonNull(subject.getId())) {
                subjectDao.update(subject);
            } else {
                subjectDao.create(subject);
                subject = subjectDao.findByName(subject.getSubjectName());
            }
            test.setSubject(subject);
        }
    }

    @Override
    @Transactional
    public void update(TestDto testDto) {
        Test persistedTest = testDao.read(converter.fromString(testDto.getId()));
        Test newTest = testAssembler.assemble(testDto);
        performUpdate(persistedTest, newTest);
    }

    private void performUpdate(Test persistentEntity, Test newEntity) {
        persistentEntity.setTestName(newEntity.getTestName());

        List<Question> persistedQuestions = persistentEntity.getQuestions();
        List<Question> newQuestions = newEntity.getQuestions();

        updateQuestions(persistedQuestions, newQuestions);

        Subject persistedSubject = persistentEntity.getSubject();
        Subject newSubject = newEntity.getSubject();

        if (persistedSubject.equals(newSubject)) {
            return;
        }
        if (persistedSubject.getId().equals(newSubject.getId())) {
            persistedSubject.setSubjectName(newSubject.getSubjectName());
            return;
        }
        persistentEntity.setSubject(newSubject);
    }

    private void updateQuestions(List<Question> persistedQuestions, List<Question> newQuestions) {
        Map<UUID, Question> stillExistentQuestions = newQuestions
                .stream()
                .filter(question -> Objects.nonNull(question.getId()))
                .collect(Collectors.toMap(Question::getId, Function.identity()));

        List<Question> questionsToAdd = newQuestions
                .stream()
                .filter(j -> Objects.isNull(j.getId()))
                .collect(Collectors.toList());

        Iterator<Question> persistentIterator = persistedQuestions.iterator();

        while (persistentIterator.hasNext()) {
            Question persistentQuestion = persistentIterator.next();
            if (stillExistentQuestions.containsKey(persistentQuestion.getId())) {
                Question updatedQuestion = stillExistentQuestions.get(persistentQuestion.getId());
                updateQuestion(persistentQuestion, updatedQuestion);
            } else {
                removeQuestion(persistentIterator, persistentQuestion);
            }
        }
        persistedQuestions.addAll(questionsToAdd);
    }

    private void removeQuestion(Iterator<Question> persistentIterator, Question persistentQuestion) {
        persistentIterator.remove();
        persistentQuestion.setTest(null); //  !!!
    }

    private void updateQuestion(Question persistentQuestion, Question updatedQuestion) {
        persistentQuestion.setMainText(updatedQuestion.getMainText());
    }


    @Override
    @Transactional(readOnly = true)
    public TestDto findById(String id) {
        Test read = testDao.read(converter.fromString(id));
        return testAssembler.assemble(read);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TestDto> findAll() {
        return testDao.findAll()
                .stream()
                .map(testAssembler::assemble)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        testDao.delete(converter.fromString(id));
    }
}
