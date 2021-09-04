package com.poc.controller;

import com.poc.constant.RoleConstant;
import com.poc.controller.request.PatientRequest;
import com.poc.entity.PatientEntity;
import com.poc.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PatientController implements BaseVersion {

    private final PatientService patientService;

    @Secured(value = {RoleConstant.ADMIN, RoleConstant.USER})
    @GetMapping(value = "/patients")
    public ResponseEntity<Page<PatientEntity>> getPatients(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "20") int size) {
        Page<PatientEntity> patients = patientService.getPatients(page, size);
        return ResponseEntity.ok(patients);
    }

    @Secured(value = {RoleConstant.ADMIN, RoleConstant.USER})
    @GetMapping(value = "/patients/{id}")
    public ResponseEntity<PatientEntity> getPatients(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
                                                     @PathVariable("id") Long id) {
        PatientEntity patient = patientService.getPatientById(id);
        return ResponseEntity.ok(patient);
    }

    @Secured(value = {RoleConstant.ADMIN, RoleConstant.USER})
    @PostMapping(value = "/patients")
    public ResponseEntity<PatientEntity> createPatient(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
                                                       @RequestBody PatientRequest patientRequest) {
        PatientEntity patient = patientService.createPatient(patientRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(patient);
    }
}
