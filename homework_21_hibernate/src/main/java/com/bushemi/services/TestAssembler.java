package com.bushemi.services;

import com.bushemi.entities.Test;
import com.bushemi.model.TestDto;

public interface TestAssembler {
    Test assemble(TestDto dto);

    TestDto assemble(Test entity);
}
