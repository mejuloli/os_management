package com.mejuloli.os_management.dto;

import java.time.LocalDateTime;

import com.mejuloli.os_management.model.ServiceOrderPriority;
import com.mejuloli.os_management.model.ServiceOrderStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ServiceOrderDTO {

    // ===== id da ordem de serviço usado em operações de leitura =====
    private Long id;

    // ===== id do cliente obrigatório para vincular a ordem =====
    @NotNull
    private Long clientId;

    // ===== id do técnico opcional para atribuição da ordem =====
    private Long technicianId;

    // ===== descrição obrigatória da ordem =====
    @NotBlank
    private String description;

    // ===== prioridade obrigatória da ordem de serviço =====
    @NotNull
    private ServiceOrderPriority priority;

    // ===== status atual da ordem controlado pela regra de negócio =====
    private ServiceOrderStatus status;

    // ===== data de abertura apenas para leitura =====
    private LocalDateTime openedAt;

    // ===== data de fechamento apenas para leitura =====
    private LocalDateTime closedAt;
}
