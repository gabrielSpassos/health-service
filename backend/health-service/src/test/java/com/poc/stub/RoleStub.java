package com.poc.stub;

import com.poc.entity.RoleEntity;

public class RoleStub {

    public static RoleEntity createEntity() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setName("ROLE_ADMIN");
        return roleEntity;
    }

}
