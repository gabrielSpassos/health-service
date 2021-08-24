package com.poc.config;

import com.poc.builder.entity.UserEntityBuilder;
import com.poc.entity.RoleEntity;
import com.poc.entity.UserEntity;
import com.poc.constant.RoleEnum;
import com.poc.repository.UserRepository;
import com.poc.service.RoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class DataInitializr implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleService roleService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        RoleEntity adminRole = roleService.getOrCreateRole(RoleEnum.ADMIN);
        RoleEntity customerRole = roleService.getOrCreateRole(RoleEnum.CUSTOMER);

        List<UserEntity> users = userRepository.findAll();

        if (users.isEmpty()) {
            createUser("admin@gmail.com", "admin", "admin", adminRole);
            createUser("usuario@gmail.com", "user", "user", customerRole);
            createUser("gabriel@gmail.com", "gabriel", "123456", adminRole, customerRole);
        }
    }

    private void createUser(String email, String name, String password, RoleEntity... roles) {
        UserEntity userEntity = UserEntityBuilder.create(email, name, passwordEncoder.encode(password), roles);

        userRepository.save(userEntity);
    }
}
