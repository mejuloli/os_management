package com.mejuloli.os_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mejuloli.os_management.model.Client;

// ===== repositório jpa responsável por operações com clientes =====
public interface ClientRepository extends JpaRepository<Client, Long> {
}
