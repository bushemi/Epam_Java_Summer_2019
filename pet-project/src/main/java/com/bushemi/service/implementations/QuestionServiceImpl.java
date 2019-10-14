package com.bushemi.service.implementations;

import com.bushemi.converters.OptionsConverter;
import com.bushemi.converters.QuestionConverter;
import com.bushemi.dao.interfaces.OptionDao;
import com.bushemi.dao.interfaces.QuestionDao;
import com.bushemi.model.OptionForSessionDto;
import com.bushemi.model.QuestionForSessionDto;
import com.bushemi.service.interfaces.QuestionService;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class QuestionServiceImpl implements QuestionService {
    private QuestionDao questionDao;
    private OptionDao optionDao;
    private QuestionConverter questionConverter;
    private OptionsConverter optionsConverter;

    public QuestionServiceImpl(QuestionDao questionDao,
                               OptionDao optionDao,
                               QuestionConverter questionConverter,
                               OptionsConverter optionsConverter) {
        this.questionDao = questionDao;
        this.optionDao = optionDao;
        this.questionConverter = questionConverter;
        this.optionsConverter = optionsConverter;
    }

    @Override
    public List<QuestionForSessionDto> findQuestionsByTestId(Long testId) {
        return questionDao.findAllByTestId(testId)
                .stream()
                .map(questionConverter::questionToQuestionForSessionDto)
                .peek(question -> question.setOptions(getOptions(question)))
                .collect(toList());
    }

    private List<OptionForSessionDto> getOptions(QuestionForSessionDto question) {
        return optionDao.finAllByQuestionId(question.getId()).stream().map(optionsConverter::optionToOptionForSessionDto).collect(toList());
    }
}
