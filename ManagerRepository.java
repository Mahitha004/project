package com.web.employee.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.employee.model.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, UUID> {
    // Updated method signature to match the field name in the Manager class
    Optional<Manager> findByManagerIdAndIsActive(UUID managerId, Boolean isActive);
}
