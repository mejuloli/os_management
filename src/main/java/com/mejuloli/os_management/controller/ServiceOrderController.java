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

import com.mejuloli.os_management.dto.ServiceOrderDTO;
import com.mejuloli.os_management.service.ServiceOrderService;

import jakarta.validation.Valid;

// ===== expõe endpoints rest relacionados a ordens de serviço =====
@RestController
@RequestMapping("/api/orders")
public class ServiceOrderController {

    private final ServiceOrderService service;

    public ServiceOrderController(ServiceOrderService service) {
        this.service = service;
    }

    // ===== retorna a lista de todas as ordens de serviço =====
    @GetMapping
    public ResponseEntity<List<ServiceOrderDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // ===== retorna uma ordem específica pelo id =====
    @GetMapping("/{id}")
    public ResponseEntity<ServiceOrderDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    // ===== cria uma nova ordem de serviço =====
    @PostMapping
    public ResponseEntity<ServiceOrderDTO> create(@Valid @RequestBody ServiceOrderDTO dto) {
        ServiceOrderDTO created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // ===== atualiza uma ordem de serviço existente =====
    @PutMapping("/{id}")
    public ResponseEntity<ServiceOrderDTO> update(@PathVariable Long id,
            @Valid @RequestBody ServiceOrderDTO dto) {
        ServiceOrderDTO updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    // ===== exclui uma ordem de serviço pelo id =====
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
