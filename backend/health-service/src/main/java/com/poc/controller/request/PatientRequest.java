package com.poc.controller.request;

import com.poc.constant.SexEnum;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class PatientRequest {

    @NotEmpty(message = "Nome do paciente não pode vazio")
    @NotNull(message = "Nome do paciente deve ser preenchido")
    private String name;

    @NotEmpty(message = "CPF do paciente não pode vazio")
    @NotNull(message = "CPF do paciente deve ser preenchido")
    private String cpf;

    @NotEmpty(message = "RG do paciente não pode vazio")
    @NotNull(message = "RG do paciente deve ser preenchido")
    private String rg;

    @NotNull(message = "A data de nascimento do paciente deve ser preenchido")
    private LocalDate birthdate;

    private SexEnum sex;
    private String phone;

}
