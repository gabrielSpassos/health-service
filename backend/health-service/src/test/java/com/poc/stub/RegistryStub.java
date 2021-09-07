package com.poc.stub;

import com.poc.controller.request.RegistryRequest;
import com.poc.dto.RegistryDTO;
import com.poc.entity.RegistryEntity;

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

    public static RegistryEntity createEntity() {
        RegistryEntity registryEntity = new RegistryEntity();
        registryEntity.setId(1L);
        registryEntity.setDescription("unit test");
        return registryEntity;
    }
}
