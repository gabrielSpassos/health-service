package com.poc.service;

import com.poc.dto.MedicalRecordDTO;
import com.poc.entity.MedicalRecordEntity;
import com.poc.entity.PatientEntity;
import com.poc.entity.RegistryEntity;
import com.poc.repository.MedicalRecordRepository;
import com.poc.stub.MedicalRecordStub;
import com.poc.stub.PatientStub;
import com.poc.stub.RegistryStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MedicalRecordServiceTest {

    @InjectMocks
    private MedicalRecordService medicalRecordService;

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @Test
    void shouldFindByPatientId() {
        PatientEntity patientEntity = PatientStub.createEntity();
        MedicalRecordEntity medicalRecordEntity = MedicalRecordStub.createEntity(patientEntity);

        given(medicalRecordRepository.findByPatientId(1L)).willReturn(medicalRecordEntity);

        MedicalRecordDTO medicalRecordDTO = medicalRecordService.findByPatientId(1L);

        assertEquals(1L, medicalRecordDTO.getId());
        assertTrue(medicalRecordDTO.getRegistries().isEmpty());
    }

    @Test
    void shouldFindById() {
        RegistryEntity registryEntity = RegistryStub.createEntity();
        PatientEntity patientEntity = PatientStub.createEntity();
        MedicalRecordEntity medicalRecordEntity = MedicalRecordStub.createEntity(patientEntity, registryEntity);

        given(medicalRecordRepository.findById(1L)).willReturn(Optional.of(medicalRecordEntity));

        MedicalRecordEntity medicalRecordById = medicalRecordService.getMedicalRecordById(1L);

        assertEquals(1L, medicalRecordById.getId());
        assertFalse(medicalRecordById.getRegistries().isEmpty());
    }

}