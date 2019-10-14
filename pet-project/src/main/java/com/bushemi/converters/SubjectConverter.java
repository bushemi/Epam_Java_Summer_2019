package com.bushemi.converters;

import com.bushemi.dao.entity.Subject;
import com.bushemi.model.SubjectDto;
import org.springframework.stereotype.Component;

@Component
public class SubjectConverter {
    public SubjectDto subjectToSubjectDto(Subject subject) {
        SubjectDto subj = new SubjectDto();
        subj.setId(subject.getId());
        subj.setSubjectName(subject.getSubjectName());

        return subj;
    }
}
