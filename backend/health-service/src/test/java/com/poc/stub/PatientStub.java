package com.poc.stub;

import com.poc.constant.SexEnum;
import com.poc.controller.request.PatientRequest;
import com.poc.dto.PatientDTO;

import java.time.LocalDate;

public class PatientStub {

    public static PatientDTO createDTO() {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(1L);
        patientDTO.setName("Amanda Maria da Silva");
        patientDTO.setCpf("80447242067");
        patientDTO.setRg("460844167");
        patientDTO.setBirthdate(LocalDate.parse("1952-10-12"));
        patientDTO.setSex(SexEnum.FEMALE);
        patientDTO.setPhone("51988443708");
        return patientDTO;
    }

    public static PatientRequest createRequest() {
        PatientRequest patientRequest = new PatientRequest();
        patientRequest.setName("Amanda Maria da Silva");
        patientRequest.setCpf("80447242067");
        patientRequest.setRg("460844167");
        patientRequest.setBirthdate(LocalDate.parse("1952-10-12"));
        patientRequest.setSex(SexEnum.FEMALE);
        patientRequest.setPhone("51988443708");
        return patientRequest;
    }
}
