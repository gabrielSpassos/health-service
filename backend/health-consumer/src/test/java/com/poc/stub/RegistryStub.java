package com.poc.stub;

import com.poc.event.RegistryEvent;

public class RegistryStub {

    public static RegistryEvent createEvent() {
        RegistryEvent registryDTO = new RegistryEvent();
        registryDTO.setId(1L);
        registryDTO.setDescription("unit test");
        return registryDTO;
    }

}
