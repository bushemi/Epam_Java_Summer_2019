package com.bushemi.repositories;

import com.bushemi.BasicSpringTest;
import com.bushemi.entities.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

public class TestRepositoryTest extends BasicSpringTest {

    @Autowired
    private TestRepository testRepository;

    @org.junit.Test
    @Transactional
    public void findAllWithDifficultMoreThan() {
        //GIVEN
        int index = 4;
        List<Test> tests = asList(getTests(index++),
                getTests(index++), getTests(index++),
                getTests(index));
        testRepository.saveAll(tests);
        List<Test> all = testRepository.findAll();

        //WHEN
        List<Test> allWithDifficultyMoreThan =
                testRepository.findAllWithDifficultyMoreThan(5);

        //THEN
        assertThat(all, hasSize(4));
        assertThat(allWithDifficultyMoreThan, hasSize(2));
    }

    @org.junit.Test
    @Transactional
    public void findAllWithTestParametersByTestName() {
        //GIVEN
        int index = 10;
        List<Test> tests = asList(getTests(index++), getTests(index));
        testRepository.saveAll(tests);
        Test parameters = new Test();
        parameters.setTestName("Test=10");
        Specification<Test> byTestParameters =
                TestRepositorySpecification.findByTestParameters(parameters);

        //WHEN
        List<Test> allByParameters =
                testRepository.findAll(byTestParameters);

        //THEN
        assertThat(allByParameters, hasSize(1));
    }

    @org.junit.Test
    @Transactional
    public void findAllWithTestParametersByDifficulty() {
        //GIVEN
        int index = 20;
        List<Test> tests = asList(getTests(index++), getTests(index));
        testRepository.saveAll(tests);
        Test parameters = new Test();
        parameters.setDifficulty(21);
        Specification<Test> byTestParameters =
                TestRepositorySpecification.findByTestParameters(parameters);

        //WHEN
        List<Test> allByParameters =
                testRepository.findAll(byTestParameters);

        //THEN
        assertThat(allByParameters, hasSize(1));
    }

    private Test getTests(int index) {
        Test test = new Test();
        test.setTestName("Test=" + index);
        test.setDifficulty(index);

        return test;
    }
}