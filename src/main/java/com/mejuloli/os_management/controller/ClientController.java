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

import com.mejuloli.os_management.dto.ClientDTO;
import com.mejuloli.os_management.service.ClientService;

import jakarta.validation.Valid;

// ===== expõe endpoints rest relacionados a clientes =====
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    // ===== retorna a lista de todos os clientes =====
    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // ===== retorna um cliente específico pelo id =====
    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    // ===== cria um novo cliente a partir do corpo da requisição =====
    @PostMapping
    public ResponseEntity<ClientDTO> create(@Valid @RequestBody ClientDTO dto) {
        ClientDTO created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // ===== atualiza um cliente existente com base no id =====
    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Long id,
            @Valid @RequestBody ClientDTO dto) {
        ClientDTO updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    // ===== exclui um cliente pelo id =====
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
