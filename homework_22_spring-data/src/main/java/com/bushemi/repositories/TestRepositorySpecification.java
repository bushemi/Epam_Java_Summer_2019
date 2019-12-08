package com.bushemi.repositories;

import com.bushemi.entities.Test;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public class TestRepositorySpecification {
    public static Specification<Test> findByTestParameters(Test parameters) {
        return new Specification<Test>() {
            @Override
            public Predicate toPredicate(Root<Test> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                List<Predicate> predicates = new ArrayList<>();
                if (nonNull(parameters.getTestName())) {
                    Predicate nameEqual = builder.equal(root.get("testName"), parameters.getTestName());
                    predicates.add(nameEqual);
                }
                if (nonNull(parameters.getDifficulty())) {
                    Predicate difficultyEqual = builder.equal(root.get("difficulty"), parameters.getDifficulty());
                    predicates.add(difficultyEqual);
                }
                return builder.and(predicates.toArray(new Predicate[0]));
            }
        };
    }
}
