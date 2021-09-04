package com.poc.controller.request;

import com.poc.constant.SexEnum;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientRequest {

    private String name;
    private String cpf;
    private String rg;
    private LocalDate birthdate;
    private SexEnum sex;
    private String phone;

}
