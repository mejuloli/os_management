package com.mejuloli.os_management.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mejuloli.os_management.dto.ClientDTO;
import com.mejuloli.os_management.exception.ResourceNotFoundException;
import com.mejuloli.os_management.model.Client;
import com.mejuloli.os_management.repository.ClientRepository;

// ===== contém regras de negócio relacionadas a clientes =====
@Service
public class ClientService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    // ===== busca todos os clientes convertendo para dto =====
    public List<ClientDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ===== busca um cliente por id ou lança exceção =====
    public ClientDTO findById(Long id) {
        Client client = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("client not found: " + id));
        return toDTO(client);
    }

    // ===== cria um novo cliente a partir de um dto =====
    public ClientDTO create(ClientDTO dto) {
        Client entity = toEntity(dto);
        entity.setId(null);
        Client saved = repository.save(entity);
        return toDTO(saved);
    }

    // ===== atualiza dados de um cliente existente =====
    public ClientDTO update(Long id, ClientDTO dto) {
        Client existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("client not found: " + id));

        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setPhone(dto.getPhone());

        Client updated = repository.save(existing);
        return toDTO(updated);
    }

    // ===== remove um cliente pelo id =====
    public void delete(Long id) {
        Client existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("client not found: " + id));
        repository.delete(existing);
    }

    // ===== converte entidade client em clientdto =====
    private ClientDTO toDTO(Client entity) {
        ClientDTO dto = new ClientDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        return dto;
    }

    // ===== converte clientdto em entidade client =====
    private Client toEntity(ClientDTO dto) {
        return Client.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .build();
    }
}
