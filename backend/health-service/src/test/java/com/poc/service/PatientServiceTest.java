package com.poc.service;

import com.poc.controller.request.PatientRequest;
import com.poc.entity.PatientEntity;
import com.poc.exception.InvalidPatientBirthdateException;
import com.poc.exception.PatientAlreadyExistentException;
import com.poc.repository.PatientRepository;
import com.poc.stub.PatientStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @InjectMocks
    private PatientService patientService;

    @Mock
    private MedicalRecordService medicalRecordService;

    @Mock
    private PatientRepository patientRepository;

    @Test
    void shouldReturnErrorForAlreadyExistentPatientByCpf() {
        PatientRequest patientRequest = PatientStub.createRequest();
        PatientEntity patientEntity = PatientStub.createEntity();

        given(patientRepository.findByCpfOrRg("80447242067", "460844167")).willReturn(patientEntity);

        PatientAlreadyExistentException error = assertThrows(PatientAlreadyExistentException.class,
                () -> patientService.createPatient(patientRequest));

        assertEquals("1", error.getErrorDTO().getCode());
        assertEquals("Paciente já cadastrado", error.getErrorDTO().getMessage());
    }

    @Test
    void shouldReturnErrorForInvalidBirthdate() {
        PatientRequest patientRequest = PatientStub.createRequest();
        patientRequest.setBirthdate(LocalDate.now().plusDays(30));

        InvalidPatientBirthdateException error = assertThrows(InvalidPatientBirthdateException.class,
                () -> patientService.createPatient(patientRequest));

        assertEquals("6", error.getErrorDTO().getCode());
        assertEquals("Data de nascimento do paciente inválida", error.getErrorDTO().getMessage());
    }

}