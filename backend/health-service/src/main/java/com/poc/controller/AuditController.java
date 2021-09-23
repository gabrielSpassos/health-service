package com.poc.controller;

import com.poc.constant.RoleConstant;
import com.poc.dto.AuditRegistryUserDTO;
import com.poc.service.RegistryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuditController implements BaseVersion {

    private final RegistryService registryService;

    @Secured(value = {RoleConstant.ADMIN})
    @GetMapping(value = "/registries/audits")
    public ResponseEntity<Page<AuditRegistryUserDTO>> getRegistriesAudit(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
                                                                         @RequestParam(defaultValue = "0") int page,
                                                                         @RequestParam(defaultValue = "20") int size) {
        Page<AuditRegistryUserDTO> auditRegistryPage = registryService.findRegistriesAudits(page, size);
        return ResponseEntity.ok(auditRegistryPage);
    }

    @Secured(value = {RoleConstant.ADMIN})
    @GetMapping(value = "/registries/{id}/audits")
    public ResponseEntity<Page<AuditRegistryUserDTO>> getAuditRegistryById(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
                                                                           @PathVariable("id") Long id,
                                                                           @RequestParam(defaultValue = "0") int page,
                                                                           @RequestParam(defaultValue = "20") int size) {
        Page<AuditRegistryUserDTO> auditRegistryPage = registryService.findAuditsByRegistryId(id, page, size);
        return ResponseEntity.ok(auditRegistryPage);
    }
}
