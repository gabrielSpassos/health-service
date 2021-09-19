package com.poc.builder.dto;

import com.poc.dto.RegistryDTO;
import com.poc.entity.RegistryEntity;

public class RegistryDTOBuilder {

    public static RegistryDTO build(RegistryEntity registryEntity) {
        return RegistryDTO.builder()
                .id(registryEntity.getId())
                .description(registryEntity.getDescription())
                .patientHasHeadache(registryEntity.getPatientHasHeadache())
                .patientHasDizziness(registryEntity.getPatientHasDizziness())
                .patientHasNausea(registryEntity.getPatientHasNausea())
                .patientHasFatigue(registryEntity.getPatientHasFatigue())
                .patientHasTremors(registryEntity.getPatientHasTremors())
                .patientFeelsTinnitus(registryEntity.getPatientFeelsTinnitus())
                .patientFeelsPain(registryEntity.getPatientFeelsPain())
                .patientHasOtherSymptom(registryEntity.getPatientHasOtherSymptom())
                .build();
    }
}
