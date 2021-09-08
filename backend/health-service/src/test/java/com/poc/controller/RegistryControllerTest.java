package com.poc.controller;

import com.poc.controller.request.RegistryRequest;
import com.poc.dto.RegistryDTO;
import com.poc.service.RegistryService;
import com.poc.stub.RegistryStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RegistryControllerTest {

    @InjectMocks
    private RegistryController registryController;
    @Mock
    private RegistryService registryService;

    @Test
    void shouldCreateRegistry() {
        RegistryRequest registryRequest = RegistryStub.createRequest();
        RegistryDTO registryDTO = RegistryStub.createDTO();

        given(registryService.createRegistry(1L, registryRequest)).willReturn(registryDTO);

        ResponseEntity<RegistryDTO> response = registryController
                .createRegistry("token", 1L, registryRequest);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
        assertEquals("unit test", response.getBody().getDescription());
    }

    @Test
    void shouldUpdateRegistry() {
        RegistryRequest registryRequest = RegistryStub.createRequest();
        RegistryDTO registryDTO = RegistryStub.createDTO();

        given(registryService.updateRegistry(1L, registryRequest)).willReturn(registryDTO);

        ResponseEntity<RegistryDTO> response = registryController
                .updateRegistry("token", 1L, registryRequest);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
        assertEquals("unit test", response.getBody().getDescription());
    }

}