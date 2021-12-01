package com.poc.service;

import com.poc.entity.RegistryEntity;
import com.poc.event.RegistryEvent;
import com.poc.mapper.RegistryMapper;
import com.poc.repository.RegistryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class RegistryService {

    private final RegistryRepository registryRepository;

    public RegistryEntity saveRegistryToCache(RegistryEvent registryEvent) {
        RegistryEntity registryEntity = RegistryMapper.map(registryEvent);
        RegistryEntity savedRegistry = registryRepository.save(registryEntity);
        log.info("Salvo registry {}", savedRegistry);
        return savedRegistry;
    }
}
