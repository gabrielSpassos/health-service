package com.poc.builder.entity;

import com.poc.entity.RoleEntity;
import com.poc.entity.UserEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserEntityBuilder {

    public static UserEntity create(String email, String name, String password, RoleEntity... roles) {
        return UserEntity.builder()
                .email(email)
                .name(name)
                .password(password)
                .roles(createRoles(roles))
                .build();
    }

    public static UserEntity create(String email, String name, String password, List<RoleEntity> roles) {
        return UserEntity.builder()
                .email(email)
                .name(name)
                .password(password)
                .roles(roles)
                .build();
    }

    private static List<RoleEntity> createRoles(RoleEntity... roles) {
        return Arrays.stream(roles)
                .collect(Collectors.toList());
    }
}
