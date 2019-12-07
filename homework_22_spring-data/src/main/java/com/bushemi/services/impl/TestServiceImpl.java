package com.bushemi.services.impl;

import com.bushemi.entities.Question;
import com.bushemi.entities.Subject;
import com.bushemi.entities.Test;
import com.bushemi.model.TestDto;
import com.bushemi.repositories.SubjectRepository;
import com.bushemi.repositories.TestRepository;
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
    private final TestRepository testDao;
    private final SubjectRepository subjectDao;
    private final TestAssembler testAssembler;
    private final UuidStringConverter converter;

    public TestServiceImpl(TestRepository testDao,
                           SubjectRepository subjectDao,
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

        testDao.save(test);
    }

    private void saveSubject(Test test) {
        Subject subject = test.getSubject();
        if (nonNull(subject)) {
            if (nonNull(subject.getId())) {
                subjectDao.save(subject);
            } else {
                subjectDao.save(subject);
                subject = subjectDao.findBySubjectName(subject.getSubjectName());
            }
            test.setSubject(subject);
        }
    }

    @Override
    @Transactional
    public void update(TestDto testDto) {
        Test persistedTest = testDao.findById(converter.fromString(testDto.getId())).get();
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
    @Transactional
    public TestDto findById(String id) {
        Optional<Test> byId = testDao.findById(converter.fromString(id));
        if (!byId.isPresent()) return null;
        Test read = byId.get();
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
        testDao.deleteById(converter.fromString(id));
    }
}
