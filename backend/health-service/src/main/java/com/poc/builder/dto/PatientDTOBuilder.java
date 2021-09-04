package com.poc.builder.dto;

import com.poc.dto.PatientDTO;
import com.poc.entity.PatientEntity;

public class PatientDTOBuilder {

    public static PatientDTO build(PatientEntity entity) {
        return PatientDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .cpf(entity.getCpf())
                .rg(entity.getRg())
                .birthdate(entity.getBirthdate())
                .sex(entity.getSex())
                .phone(entity.getPhone())
                .build();
    }
}
