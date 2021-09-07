package com.poc.builder.entity;

import com.poc.entity.RoleEntity;
import com.poc.entity.UserEntity;

import java.util.List;

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

}
