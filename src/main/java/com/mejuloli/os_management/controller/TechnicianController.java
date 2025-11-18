package com.mejuloli.os_management.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mejuloli.os_management.dto.TechnicianDTO;
import com.mejuloli.os_management.service.TechnicianService;

import jakarta.validation.Valid;

// ===== expõe endpoints rest relacionados a técnicos =====
@RestController
@RequestMapping("/api/technicians")
public class TechnicianController {

    private final TechnicianService service;

    public TechnicianController(TechnicianService service) {
        this.service = service;
    }

    // ===== retorna a lista de todos os técnicos =====
    @GetMapping
    public ResponseEntity<List<TechnicianDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // ===== retorna um técnico específico pelo id =====
    @GetMapping("/{id}")
    public ResponseEntity<TechnicianDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    // ===== cria um novo técnico a partir do corpo da requisição =====
    @PostMapping
    public ResponseEntity<TechnicianDTO> create(@Valid @RequestBody TechnicianDTO dto) {
        TechnicianDTO created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // ===== atualiza um técnico existente com base no id =====
    @PutMapping("/{id}")
    public ResponseEntity<TechnicianDTO> update(@PathVariable Long id,
            @Valid @RequestBody TechnicianDTO dto) {
        TechnicianDTO updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    // ===== exclui um técnico pelo id =====
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
