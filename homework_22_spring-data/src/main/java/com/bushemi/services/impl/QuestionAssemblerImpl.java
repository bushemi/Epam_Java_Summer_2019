package com.bushemi.services.impl;

import com.bushemi.entities.Question;
import com.bushemi.entities.Test;
import com.bushemi.model.QuestionDto;
import com.bushemi.repositories.TestRepository;
import com.bushemi.services.QuestionAssembler;
import com.bushemi.services.UuidStringConverter;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class QuestionAssemblerImpl implements QuestionAssembler {
    private final TestRepository dao;
    private final UuidStringConverter converter;

    public QuestionAssemblerImpl(TestRepository dao, UuidStringConverter converter) {
        this.dao = dao;
        this.converter = converter;
    }

    @Override
    public Question assemble(QuestionDto dto) {
        Question question = new Question();
        question.setId(converter.fromString(dto.getId()));
        question.setMainText(dto.getMainText());

        Test read = null;
        if (nonNull(dto.getTestId())) {
            read = dao.findById(converter.fromString(dto.getTestId())).orElseGet(null);
        }
        question.setTest(read);

        return question;
    }

    @Override
    public QuestionDto assemble(Question question) {
        QuestionDto dto = new QuestionDto();
        dto.setId(converter.fromUUID(question.getId()));
        dto.setMainText(question.getMainText());
        dto.setTestId(converter.fromUUID(question.getTest().getId()));
        return dto;
    }
}
