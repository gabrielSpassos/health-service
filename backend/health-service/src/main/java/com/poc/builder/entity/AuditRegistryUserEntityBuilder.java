package com.poc.builder.entity;

import com.poc.constant.AuditOperationTypeEnum;
import com.poc.entity.AuditRegistryUserEntity;
import com.poc.entity.RegistryEntity;
import com.poc.entity.UserEntity;

public class AuditRegistryUserEntityBuilder {

    public static AuditRegistryUserEntity build(RegistryEntity registryEntity, UserEntity userEntity,
                                                AuditOperationTypeEnum auditOperationType) {
        return AuditRegistryUserEntity.builder()
                .registryId(registryEntity.getId())
                .userId(userEntity.getId())
                .auditOperationType(auditOperationType.name())
                .build();
    }
}
