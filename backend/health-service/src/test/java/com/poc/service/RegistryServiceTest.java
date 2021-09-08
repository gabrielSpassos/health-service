package com.poc.service;

import com.poc.controller.request.RegistryRequest;
import com.poc.dto.RegistryDTO;
import com.poc.entity.MedicalRecordEntity;
import com.poc.entity.PatientEntity;
import com.poc.entity.RegistryEntity;
import com.poc.repository.RegistryRepository;
import com.poc.stub.MedicalRecordStub;
import com.poc.stub.PatientStub;
import com.poc.stub.RegistryStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RegistryServiceTest {

    @InjectMocks
    private RegistryService registryService;

    @Mock
    private MedicalRecordService medicalRecordService;

    @Mock
    private RegistryRepository registryRepository;

    @Captor
    private ArgumentCaptor<RegistryEntity> argumentCaptor;

    @Test
    void shouldCreateRegistry() {
        PatientEntity patientEntity = PatientStub.createEntity();
        MedicalRecordEntity medicalRecordEntity = MedicalRecordStub.createEntity(patientEntity);
        RegistryRequest registryRequest = RegistryStub.createRequest();
        RegistryEntity registryEntity = RegistryStub.createEntity();

        given(medicalRecordService.getMedicalRecordById(1L)).willReturn(medicalRecordEntity);
        given(registryRepository.save(argumentCaptor.capture())).willReturn(registryEntity);

        RegistryDTO registryDTO = registryService.createRegistry(1L, registryRequest);

        assertNotNull(registryDTO);
        assertEquals(1L, registryDTO.getId());
        assertEquals("unit test", registryDTO.getDescription());

        RegistryEntity value = argumentCaptor.getValue();
        assertNull(value.getId());
        assertEquals("unit test", value.getDescription());
    }

    @Test
    void shouldUpdateRegistry() {
        RegistryRequest registryRequest = RegistryStub.createRequest();
        RegistryEntity registryEntity = RegistryStub.createEntity();

        given(registryRepository.findById(1L)).willReturn(Optional.of(registryEntity));
        given(registryRepository.save(argumentCaptor.capture())).willReturn(registryEntity);

        RegistryDTO registryDTO = registryService.updateRegistry(1L, registryRequest);

        assertNotNull(registryDTO);
        assertEquals(1L, registryDTO.getId());
        assertEquals("unit test", registryDTO.getDescription());

        RegistryEntity value = argumentCaptor.getValue();
        assertEquals(1L, value.getId());
        assertEquals("unit test", value.getDescription());
    }

}