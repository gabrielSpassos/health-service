package com.poc.controller;

import com.poc.constant.RoleConstant;
import com.poc.controller.request.RegistryRequest;
import com.poc.dto.MedicalRecordDTO;
import com.poc.dto.RegistryDTO;
import com.poc.service.MedicalRecordService;
import com.poc.service.RegistryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MedicalRecordController implements BaseVersion {

    private final MedicalRecordService medicalRecordService;
    private final RegistryService registryService;

    @Secured(value = {RoleConstant.ADMIN})
    @GetMapping(value = "/patients/{patientId}/medical-records")
    public ResponseEntity<MedicalRecordDTO> getPatientMedicalRecord(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
                                                                    @PathVariable("patientId") Long patientId) {
        MedicalRecordDTO medicalRecordDTO = medicalRecordService.findByPatientId(patientId);
        return ResponseEntity.ok(medicalRecordDTO);
    }

    @Secured(value = {RoleConstant.ADMIN})
    @PostMapping(value = "/medical-records/{medicalRecordId}/registries")
    public ResponseEntity<RegistryDTO> createRegistry(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
                                                      @PathVariable("medicalRecordId") Long medicalRecordId,
                                                      @RequestBody RegistryRequest registryRequest) {
        RegistryDTO registry = registryService.createRegistry(medicalRecordId, registryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(registry);
    }
}
