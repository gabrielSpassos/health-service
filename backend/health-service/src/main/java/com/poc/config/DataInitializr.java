package com.poc.config;

import com.google.common.collect.Lists;
import com.poc.constant.RoleEnum;
import com.poc.controller.request.UserRequest;
import com.poc.entity.UserEntity;
import com.poc.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class DataInitializr implements ApplicationListener<ContextRefreshedEvent> {

    private final UserService userService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        List<UserEntity> users = userService.findUsers();

        if (users.isEmpty()) {
            createUser("admin@gmail.com", "admin", "admin", RoleEnum.ROLE_ADMIN);
            createUser("usuario@gmail.com", "user", "user", RoleEnum.ROLE_USER);
        }
    }

    private void createUser(String email, String name, String password, RoleEnum role) {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail(email);
        userRequest.setName(name);
        userRequest.setPassword(password);
        userRequest.setRoles(Lists.newArrayList(role));

        userService.createUser(userRequest);
    }
}
