package com.poc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditRegistryUserDTO {

    private Long id;
    private RegistryDTO registry;
    private UserDTO user;
    private String auditOperationType;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
