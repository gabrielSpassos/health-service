package com.poc.builder.entity;

import com.poc.controller.request.RegistryRequest;
import com.poc.entity.MedicalRecordEntity;
import com.poc.entity.RegistryEntity;

public class RegistryEntityBuilder {

    public static RegistryEntity build(RegistryRequest registryRequest, MedicalRecordEntity medicalRecordEntity) {
        return RegistryEntity.builder()
                .medicalRecord(medicalRecordEntity)
                .description(registryRequest.getDescription())
                .patientHasHeadache(registryRequest.getPatientHasHeadache())
                .patientHasDizziness(registryRequest.getPatientHasDizziness())
                .patientHasNausea(registryRequest.getPatientHasNausea())
                .patientHasFatigue(registryRequest.getPatientHasFatigue())
                .patientHasTremors(registryRequest.getPatientHasTremors())
                .patientFeelsTinnitus(registryRequest.getPatientFeelsTinnitus())
                .patientFeelsPain(registryRequest.getPatientFeelsPain())
                .patientHasOtherSymptom(registryRequest.getPatientHasOtherSymptom())
                .build();
    }

    public static RegistryEntity build(RegistryEntity registryEntity, RegistryRequest registryRequest) {
        return RegistryEntity.builder()
                .id(registryEntity.getId())
                .medicalRecord(registryEntity.getMedicalRecord())
                .description(registryRequest.getDescription())
                .patientHasHeadache(registryRequest.getPatientHasHeadache())
                .patientHasDizziness(registryRequest.getPatientHasDizziness())
                .patientHasNausea(registryRequest.getPatientHasNausea())
                .patientHasFatigue(registryRequest.getPatientHasFatigue())
                .patientHasTremors(registryRequest.getPatientHasTremors())
                .patientFeelsTinnitus(registryRequest.getPatientFeelsTinnitus())
                .patientFeelsPain(registryRequest.getPatientFeelsPain())
                .patientHasOtherSymptom(registryRequest.getPatientHasOtherSymptom())
                .build();
    }
}
