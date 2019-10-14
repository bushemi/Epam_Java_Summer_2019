package com.bushemi.converters;

import com.bushemi.dao.entity.Question;
import com.bushemi.model.QuestionForSessionDto;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class QuestionConverter {
    public QuestionForSessionDto questionToQuestionForSessionDto(Question question) {
        QuestionForSessionDto questionForSessionDto = new QuestionForSessionDto();

        questionForSessionDto.setId(question.getId());
        questionForSessionDto.setMainText(question.getMainText());

        return questionForSessionDto;
    }
}
