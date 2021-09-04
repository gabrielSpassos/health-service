package com.poc.controller;

import com.poc.constant.RoleConstant;
import com.poc.dto.MedicalRecordDTO;
import com.poc.service.MedicalRecordService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MedicalRecordController implements BaseVersion {

    private final MedicalRecordService medicalRecordService;

    @CrossOrigin(origins = "http://localhost:3000")
    @Secured(value = {RoleConstant.ADMIN})
    @GetMapping(value = "/patients/{patientId}/medical-records")
    public ResponseEntity<MedicalRecordDTO> getPatientMedicalRecord(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
                                                                    @PathVariable("patientId") Long patientId) {
        MedicalRecordDTO medicalRecordDTO = medicalRecordService.findByPatientId(patientId);
        return ResponseEntity.ok(medicalRecordDTO);
    }

}
