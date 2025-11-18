package com.mejuloli.os_management.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "service_orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceOrder {

    // ===== identificador único da ordem de serviço =====
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ===== cliente vinculado à ordem de serviço =====
    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id")
    @NotNull
    private Client client;

    // ===== técnico responsável que pode ser definido depois =====
    @ManyToOne
    @JoinColumn(name = "technician_id")
    private Technician technician;

    // ===== descrição obrigatória do problema ou solicitação =====
    @NotBlank
    @Column(nullable = false)
    private String description;

    // ===== prioridade obrigatória da ordem de serviço =====
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ServiceOrderPriority priority;

    // ===== status atual da ordem de serviço =====
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ServiceOrderStatus status;

    // ===== data e hora em que a ordem foi aberta =====
    private LocalDateTime openedAt;

    // ===== data e hora em que a ordem foi encerrada =====
    private LocalDateTime closedAt;
}
