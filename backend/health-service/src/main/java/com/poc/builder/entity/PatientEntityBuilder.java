package com.poc.builder.entity;

import com.poc.controller.request.PatientRequest;
import com.poc.dto.PatientDTO;
import com.poc.entity.PatientEntity;

public class PatientEntityBuilder {

    public static PatientEntity build(PatientRequest patientRequest) {
        return PatientEntity.builder()
                .name(patientRequest.getName())
                .cpf(patientRequest.getCpf())
                .rg(patientRequest.getRg())
                .birthdate(patientRequest.getBirthdate())
                .sex(patientRequest.getSex())
                .phone(patientRequest.getPhone())
                .build();
    }

    public static PatientEntity build(PatientDTO patientDTO) {
        return PatientEntity.builder()
                .id(patientDTO.getId())
                .name(patientDTO.getName())
                .cpf(patientDTO.getCpf())
                .rg(patientDTO.getRg())
                .birthdate(patientDTO.getBirthdate())
                .sex(patientDTO.getSex())
                .phone(patientDTO.getPhone())
                .build();
    }
}
