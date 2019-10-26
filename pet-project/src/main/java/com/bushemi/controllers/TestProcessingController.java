package com.bushemi.controllers;

import com.bushemi.dao.entity.PassedTest;
import com.bushemi.model.OptionForSessionDto;
import com.bushemi.model.QuestionForSessionDto;
import com.bushemi.model.RedirectResponse;
import com.bushemi.model.TestForSessionDto;
import com.bushemi.service.interfaces.PassedTestsService;
import com.bushemi.service.interfaces.QuestionService;
import com.bushemi.service.interfaces.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.Objects.nonNull;

@Controller
@RequestMapping
public class TestProcessingController {
    private static final Logger LOG = LoggerFactory.getLogger("TestProcessingController");
    private static final String COUNT_RIGHT_ANSWERS = "countRightAnswers";
    @Autowired
    private PassedTestsService passedTestsService;
    @Autowired
    private TestService testService;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/testToGo/{testId}")
    public String testing(HttpSession session, @PathVariable("testId") Long testId) {
        if (nonNull(testId)) {
            TestForSessionDto testById;
            try {
                testById = testService.findTestById(testId);
            } catch (Exception e) {
                LOG.error("error with find by id = {}", e);
                return "404";
            }

            LocalDateTime localDateTime = LocalDateTime.now().plusSeconds(testById.getSecondsToComplete());
            long time = convertToDateViaInstant(localDateTime).getTime();
            List<QuestionForSessionDto> questionsByTestId = questionService.findQuestionsByTestId(testId);

            session.setAttribute("startTime", LocalDateTime.now());
            session.setAttribute("finalTime", time);
            session.setAttribute("testId", testId);
            session.setAttribute("questions", questionsByTestId);
            session.setAttribute("currentQuestion", 0);
            session.setAttribute("countRightAnswers", 0);
            session.setAttribute("countWrongAnswers", 0);
            return "testing";
        }
        return "404";
    }

    private Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }

    @GetMapping("/testInProcess")
    public String testInProcess(HttpSession session) {
        LOG.info("user with session id = {}", session.getId());
        List<QuestionForSessionDto> questions = (ArrayList<QuestionForSessionDto>) session.getAttribute("questions");
        Integer currentQuestion = (Integer) session.getAttribute("currentQuestion");
        if (nonNull(questions) && nonNull(currentQuestion)) {
            QuestionForSessionDto questionForSessionDto = questions.get(currentQuestion);
            LOG.info("put question {} into session = {}", questionForSessionDto, session.getId());
            session.setAttribute("question", questionForSessionDto);
            return "testInProcess";
        }

        return "404";
    }

    @PostMapping("/testInProcess")
    @ResponseBody
    public RedirectResponse nextQuestion(HttpSession session, @RequestBody List<Integer> answerIds) {
        LOG.info("user with session id = {}", session.getId());
        List<QuestionForSessionDto> questions = (ArrayList<QuestionForSessionDto>) session.getAttribute("questions");
        Integer currentQuestion = (Integer) session.getAttribute("currentQuestion");
        validateResults(session, answerIds);
        if (nonNull(questions) && nonNull(currentQuestion)) {
            currentQuestion++;
            if (currentQuestion < questions.size()) {
                session.setAttribute("currentQuestion", currentQuestion);
                return new RedirectResponse("/testInProcess");
            } else {
                int countRightAnswers = (int) session.getAttribute(COUNT_RIGHT_ANSWERS);
                long testId = (long) session.getAttribute("testId");
                long userId = (long) session.getAttribute("userId");
                session.setAttribute("question", null);
                session.setAttribute("currentQuestion", null);
                session.setAttribute("questions", null);
                LocalDateTime startTime = (LocalDateTime) session.getAttribute("startTime");
                long spentSeconds = ChronoUnit.SECONDS.between(startTime, LocalDateTime.now());
                LOG.info("test completed by user with id = {}. Spent time {} for test {}", userId, spentSeconds, userId);
                session.setAttribute("spentTime", spentSeconds);
                passedTestsService.save(new PassedTest(null, testId, userId, countRightAnswers, (int) spentSeconds));
                return new RedirectResponse("testResult");
            }
        }
        return new RedirectResponse("404");
    }

    private void validateResults(HttpSession session, List<Integer> indexesOfAnswers) {
        QuestionForSessionDto question = (QuestionForSessionDto) session.getAttribute("question");
        List<OptionForSessionDto> options = question.getOptions();
        int countRightAnswers = (int) session.getAttribute(COUNT_RIGHT_ANSWERS);
        for (OptionForSessionDto option : options) {
            if (indexesOfAnswers.contains(option.getId().intValue()) && option.isCorrect()) {
                countRightAnswers++;
            }
        }
        session.setAttribute(COUNT_RIGHT_ANSWERS, countRightAnswers);
    }

    @GetMapping("/testResult")
    public String testResult() {
        return "testResult";
    }
}
