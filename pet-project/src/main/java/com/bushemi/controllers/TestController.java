package com.bushemi.controllers;

import com.bushemi.dao.entity.Test;
import com.bushemi.model.PassedTestForSessionDto;
import com.bushemi.model.SubjectDto;
import com.bushemi.model.TestForSessionDto;
import com.bushemi.model.TestForTestsPage;
import com.bushemi.service.interfaces.PassedTestsService;
import com.bushemi.service.interfaces.SubjectService;
import com.bushemi.service.interfaces.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Objects.nonNull;

@Controller
@RequestMapping
public class TestController {

    private final SubjectService service;
    private final TestService testService;
    private final PassedTestsService passedTestsService;

    @Autowired
    public TestController(SubjectService service,
                          TestService testService,
                          PassedTestsService passedTestsService) {
        this.service = service;
        this.testService = testService;
        this.passedTestsService = passedTestsService;
    }

    @GetMapping("/addTest")
    public String addTest(HttpSession session) {
        List<SubjectDto> subjectDtos = service.findAll();
        session.setAttribute("subjects", subjectDtos);
        return "addTest";
    }

    @GetMapping("/editTest/{testId}")
    public String editTest(HttpSession session, @PathVariable("testId") Long testId) {
        if (nonNull(testId)) {
            TestForSessionDto testById = testService.findTestById(testId);
            session.setAttribute("test", testById);
        }
        return "editTest";
    }

    @PostMapping("/editTest")
    public String saveTest(HttpSession session, Test test) {
        if (nonNull(test.getId())) {
            Long savedId = testService.save(test);
            session.setAttribute("editTestId", savedId);
        } else {
            testService.update(test);
            session.setAttribute("editTestId", test.getId());
        }
        return "editTest";
    }

    @GetMapping("/tests")
    public String tests(HttpSession session) {
        List<TestForTestsPage> testsForTestsPages = testService.findAllTests();
        long userId = (long) session.getAttribute("userId");
        enhanceTestsWithPassedTestInformation(testsForTestsPages, userId);
        session.setAttribute("tests", testsForTestsPages);
        return "tests";
    }

    private void enhanceTestsWithPassedTestInformation(List<TestForTestsPage> testsForTestsPages, long userId) {
        Map<Long, PassedTestForSessionDto> allByUserId = passedTestsService.findAllByUserId(userId);
        Set<Long> testIds = allByUserId.keySet();
        testsForTestsPages.stream()
                .filter(test -> testIds.contains(test.getId()))
                .peek(test -> test.setCorrectAnswers(allByUserId.get(test.getId()).getCorrectAnswers()))
                .forEach(test -> test.setSpentTime(allByUserId.get(test.getId()).getSpentTime()));
    }

}
