package com.mejuloli.os_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mejuloli.os_management.model.ServiceOrder;

// ===== repositório jpa responsável por operações com ordens de serviço =====
public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {
}
