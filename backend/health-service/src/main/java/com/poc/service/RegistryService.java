package com.poc.service;

import com.poc.builder.dto.RegistryDTOBuilder;
import com.poc.builder.entity.RegistryEntityBuilder;
import com.poc.controller.request.RegistryRequest;
import com.poc.dto.RegistryDTO;
import com.poc.entity.MedicalRecordEntity;
import com.poc.entity.RegistryEntity;
import com.poc.exception.RegistryNotFoundException;
import com.poc.repository.RegistryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class RegistryService {

    private final MedicalRecordService medicalRecordService;
    private final RegistryRepository registryRepository;

    public RegistryDTO createRegistry(Long medicalRecordId, RegistryRequest registryRequest) {
        MedicalRecordEntity medicalRecordEntity = medicalRecordService.getMedicalRecordById(medicalRecordId);

        RegistryEntity registryEntity = RegistryEntityBuilder.build(registryRequest, medicalRecordEntity);
        RegistryEntity savedRegistry = registryRepository.save(registryEntity);
        log.info("Criado registro {}", savedRegistry);
        return RegistryDTOBuilder.build(savedRegistry);
    }

    public RegistryDTO updateRegistry(Long id, RegistryRequest registryRequest) {
        return registryRepository.findById(id)
                .map(registryEntity -> RegistryEntityBuilder.build(registryEntity, registryRequest))
                .map(registryRepository::save)
                .map(RegistryDTOBuilder::build)
                .orElseThrow(RegistryNotFoundException::new);
    }
}
