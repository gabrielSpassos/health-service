package com.poc.builder.entity;

import com.poc.dto.PatientDTO;
import com.poc.entity.MedicalRecordEntity;
import com.poc.entity.PatientEntity;

import java.util.Collections;

public class MedicalRecordEntityBuilder {

    public static MedicalRecordEntity builder(PatientDTO patientDTO) {
        PatientEntity patientEntity = PatientEntityBuilder.build(patientDTO);

        return MedicalRecordEntity.builder()
                .patient(patientEntity)
                .registries(Collections.emptySet())
                .build();
    }
}
