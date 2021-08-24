package com.poc.service;

import com.poc.constant.RoleEnum;
import com.poc.entity.RoleEntity;
import com.poc.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleEntity getOrCreateRole(RoleEnum role) {
        RoleEntity roleEntity = roleRepository.findByName(role.name());

        return Optional.ofNullable(roleEntity)
                .orElseGet(() -> createRole(role));
    }

    private RoleEntity createRole(RoleEnum role) {
        RoleEntity roleEntity = new RoleEntity(role);

        return roleRepository.save(roleEntity);
    }
}
