package com.bushemi.service.implementations;

import com.bushemi.converters.SubjectConverter;
import com.bushemi.dao.entity.Subject;
import com.bushemi.dao.interfaces.SubjectDao;
import com.bushemi.model.SubjectDto;
import com.bushemi.service.interfaces.SubjectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {

    private SubjectDao subjectDao;
    private SubjectConverter subjectConverter;

    public SubjectServiceImpl(SubjectDao subjectDao,
                              SubjectConverter subjectConverter) {
        this.subjectDao = subjectDao;
        this.subjectConverter = subjectConverter;
    }

    @Override
    public List<SubjectDto> findAll() {
        return subjectDao.findAll()
                .stream()
                .map(subjectConverter::subjectToSubjectDto)
                .collect(Collectors.toList());
    }

    @Override
    public SubjectDto findById(Long id) {
        Subject subject = subjectDao.findById(id);
        return subjectConverter.subjectToSubjectDto(subject);
    }
}
