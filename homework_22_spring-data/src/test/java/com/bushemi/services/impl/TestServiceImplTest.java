package com.bushemi.services.impl;

import com.bushemi.BasicSpringTest;
import com.bushemi.model.QuestionDto;
import com.bushemi.model.SubjectDto;
import com.bushemi.model.TestDto;
import com.bushemi.services.TestService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;


public class TestServiceImplTest extends BasicSpringTest {

    @Autowired
    private TestService testService;

    @Test
    @Transactional
    public void save() {
        //GIVEN
        TestDto testDto = getTestDto("123-save", "asd-save");
        int sizeBeforeSaving = testService.findAll().size();

        //WHEN
        testService.save(testDto);

        //THEN
        int sizeAfterSaving = testService.findAll().size();
        assertThat(sizeBeforeSaving, is(0));
        assertThat(sizeAfterSaving, is(1));
    }

    @Test
    @Transactional
    public void update() {
        //GIVEN
        final String testNameBefore = "123-update-before";
        final String testNameAfter = "123-update-after";
        TestDto testDto = getTestDto(testNameBefore, "asd-update");
        testService.save(testDto);
        Optional<TestDto> optionalTestDto =
                testService.findAll()
                        .stream()
                        .filter(testDto1 -> testDto1.getTestName().equals(testNameBefore))
                        .findAny();

        TestDto testForUpdate =
                optionalTestDto
                        .orElseThrow(() -> new RuntimeException("No test with given name"));
        testForUpdate.setTestName(testNameAfter);

        //WHEN
        testService.update(testForUpdate);

        //THEN
        TestDto byId = testService.findById(testForUpdate.getId());
        assertNotNull(byId);
        assertThat(testNameAfter, is(byId.getTestName()));
    }

    @Test
    @Transactional
    public void updateQuestions() {
        //GIVEN
        final String testNameBefore = "123-updateQuestions-before";
        final String testNameAfter = "123-updateQuestions-after";
        TestDto testDto = getTestDto(testNameBefore, "asd-updateQuestions");
        testDto.setQuestionDtos(asList(new QuestionDto("update-1"), new QuestionDto("update-2")));
        testService.save(testDto);
        Optional<TestDto> optionalTestDto =
                testService.findAll()
                        .stream()
                        .filter(testDto1 -> testDto1.getTestName().equals(testNameBefore))
                        .findAny();

        TestDto testForUpdate =
                optionalTestDto
                        .orElseThrow(() -> new RuntimeException("No test with given name"));
        List<QuestionDto> questionDtosBeforeUpdate = testForUpdate.getQuestionDtos();
        QuestionDto questionDto = questionDtosBeforeUpdate.get(0);
        testForUpdate.setQuestionDtos(asList(new QuestionDto("update-3"), questionDto));

        //WHEN
        testService.update(testForUpdate);

        //THEN
        TestDto byId = testService.findById(testForUpdate.getId());
        List<QuestionDto> questionDtosAfterUpdate = byId.getQuestionDtos();
        assertThat(questionDtosAfterUpdate.size(), is(2));
        assertTrue(questionDtosAfterUpdate.contains(questionDto));
        assertFalse(questionDtosAfterUpdate.contains(questionDtosBeforeUpdate.get(1)));
    }

    private TestDto getTestDto(String testName, String subjectName) {
        TestDto testDto = new TestDto();
        testDto.setTestName(testName);
        SubjectDto subject = new SubjectDto();
        subject.setSubjectName(subjectName);
        testDto.setSubject(subject);
        String questionName = testName + "=" + subjectName;
        QuestionDto questionDto = new QuestionDto(questionName);
        testDto.setQuestionDtos(Collections.singletonList(questionDto));
        return testDto;
    }

    @Test
    @Transactional
    public void findById() {
        //GIVEN
        final String testName = "123-findById";
        TestDto testDto = getTestDto(testName, "asd-findById");
        testService.save(testDto);
        Optional<TestDto> optionalTestDto =
                testService.findAll()
                        .stream()
                        .filter(testDto1 -> testDto1.getTestName().equals(testName))
                        .findAny();
        TestDto savedTest = optionalTestDto
                .orElseThrow(() -> new RuntimeException("Test not found"));
        //WHEN
        TestDto byId = testService.findById(savedTest.getId());
        //THEN
        assertThat(byId.getTestName(), is(equalTo(testName)));
        assertThat(byId.getSubject().getSubjectName(), is(equalTo(testDto.getSubject().getSubjectName())));
    }

    @Test
    @Transactional
    public void findAll() {
        //GIVEN
        final String testName = "123-findAll";
        TestDto testDto = getTestDto(testName, "asd-findAll");
        testService.save(testDto);

        //WHEN
        Optional<TestDto> optionalTestDto =
                testService.findAll()
                        .stream()
                        .filter(testDto1 -> testDto1.getTestName().equals(testName))
                        .findAny();
        //THEN
        assertTrue(optionalTestDto.isPresent());
    }

    @Test
    @Transactional
    public void deleteById() {
        //GIVEN
        final String testName = "123-deleteById";
        TestDto testDto = getTestDto(testName, "asd-deleteById");
        testService.save(testDto);
        Optional<TestDto> optionalTestDto =
                testService.findAll()
                        .stream()
                        .filter(testDto1 -> testDto1.getTestName().equals(testName))
                        .findAny();
        TestDto savedTest = optionalTestDto
                .orElseThrow(() -> new RuntimeException("Test not found"));

        //WHEN
        testService.deleteById(savedTest.getId());

        //THEN
        TestDto byId = testService.findById(savedTest.getId());
        assertNull(byId);
    }
}