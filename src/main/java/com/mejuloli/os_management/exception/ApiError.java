package com.mejuloli.os_management.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

// ===== modelo de resposta de erro padronizado para a api =====
@Data
@AllArgsConstructor
public class ApiError {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
