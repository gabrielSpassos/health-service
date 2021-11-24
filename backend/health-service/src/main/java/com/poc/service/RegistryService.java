package com.poc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.builder.dto.AuditRegistryUserDTOBuilder;
import com.poc.builder.dto.RabbitMessageBuilder;
import com.poc.builder.dto.RegistryDTOBuilder;
import com.poc.builder.entity.AuditRegistryUserEntityBuilder;
import com.poc.builder.entity.RegistryEntityBuilder;
import com.poc.builder.entity.UserEntityBuilder;
import com.poc.client.RabbitProducer;
import com.poc.config.RabbitConfig;
import com.poc.constant.AuditOperationTypeEnum;
import com.poc.controller.request.RegistryRequest;
import com.poc.dto.AuditRegistryUserDTO;
import com.poc.dto.RegistryDTO;
import com.poc.dto.UserDTO;
import com.poc.entity.AuditRegistryUserEntity;
import com.poc.entity.MedicalRecordEntity;
import com.poc.entity.RegistryEntity;
import com.poc.entity.UserEntity;
import com.poc.exception.RegistryNotFoundException;
import com.poc.repository.AuditRegistryUserRepository;
import com.poc.repository.RegistryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class RegistryService {

    private final MedicalRecordService medicalRecordService;
    private final UserService userService;
    private final RabbitProducer rabbitProducer;
    private final RegistryRepository registryRepository;
    private final AuditRegistryUserRepository auditRegistryUserRepository;
    private final RabbitConfig rabbitConfig;
    private final ObjectMapper objectMapper;

    public RegistryDTO createRegistry(Long medicalRecordId, RegistryRequest registryRequest) {
        MedicalRecordEntity medicalRecordEntity = medicalRecordService.getMedicalRecordById(medicalRecordId);

        RegistryEntity registryEntity = RegistryEntityBuilder.build(registryRequest, medicalRecordEntity);
        RegistryEntity savedRegistry = registryRepository.save(registryEntity);
        log.info("Criado registro {}", savedRegistry);

        createAudit(savedRegistry, AuditOperationTypeEnum.CREATE);

        RegistryDTO registryDTO = RegistryDTOBuilder.build(savedRegistry);

        sendMessage(registryDTO);

        return registryDTO;
    }

    public RegistryDTO updateRegistry(Long id, RegistryRequest registryRequest) {
        return registryRepository.findById(id)
                .map(registryEntity -> RegistryEntityBuilder.build(registryEntity, registryRequest))
                .map(registryRepository::save)
                .map(registryEntity -> {
                    createAudit(registryEntity, AuditOperationTypeEnum.UPDATE);
                    return registryEntity;
                })
                .map(RegistryDTOBuilder::build)
                .orElseThrow(RegistryNotFoundException::new);
    }

    public RegistryDTO getRegistryById(Long registryId) {
        return registryRepository.findById(registryId)
                .map(RegistryDTOBuilder::build)
                .orElseThrow(RegistryNotFoundException::new);

    }

    public Page<AuditRegistryUserDTO> findAuditsByRegistryId(Long registryId, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return auditRegistryUserRepository.findByRegistryId(registryId, pageRequest)
                .map(this::mapAuditEntityToDTO);
    }

    public Page<AuditRegistryUserDTO> findRegistriesAudits(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return auditRegistryUserRepository.findAll(pageRequest)
                .map(this::mapAuditEntityToDTO);
    }

    private void sendMessage(RegistryDTO registryDTO) {
        try {
            byte[] binaryData = objectMapper.writeValueAsBytes(registryDTO);
            Message message = RabbitMessageBuilder.build(binaryData);
            rabbitProducer.sendMessage(rabbitConfig.getExchangeName(), rabbitConfig.getExchangeRoutingKey(), message);
        } catch (Exception e) {
            log.error("Erro ao enviar evento {}", registryDTO, e);
        }
    }

    private void createAudit(RegistryEntity registryEntity, AuditOperationTypeEnum auditOperationType) {
        UserDTO userDTO = userService.getUserFromToken();
        UserEntity userEntity = UserEntityBuilder.build(userDTO);

        AuditRegistryUserEntity auditRegistryUserEntity = AuditRegistryUserEntityBuilder
                .build(registryEntity, userEntity, auditOperationType);
        AuditRegistryUserEntity savedAudit = auditRegistryUserRepository.save(auditRegistryUserEntity);
        log.info("Criado auditoria {}", savedAudit);
    }

    private AuditRegistryUserDTO mapAuditEntityToDTO(AuditRegistryUserEntity auditRegistryUserEntity) {
        RegistryDTO registryDTO = getRegistryById(auditRegistryUserEntity.getRegistryId());
        UserDTO userDTO = userService.findUserById(auditRegistryUserEntity.getUserId());

        return AuditRegistryUserDTOBuilder.build(auditRegistryUserEntity, registryDTO, userDTO);
    }
}
