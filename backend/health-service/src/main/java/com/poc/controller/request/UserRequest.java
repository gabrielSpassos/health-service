package com.poc.controller.request;

import com.poc.constant.RoleEnum;
import lombok.Data;

import java.util.List;

@Data
public class UserRequest {

    private String email;
    private String name;
    private String password;
    private List<RoleEnum> roles;

}
