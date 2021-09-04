package com.poc.dto;

import com.poc.constant.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

    private Long id;
    private String name;
    private String cpf;
    private String rg;
    private LocalDate birthdate;
    private SexEnum sex;
    private String phone;

}
