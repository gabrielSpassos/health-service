package com.poc.builder.entity;

import com.poc.dto.RoleDTO;
import com.poc.dto.UserDTO;
import com.poc.entity.RoleEntity;
import com.poc.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class UserEntityBuilder {

    public static UserEntity create(Long id, String email, String name, String password, List<RoleEntity> roles) {
        UserEntity userEntity = create(email, name, password, roles);
        userEntity.setId(id);
        return userEntity;
    }

    public static UserEntity create(String email, String name, String password, List<RoleEntity> roles) {
        return UserEntity.builder()
                .email(email)
                .name(name)
                .password(password)
                .roles(roles)
                .build();
    }

    public static UserEntity build(UserDTO userDTO) {
        List<RoleEntity> roles = userDTO.getRoles().stream()
                .map(UserEntityBuilder::buildRole)
                .collect(Collectors.toList());

        return UserEntity.builder()
                .id(userDTO.getId())
                .email(userDTO.getEmail())
                .name(userDTO.getName())
                .password(userDTO.getPassword())
                .roles(roles)
                .build();
    }

    private static RoleEntity buildRole(RoleDTO roleDTO) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(roleDTO.getId());
        roleEntity.setName(roleDTO.getName());
        return roleEntity;
    }

}
