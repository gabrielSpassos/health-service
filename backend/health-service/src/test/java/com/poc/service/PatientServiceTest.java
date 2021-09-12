package com.poc.service;

import com.poc.controller.request.PatientRequest;
import com.poc.dto.MedicalRecordDTO;
import com.poc.dto.PatientDTO;
import com.poc.entity.PatientEntity;
import com.poc.exception.InvalidPatientBirthdateException;
import com.poc.exception.PatientAlreadyExistentException;
import com.poc.exception.PatientNotFoundException;
import com.poc.repository.PatientRepository;
import com.poc.stub.MedicalRecordStub;
import com.poc.stub.PatientStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @InjectMocks
    private PatientService patientService;

    @Mock
    private MedicalRecordService medicalRecordService;

    @Mock
    private PatientRepository patientRepository;

    @Captor
    private ArgumentCaptor<PatientEntity> argumentCaptor;

    @Test
    void shouldCreatePatient() {
        PatientRequest patientRequest = PatientStub.createRequest();
        PatientEntity patientEntity = PatientStub.createEntity();
        PatientDTO patientDTO = PatientStub.createDTO();
        MedicalRecordDTO medicalRecordDTO = MedicalRecordStub.createDTO(patientDTO);

        given(patientRepository.findByCpfOrRg("80447242067", "460844167")).willReturn(null);
        given(patientRepository.save(argumentCaptor.capture())).willReturn(patientEntity);
        given(medicalRecordService.createMedicalRecord(patientDTO)).willReturn(medicalRecordDTO);

        PatientDTO patient = patientService.createPatient(patientRequest);

        assertEquals(1L, patient.getId());
        assertEquals("80447242067", patient.getCpf());
        assertEquals("460844167", patient.getRg());

        PatientEntity value = argumentCaptor.getValue();
        assertNull(value.getId());
        assertEquals("80447242067", value.getCpf());
        assertEquals("460844167", value.getRg());
        verify(patientRepository).save(any());
    }

    @Test
    void shouldReturnErrorForAlreadyExistentPatientByCpf() {
        PatientRequest patientRequest = PatientStub.createRequest();
        PatientEntity patientEntity = PatientStub.createEntity();

        given(patientRepository.findByCpfOrRg("80447242067", "460844167")).willReturn(patientEntity);

        PatientAlreadyExistentException error = assertThrows(PatientAlreadyExistentException.class,
                () -> patientService.createPatient(patientRequest));

        assertEquals("1", error.getErrorDTO().getCode());
        assertEquals("Paciente já cadastrado", error.getErrorDTO().getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, error.getHttpStatus());
    }

    @Test
    void shouldReturnErrorForInvalidBirthdate() {
        PatientRequest patientRequest = PatientStub.createRequest();
        patientRequest.setBirthdate(LocalDate.now().plusDays(30));

        InvalidPatientBirthdateException error = assertThrows(InvalidPatientBirthdateException.class,
                () -> patientService.createPatient(patientRequest));

        assertEquals("6", error.getErrorDTO().getCode());
        assertEquals("Data de nascimento do paciente inválida", error.getErrorDTO().getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, error.getHttpStatus());
    }

    @Test
    void shouldGetPatientById() {
        PatientEntity patientEntity = PatientStub.createEntity();

        given(patientRepository.findById(1L)).willReturn(Optional.of(patientEntity));

        PatientDTO patientDTO = patientService.getPatientById(1L);

        assertEquals(1L, patientDTO.getId());
        assertEquals("80447242067", patientDTO.getCpf());
    }

    @Test
    void shouldReturnErrorWithPatientNotFound() {
        given(patientRepository.findById(88L)).willReturn(Optional.empty());

        PatientNotFoundException error = assertThrows(PatientNotFoundException.class, () -> patientService.getPatientById(88L));

        assertEquals("2", error.getErrorDTO().getCode());
        assertEquals("Paciente não encontrado", error.getErrorDTO().getMessage());
    }

}