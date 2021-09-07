package com.poc.controller.request;

import com.poc.constant.RoleEnum;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

@Data
public class UserRequest {

    private String email;
    private String name;
    private String password;
    private List<RoleEnum> roles;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
