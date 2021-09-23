package com.poc.stub;

import com.poc.constant.RoleEnum;
import com.poc.controller.request.UserRequest;
import com.poc.dto.UserDTO;
import com.poc.entity.UserEntity;
import org.assertj.core.util.Lists;

public class UserStub {

    public static UserEntity createEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setEmail("test@gmail.com");
        userEntity.setName("test");
        userEntity.setPassword("123456");
        userEntity.setRoles(Lists.newArrayList(RoleStub.createEntity()));
        return userEntity;
    }

    public static UserDTO createDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setEmail("test@gmail.com");
        userDTO.setName("test");
        userDTO.setPassword("123456");
        userDTO.setRoles(Lists.newArrayList(RoleStub.createDTO()));
        return userDTO;
    }

    public static UserRequest createRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("test@gmail.com");
        userRequest.setName("test");
        userRequest.setPassword("123456");
        userRequest.setRoles(Lists.newArrayList(RoleEnum.ROLE_ADMIN));
        return userRequest;
    }

}
