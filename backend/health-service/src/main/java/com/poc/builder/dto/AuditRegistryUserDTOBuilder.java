package com.poc.builder.dto;

import com.poc.dto.AuditRegistryUserDTO;
import com.poc.dto.RegistryDTO;
import com.poc.dto.UserDTO;
import com.poc.entity.AuditRegistryUserEntity;

public class AuditRegistryUserDTOBuilder {

    public static AuditRegistryUserDTO build(AuditRegistryUserEntity entity, RegistryDTO registryDTO, UserDTO userDTO) {
        return AuditRegistryUserDTO.builder()
                .id(entity.getId())
                .registry(registryDTO)
                .user(userDTO)
                .auditOperationType(entity.getAuditOperationType())
                .build();
    }
}
