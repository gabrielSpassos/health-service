package com.poc.stub;

import com.poc.controller.request.RegistryRequest;
import com.poc.dto.RegistryDTO;

public class RegistryStub {

    public static RegistryRequest createRequest() {
        RegistryRequest registryRequest = new RegistryRequest();
        registryRequest.setDescription("unit test");
        return registryRequest;
    }

    public static RegistryDTO createDTO() {
        RegistryDTO registryDTO = new RegistryDTO();
        registryDTO.setId(1L);
        registryDTO.setDescription("unit test");
        return registryDTO;
    }
}
