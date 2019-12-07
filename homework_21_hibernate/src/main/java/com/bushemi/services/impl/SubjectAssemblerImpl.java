package com.bushemi.services.impl;

import com.bushemi.entities.Subject;
import com.bushemi.model.SubjectDto;
import com.bushemi.services.SubjectAssembler;
import com.bushemi.services.UuidStringConverter;
import org.springframework.stereotype.Service;

@Service
public class SubjectAssemblerImpl implements SubjectAssembler {

    private final UuidStringConverter uuidConverter;

    public SubjectAssemblerImpl(UuidStringConverter uuidConverter) {
        this.uuidConverter = uuidConverter;
    }

    @Override
    public Subject assemble(SubjectDto obj) {
        Subject subject = new Subject();
        subject.setId(uuidConverter.fromString(obj.getId()));
        subject.setSubjectName(obj.getSubjectName());

        return subject;
    }

    @Override
    public SubjectDto assemble(Subject obj) {
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(uuidConverter.fromUUID(obj.getId()));
        subjectDto.setSubjectName(obj.getSubjectName());

        return subjectDto;
    }
}
