package com.poc.builder.dto;

import com.poc.dto.RegistryDTO;
import com.poc.entity.RegistryEntity;

public class RegistryDTOBuilder {

    public static RegistryDTO build(RegistryEntity registryEntity) {
        return RegistryDTO.builder()
                .id(registryEntity.getId())
                .description(registryEntity.getDescription())
                .build();
    }
}
