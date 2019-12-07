package com.bushemi.services;

import com.bushemi.entities.Subject;
import com.bushemi.model.SubjectDto;

public interface SubjectAssembler {
    Subject assemble(SubjectDto obj);

    SubjectDto assemble(Subject obj);
}
