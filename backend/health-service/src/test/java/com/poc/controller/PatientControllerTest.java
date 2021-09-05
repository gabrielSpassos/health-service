package com.poc.controller;

import com.poc.controller.request.PatientRequest;
import com.poc.dto.PatientDTO;
import com.poc.service.PatientService;
import com.poc.stub.PatientStub;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PatientControllerTest {

    @InjectMocks
    private PatientController patientController;
    @Mock
    private PatientService patientService;

    @Test
    public void shouldReturnPatients() {
        PatientDTO patientDTO = PatientStub.createDTO();
        PageImpl patientsPage = new PageImpl<>(Lists.newArrayList(patientDTO));

        given(patientService.getPatients(0, 20)).willReturn(patientsPage);

        ResponseEntity<Page<PatientDTO>> response = patientController.getPatients("token", 0, 20);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getContent().get(0).getId());
        assertEquals("460844167", response.getBody().getContent().get(0).getRg());
    }

    @Test
    public void shouldReturnPatientById() {
        PatientDTO patientDTO = PatientStub.createDTO();

        given(patientService.getPatientById(1L)).willReturn(patientDTO);

        ResponseEntity<PatientDTO> response = patientController.getPatientById("token", 1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
        assertEquals("Amanda Maria da Silva", response.getBody().getName());
    }

    @Test
    public void shouldCreatePatient() {
        PatientRequest patientRequest = PatientStub.createRequest();
        PatientDTO patientDTO = PatientStub.createDTO();

        given(patientService.createPatient(patientRequest)).willReturn(patientDTO);

        ResponseEntity<PatientDTO> response = patientController.createPatient("token", patientRequest);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
        assertEquals("Amanda Maria da Silva", response.getBody().getName());
    }
}