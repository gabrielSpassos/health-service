package com.poc.entity;

import com.poc.constant.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "PATIENT")
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "RG")
    private String rg;

    @Column(name = "BIRTHDATE")
    private LocalDate birthdate;

    @Column(name = "SEX")
    private SexEnum sex;

    @Column(name = "PHONE")
    private String phone;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
