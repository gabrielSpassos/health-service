package com.poc.config;

import com.poc.builder.entity.UserEntityBuilder;
import com.poc.constant.RoleConstant;
import com.poc.entity.RoleEntity;
import com.poc.entity.UserEntity;
import com.poc.repository.RoleRepository;
import com.poc.repository.UserRepository;
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

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        List<UserEntity> users = userRepository.findAll();

        if (users.isEmpty()) {
            createUser("admin@gmail.com", "admin", "admin", RoleConstant.ADMIN);
            createUser("usuario@gmail.com", "user", "user", RoleConstant.USER);
        }
    }

    private void createUser(String email, String name, String password, String role) {
        RoleEntity roleEntity = new RoleEntity(role);
        RoleEntity savedRole = roleRepository.save(roleEntity);

        UserEntity userEntity = UserEntityBuilder.create(email, name, passwordEncoder.encode(password), savedRole);
        userRepository.save(userEntity);
    }
}
