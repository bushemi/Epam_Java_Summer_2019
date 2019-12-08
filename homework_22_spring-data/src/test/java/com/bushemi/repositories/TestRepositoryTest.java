package com.bushemi.repositories;

import com.bushemi.BasicSpringTest;
import com.bushemi.entities.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

public class TestRepositoryTest extends BasicSpringTest {
    private static int difficulty = 4;
    @Autowired
    private TestRepository testRepository;

    @org.junit.Test
    @Transactional
    public void findAllWithDifficultMoreThan() {
        //GIVEN
        List<Test> tests = asList(getTests(), getTests(), getTests(), getTests());
        testRepository.saveAll(tests);
        List<Test> all = testRepository.findAll();

        //WHEN
        List<Test> allWithDifficultyMoreThan =
                testRepository.findAllWithDifficultyMoreThan(5);

        //THEN
        assertThat(all, hasSize(4));
        assertThat(allWithDifficultyMoreThan, hasSize(2));
    }

    private Test getTests() {
        Test test = new Test();
        test.setTestName("Test=" + difficulty);
        test.setDifficulty(difficulty++);

        return test;
    }
}