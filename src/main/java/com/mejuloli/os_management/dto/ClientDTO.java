package com.mejuloli.os_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClientDTO {

    // ===== id do cliente usado em operações de leitura =====
    private Long id;

    // ===== nome do cliente obrigatório na criação e atualização =====
    @NotBlank
    private String name;

    // ===== email opcional com validação de formato =====
    @Email
    private String email;

    // ===== telefone de contato opcional =====
    private String phone;
}
