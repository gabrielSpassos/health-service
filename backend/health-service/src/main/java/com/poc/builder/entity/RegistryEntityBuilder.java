package com.poc.builder.entity;

import com.poc.controller.request.RegistryRequest;
import com.poc.entity.MedicalRecordEntity;
import com.poc.entity.RegistryEntity;

public class RegistryEntityBuilder {

    public static RegistryEntity build(RegistryRequest registryRequest, MedicalRecordEntity medicalRecordEntity) {
        return RegistryEntity.builder()
                .medicalRecord(medicalRecordEntity)
                .description(registryRequest.getDescription())
                .build();
    }
}
