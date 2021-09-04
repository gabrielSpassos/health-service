package com.poc.builder.dto;

import com.poc.dto.MedicalRecordDTO;
import com.poc.dto.PatientDTO;
import com.poc.dto.RegistryDTO;
import com.poc.entity.MedicalRecordEntity;

import java.util.List;
import java.util.stream.Collectors;

public class MedicalRecordDTOBuilder {

    public static MedicalRecordDTO build(MedicalRecordEntity medicalRecordEntity) {
        PatientDTO patient = PatientDTOBuilder.build(medicalRecordEntity.getPatient());

        List<RegistryDTO> registries = medicalRecordEntity.getRegistries().stream()
                .map(RegistryDTOBuilder::build)
                .collect(Collectors.toList());


        return MedicalRecordDTO.builder()
                .id(medicalRecordEntity.getId())
                .patient(patient)
                .registries(registries)
                .build();
    }
}
