package com.poc.builder.dto;

import com.poc.dto.RoleDTO;
import com.poc.dto.UserDTO;
import com.poc.entity.RoleEntity;
import com.poc.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class UserDTOBuilder {

    public static UserDTO build(UserEntity userEntity) {
        List<RoleDTO> roles = userEntity.getRoles()
                .stream()
                .map(UserDTOBuilder::buildRole)
                .collect(Collectors.toList());

        return UserDTO.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .name(userEntity.getName())
                .password(userEntity.getPassword())
                .roles(roles)
                .build();
    }

    private static RoleDTO buildRole(RoleEntity roleEntity) {
        return RoleDTO.builder()
                .id(roleEntity.getId())
                .name(roleEntity.getName())
                .build();
    }
}
