package com.poc.stub;

import com.poc.dto.MedicalRecordDTO;
import com.poc.dto.PatientDTO;
import com.poc.dto.RegistryDTO;

import java.util.Arrays;

public class MedicalRecordStub {

    public static MedicalRecordDTO createDTO(PatientDTO patientDTO, RegistryDTO... registries) {
        MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO();
        medicalRecordDTO.setId(1L);
        medicalRecordDTO.setPatient(patientDTO);
        medicalRecordDTO.setRegistries(Arrays.asList(registries));
        return medicalRecordDTO;
    }
}
