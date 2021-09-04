package com.poc.controller;

import com.poc.dto.MedicalRecordDTO;
import com.poc.dto.PatientDTO;
import com.poc.service.MedicalRecordService;
import com.poc.stub.MedicalRecordStub;
import com.poc.stub.PatientStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class MedicalRecordControllerTest {

    @InjectMocks
    private MedicalRecordController medicalRecordController;
    @Mock
    private MedicalRecordService medicalRecordService;

    @Test
    public void shouldReturnMedicalRecordByPatientId() {
        PatientDTO patientDTO = PatientStub.createDTO();
        MedicalRecordDTO medicalRecordDTO = MedicalRecordStub.createDTO(patientDTO);

        given(medicalRecordService.findByPatientId(1L)).willReturn(medicalRecordDTO);

        ResponseEntity<MedicalRecordDTO> response = medicalRecordController
                .getPatientMedicalRecord("token", 1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
        assertEquals(patientDTO.getId(), response.getBody().getPatient().getId());
        assertEquals(patientDTO.getCpf(), response.getBody().getPatient().getCpf());
        assertTrue(medicalRecordDTO.getRegistries().isEmpty());
    }
}