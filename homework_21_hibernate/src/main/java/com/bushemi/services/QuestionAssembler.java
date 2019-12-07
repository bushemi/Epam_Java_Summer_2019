package com.bushemi.services;

import com.bushemi.entities.Question;
import com.bushemi.model.QuestionDto;

public interface QuestionAssembler {
    Question assemble(QuestionDto dto);
    QuestionDto assemble(Question dto);
}
