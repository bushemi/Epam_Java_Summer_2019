package com.bushemi.repositories;

import com.bushemi.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TestRepository extends JpaRepository<Test, UUID> {
    void deleteById(UUID id);

    @Query("SELECT t FROM Test t WHERE t.difficulty > ?1")
    List<Test> findAllWithDifficultyMoreThan(int minDifficult);
}
