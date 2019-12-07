package com.bushemi.repositories;

import com.bushemi.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TestRepository extends JpaRepository<Test, UUID> {
    void deleteById(UUID id);
}
