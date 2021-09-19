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
public class RegistryDTO {

    private Long id;
    private String description;
    private Boolean patientHasHeadache;
    private Boolean patientHasDizziness;
    private Boolean patientHasNausea;
    private Boolean patientHasFatigue;
    private Boolean patientHasTremors;
    private Boolean patientFeelsTinnitus;
    private Boolean patientFeelsPain;
    private Boolean patientHasOtherSymptom;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
