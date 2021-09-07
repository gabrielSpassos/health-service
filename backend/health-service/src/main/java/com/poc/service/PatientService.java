package com.poc.service;

import com.poc.builder.dto.PatientDTOBuilder;
import com.poc.builder.entity.PatientEntityBuilder;
import com.poc.controller.request.PatientRequest;
import com.poc.dto.PatientDTO;
import com.poc.entity.PatientEntity;
import com.poc.exception.InvalidPatientBirthdateException;
import com.poc.exception.PatientAlreadyExistentException;
import com.poc.exception.PatientNotFoundException;
import com.poc.repository.PatientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class PatientService {

    private final MedicalRecordService medicalRecordService;
    private final PatientRepository patientRepository;
    private static final String SORT_PROPERTY = "name";

    public PatientDTO createPatient(PatientRequest patientRequest) {
        PatientEntity patientAlreadyCreated = patientRepository.findByCpfOrRg(patientRequest.getCpf(), patientRequest.getRg());

        if (Objects.nonNull(patientAlreadyCreated)) {
            throw new PatientAlreadyExistentException();
        }

        validateBirthdate(patientRequest);

        PatientEntity patientEntity = PatientEntityBuilder.build(patientRequest);
        PatientEntity savedPatient = patientRepository.save(patientEntity);
        log.info("Criado paciente {}", savedPatient);
        PatientDTO patientDTO = PatientDTOBuilder.build(savedPatient);
        medicalRecordService.createMedicalRecord(patientDTO);
        return patientDTO;
    }

    public Page<PatientDTO> getPatients(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, SORT_PROPERTY);

        return patientRepository.findAll(pageRequest)
                .map(PatientDTOBuilder::build);
    }

    public PatientDTO getPatientById(Long id) {
        return patientRepository.findById(id)
                .map(PatientDTOBuilder::build)
                .orElseThrow(PatientNotFoundException::new);
    }

    private void validateBirthdate(PatientRequest patientRequest) {
        LocalDate today = LocalDate.now(ZoneId.of("America/Sao_Paulo"));

        boolean isBirthdateAfterToday = patientRequest.getBirthdate().isAfter(today);

        if (isBirthdateAfterToday) throw new InvalidPatientBirthdateException();
    }
}
