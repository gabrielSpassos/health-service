package com.poc.service;

import com.poc.builder.entity.UserEntityBuilder;
import com.poc.controller.request.UserRequest;
import com.poc.entity.RoleEntity;
import com.poc.entity.UserEntity;
import com.poc.repository.RoleRepository;
import com.poc.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserEntity createUser(UserRequest userRequest) {
        UserEntity userByEmail = userRepository.findByEmail(userRequest.getEmail());

        if (Objects.nonNull(userByEmail)) {
            return userByEmail;
        }

        List<RoleEntity> roles = userRequest.getRoles().stream()
                .map(roleEnum -> new RoleEntity(roleEnum.name()))
                .map(roleRepository::save)
                .collect(Collectors.toList());

        UserEntity userEntity = UserEntityBuilder
                .create(userRequest.getEmail(), userRequest.getName(), userRequest.getPassword(), roles);

        return userRepository.save(userEntity);
    }
}
