package com.poc.service;

import com.poc.builder.entity.PatientEntityBuilder;
import com.poc.controller.request.PatientRequest;
import com.poc.entity.PatientEntity;
import com.poc.exception.PatientAlreadyExistentException;
import com.poc.exception.PatientNotFoundException;
import com.poc.repository.PatientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private static final String SORT_PROPERTY = "name";

    public PatientEntity createPatient(PatientRequest patientRequest) {
        PatientEntity patientAlreadyCreated = patientRepository.findByCpfOrRg(patientRequest.getCpf(), patientRequest.getRg());

        if (Objects.nonNull(patientAlreadyCreated)) {
            throw new PatientAlreadyExistentException();
        }

        PatientEntity patientEntity = PatientEntityBuilder.build(patientRequest);
        return patientRepository.save(patientEntity);
    }

    public Page<PatientEntity> getPatients(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, SORT_PROPERTY);

        return patientRepository.findAll(pageRequest);
    }

    public PatientEntity getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(PatientNotFoundException::new);
    }
}
