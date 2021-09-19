package com.poc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "REGISTRY")
@EqualsAndHashCode(exclude = "medicalRecord")
public class RegistryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MEDICAL_REPORT_ID", nullable = false)
    private MedicalRecordEntity medicalRecord;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PATIENT_HAS_HEADACHE")
    private Boolean patientHasHeadache;

    @Column(name = "PATIENT_HAS_DIZZINESS")
    private Boolean patientHasDizziness;

    @Column(name = "PATIENT_HAS_NAUSEA")
    private Boolean patientHasNausea;

    @Column(name = "PATIENT_HAS_FATIGUE")
    private Boolean patientHasFatigue;

    @Column(name = "PATIENT_HAS_TREMORS")
    private Boolean patientHasTremors;

    @Column(name = "PATIENT_FEELS_TINNITUS")
    private Boolean patientFeelsTinnitus;

    @Column(name = "PATIENT_FEELS_PAIN")
    private Boolean patientFeelsPain;

    @Column(name = "PATIENT_HAS_OTHER_SYMPTOM")
    private Boolean patientHasOtherSymptom;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
