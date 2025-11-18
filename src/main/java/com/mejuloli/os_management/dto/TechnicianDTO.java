package com.mejuloli.os_management.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TechnicianDTO {

    // ===== id do técnico usado em operações de leitura =====
    private Long id;

    // ===== nome do técnico obrigatório =====
    @NotBlank
    private String name;

    // ===== especialidade opcional do técnico =====
    private String specialty;
}
