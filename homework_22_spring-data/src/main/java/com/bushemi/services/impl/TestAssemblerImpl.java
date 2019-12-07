package com.bushemi.services.impl;

import com.bushemi.entities.Question;
import com.bushemi.entities.Subject;
import com.bushemi.entities.Test;
import com.bushemi.model.QuestionDto;
import com.bushemi.model.SubjectDto;
import com.bushemi.model.TestDto;
import com.bushemi.services.QuestionAssembler;
import com.bushemi.services.SubjectAssembler;
import com.bushemi.services.TestAssembler;
import com.bushemi.services.UuidStringConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class TestAssemblerImpl implements TestAssembler {
    private final SubjectAssembler subjectAssembler;
    private final QuestionAssembler questionAssembler;
    private final UuidStringConverter uuidConverter;

    public TestAssemblerImpl(SubjectAssembler subjectAssembler,
                             QuestionAssembler questionAssembler,
                             UuidStringConverter uuidConverter) {
        this.subjectAssembler = subjectAssembler;
        this.questionAssembler = questionAssembler;
        this.uuidConverter = uuidConverter;
    }

    @Override
    public Test assemble(TestDto dto) {
        Test entity = new Test();
        entity.setId(uuidConverter.fromString(dto.getId()));
        Subject subject = subjectAssembler.assemble(dto.getSubject());
        entity.setSubject(subject);
        entity.setTestName(dto.getTestName());
        List<Question> questions = dto.getQuestionDtos()
                .stream()
                .map(questionAssembler::assemble)
                .collect(Collectors.toList());
        entity.setQuestions(questions);
        return entity;
    }

    @Override
    public TestDto assemble(Test entity) {
        if (isNull(entity)) return null;
        TestDto dto = new TestDto();
        dto.setId(uuidConverter.fromUUID(entity.getId()));
        SubjectDto subject = subjectAssembler.assemble(entity.getSubject());
        dto.setSubject(subject);
        dto.setTestName(entity.getTestName());
        List<QuestionDto> questions = entity.getQuestions()
                .stream()
                .peek(question -> question.setTest(entity))
                .map(questionAssembler::assemble)
                .collect(Collectors.toList());
        dto.setQuestionDtos(questions);

        return dto;
    }
}
