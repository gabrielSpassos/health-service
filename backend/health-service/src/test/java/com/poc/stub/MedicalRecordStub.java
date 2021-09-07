package com.poc.stub;

import com.poc.dto.MedicalRecordDTO;
import com.poc.dto.PatientDTO;
import com.poc.dto.RegistryDTO;
import com.poc.entity.MedicalRecordEntity;
import com.poc.entity.PatientEntity;
import com.poc.entity.RegistryEntity;

import java.util.Arrays;
import java.util.HashSet;

public class MedicalRecordStub {

    public static MedicalRecordDTO createDTO(PatientDTO patientDTO, RegistryDTO... registries) {
        MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO();
        medicalRecordDTO.setId(1L);
        medicalRecordDTO.setPatient(patientDTO);
        medicalRecordDTO.setRegistries(Arrays.asList(registries));
        return medicalRecordDTO;
    }

    public static MedicalRecordEntity createEntity(PatientEntity patientEntity, RegistryEntity... registries) {
        MedicalRecordEntity medicalRecordEntity = new MedicalRecordEntity();
        medicalRecordEntity.setId(1L);
        medicalRecordEntity.setPatient(patientEntity);
        medicalRecordEntity.setRegistries(new HashSet<>(Arrays.asList(registries)));
        return medicalRecordEntity;
    }
}
