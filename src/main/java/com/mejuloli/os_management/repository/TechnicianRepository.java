package com.mejuloli.os_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mejuloli.os_management.model.Technician;

// ===== repositório jpa responsável por operações com técnicos =====
public interface TechnicianRepository extends JpaRepository<Technician, Long> {
}
