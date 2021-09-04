package com.poc.service;

import com.poc.builder.dto.MedicalRecordDTOBuilder;
import com.poc.builder.entity.MedicalRecordEntityBuilder;
import com.poc.dto.MedicalRecordDTO;
import com.poc.dto.PatientDTO;
import com.poc.entity.MedicalRecordEntity;
import com.poc.exception.MedicalRecordNotFoundException;
import com.poc.repository.MedicalRecordRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordDTO findByPatientId(Long patientId) {
        return Optional.ofNullable(medicalRecordRepository.findByPatientId(patientId))
                .map(MedicalRecordDTOBuilder::build)
                .orElseThrow(MedicalRecordNotFoundException::new);
    }

    MedicalRecordDTO createMedicalRecord(PatientDTO patientDTO) {
        MedicalRecordEntity medicalRecordEntity = MedicalRecordEntityBuilder.builder(patientDTO);

        MedicalRecordEntity savedMedicalRecord = medicalRecordRepository.save(medicalRecordEntity);
        log.info("Criado prontuário: {}", savedMedicalRecord);
        return MedicalRecordDTOBuilder.build(savedMedicalRecord);
    }

    MedicalRecordEntity getMedicalRecordById(Long id) {
        return medicalRecordRepository.findById(id)
                .orElseThrow(MedicalRecordNotFoundException::new);
    }
}
