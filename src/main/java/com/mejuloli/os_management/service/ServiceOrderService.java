package com.mejuloli.os_management.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mejuloli.os_management.dto.ServiceOrderDTO;
import com.mejuloli.os_management.exception.ResourceNotFoundException;
import com.mejuloli.os_management.model.Client;
import com.mejuloli.os_management.model.ServiceOrder;
import com.mejuloli.os_management.model.ServiceOrderStatus;
import com.mejuloli.os_management.model.Technician;
import com.mejuloli.os_management.repository.ClientRepository;
import com.mejuloli.os_management.repository.ServiceOrderRepository;
import com.mejuloli.os_management.repository.TechnicianRepository;

// ===== contém regras de negócio relacionadas a ordens de serviço =====
@Service
public class ServiceOrderService {

    private final ServiceOrderRepository serviceOrderRepository;
    private final ClientRepository clientRepository;
    private final TechnicianRepository technicianRepository;

    public ServiceOrderService(ServiceOrderRepository serviceOrderRepository,
            ClientRepository clientRepository,
            TechnicianRepository technicianRepository) {
        this.serviceOrderRepository = serviceOrderRepository;
        this.clientRepository = clientRepository;
        this.technicianRepository = technicianRepository;
    }

    // ===== busca todas as ordens de serviço convertendo para dto =====
    public List<ServiceOrderDTO> findAll() {
        return serviceOrderRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ===== busca uma ordem por id ou lança exceção =====
    public ServiceOrderDTO findById(Long id) {
        ServiceOrder serviceOrder = serviceOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("service order not found: " + id));
        return toDTO(serviceOrder);
    }

    // ===== cria uma nova ordem de serviço com status aberto =====
    public ServiceOrderDTO create(ServiceOrderDTO dto) {
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("client not found: " + dto.getClientId()));

        Technician technician = null;
        if (dto.getTechnicianId() != null) {
            technician = technicianRepository.findById(dto.getTechnicianId())
                    .orElseThrow(() -> new ResourceNotFoundException("technician not found: " + dto.getTechnicianId()));
        }

        ServiceOrder entity = new ServiceOrder();
        entity.setClient(client);
        entity.setTechnician(technician);
        entity.setDescription(dto.getDescription());
        entity.setPriority(dto.getPriority());
        entity.setStatus(ServiceOrderStatus.OPEN);
        entity.setOpenedAt(LocalDateTime.now());
        entity.setClosedAt(null);

        ServiceOrder saved = serviceOrderRepository.save(entity);
        return toDTO(saved);
    }

    // ===== atualiza dados de uma ordem já existente =====
    public ServiceOrderDTO update(Long id, ServiceOrderDTO dto) {
        ServiceOrder existing = serviceOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("service order not found: " + id));

        if (dto.getClientId() != null) {
            Client client = clientRepository.findById(dto.getClientId())
                    .orElseThrow(() -> new ResourceNotFoundException("client not found: " + dto.getClientId()));
            existing.setClient(client);
        }

        if (dto.getTechnicianId() != null) {
            Technician technician = technicianRepository.findById(dto.getTechnicianId())
                    .orElseThrow(() -> new ResourceNotFoundException("technician not found: " + dto.getTechnicianId()));
            existing.setTechnician(technician);
        }

        existing.setDescription(dto.getDescription());
        existing.setPriority(dto.getPriority());

        // ===== atualiza status e controla datas de abertura e fechamento =====
        if (dto.getStatus() != null) {
            existing.setStatus(dto.getStatus());
            if (dto.getStatus() == ServiceOrderStatus.CLOSED && existing.getClosedAt() == null) {
                existing.setClosedAt(LocalDateTime.now());
            }
        }

        ServiceOrder updated = serviceOrderRepository.save(existing);
        return toDTO(updated);
    }

    // ===== remove uma ordem de serviço pelo id =====
    public void delete(Long id) {
        ServiceOrder existing = serviceOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("service order not found: " + id));
        serviceOrderRepository.delete(existing);
    }

    // ===== converte entidade serviceorder em serviceorderdto =====
    private ServiceOrderDTO toDTO(ServiceOrder entity) {
        ServiceOrderDTO dto = new ServiceOrderDTO();
        dto.setId(entity.getId());
        dto.setClientId(entity.getClient().getId());
        dto.setTechnicianId(entity.getTechnician() != null ? entity.getTechnician().getId() : null);
        dto.setDescription(entity.getDescription());
        dto.setPriority(entity.getPriority());
        dto.setStatus(entity.getStatus());
        dto.setOpenedAt(entity.getOpenedAt());
        dto.setClosedAt(entity.getClosedAt());
        return dto;
    }
}
