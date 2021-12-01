package com.poc.mapper;

import com.poc.entity.RegistryEntity;
import com.poc.event.RegistryEvent;

public class RegistryMapper {

    public static RegistryEntity map(RegistryEvent event) {
        return RegistryEntity.builder()
                .id(String.valueOf(event.getId()))
                .description(event.getDescription())
                .patientHasHeadache(event.getPatientHasHeadache())
                .patientHasDizziness(event.getPatientHasDizziness())
                .patientHasNausea(event.getPatientHasNausea())
                .patientHasFatigue(event.getPatientHasFatigue())
                .patientHasTremors(event.getPatientHasTremors())
                .patientFeelsTinnitus(event.getPatientFeelsTinnitus())
                .patientFeelsPain(event.getPatientFeelsPain())
                .patientHasOtherSymptom(event.getPatientHasOtherSymptom())
                .build();
    }
}
