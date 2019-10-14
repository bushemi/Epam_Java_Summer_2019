package com.bushemi.service.implementations;

import com.bushemi.converters.PassedTestConverter;
import com.bushemi.dao.entity.PassedTest;
import com.bushemi.dao.interfaces.PassedTestDao;
import com.bushemi.model.PassedTestForSessionDto;
import com.bushemi.service.interfaces.PassedTestsService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

@Service
public class PassedTestsServiceImpl implements PassedTestsService {
    private PassedTestDao passedTestDao;
    private PassedTestConverter passedTestConverter;

    public PassedTestsServiceImpl(PassedTestDao passedTestDao,
                                  PassedTestConverter passedTestConverter) {
        this.passedTestDao = passedTestDao;
        this.passedTestConverter = passedTestConverter;
    }

    @Override
    public void save(PassedTest passedTest) {
        PassedTest byUserIdAndTestId =
                passedTestDao.findByUserIdAndTestId(passedTest.getUserId(), passedTest.getTestId());
        if (isNull(byUserIdAndTestId)) {
            passedTestDao.save(passedTest);
        } else {
            passedTest.setId(byUserIdAndTestId.getId());
            passedTestDao.update(passedTest);
        }
    }

    @Override
    public Map<Long, PassedTestForSessionDto> findAllByUserId(Long userId) {
        Map<Long, PassedTestForSessionDto> passedTests = new HashMap<>();
        passedTestDao.findAllByUserId(userId)
                .stream()
                .map(passedTestConverter::passedTestToPassedTestForSessionDto)
                .forEach(passedTest -> passedTests.put(passedTest.getTestId(), passedTest));

        return passedTests;
    }
}
