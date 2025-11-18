package com.mejuloli.os_management.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mejuloli.os_management.dto.TechnicianDTO;
import com.mejuloli.os_management.exception.ResourceNotFoundException;
import com.mejuloli.os_management.model.Technician;
import com.mejuloli.os_management.repository.TechnicianRepository;

// ===== contém regras de negócio relacionadas a técnicos =====
@Service
public class TechnicianService {

    private final TechnicianRepository repository;

    public TechnicianService(TechnicianRepository repository) {
        this.repository = repository;
    }

    // ===== busca todos os técnicos convertendo para dto =====
    public List<TechnicianDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ===== busca um técnico por id ou lança exceção =====
    public TechnicianDTO findById(Long id) {
        Technician technician = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("technician not found: " + id));
        return toDTO(technician);
    }

    // ===== cria um novo técnico a partir de um dto =====
    public TechnicianDTO create(TechnicianDTO dto) {
        Technician entity = toEntity(dto);
        entity.setId(null);
        Technician saved = repository.save(entity);
        return toDTO(saved);
    }

    // ===== atualiza dados de um técnico existente =====
    public TechnicianDTO update(Long id, TechnicianDTO dto) {
        Technician existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("technician not found: " + id));

        existing.setName(dto.getName());
        existing.setSpecialty(dto.getSpecialty());

        Technician updated = repository.save(existing);
        return toDTO(updated);
    }

    // ===== remove um técnico pelo id =====
    public void delete(Long id) {
        Technician existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("technician not found: " + id));
        repository.delete(existing);
    }

    // ===== converte entidade technician em techniciandto =====
    private TechnicianDTO toDTO(Technician entity) {
        TechnicianDTO dto = new TechnicianDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSpecialty(entity.getSpecialty());
        return dto;
    }

    // ===== converte techniciandto em entidade technician =====
    private Technician toEntity(TechnicianDTO dto) {
        return Technician.builder()
                .id(dto.getId())
                .name(dto.getName())
                .specialty(dto.getSpecialty())
                .build();
    }
}
