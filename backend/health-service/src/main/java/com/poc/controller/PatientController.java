package com.poc.controller;

import com.poc.constant.RoleConstant;
import com.poc.controller.request.PatientRequest;
import com.poc.dto.PatientDTO;
import com.poc.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
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

    @CrossOrigin(origins = "http://localhost:3000")
    @Secured(value = {RoleConstant.ADMIN, RoleConstant.USER})
    @GetMapping(value = "/patients")
    public ResponseEntity<Page<PatientDTO>> getPatients(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "20") int size) {
        Page<PatientDTO> patients = patientService.getPatients(page, size);
        return ResponseEntity.ok(patients);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @Secured(value = {RoleConstant.ADMIN, RoleConstant.USER})
    @GetMapping(value = "/patients/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
                                                     @PathVariable("id") Long id) {
        PatientDTO patient = patientService.getPatientById(id);
        return ResponseEntity.ok(patient);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @Secured(value = {RoleConstant.ADMIN})
    @PostMapping(value = "/patients")
    public ResponseEntity<PatientDTO> createPatient(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
                                                       @RequestBody PatientRequest patientRequest) {
        PatientDTO patient = patientService.createPatient(patientRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(patient);
    }
}
