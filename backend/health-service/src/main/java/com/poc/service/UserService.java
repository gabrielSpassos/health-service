package com.poc.service;

import com.poc.builder.entity.UserEntityBuilder;
import com.poc.constant.RoleEnum;
import com.poc.controller.request.UserRequest;
import com.poc.entity.RoleEntity;
import com.poc.entity.UserEntity;
import com.poc.exception.UserNotFoundException;
import com.poc.repository.RoleRepository;
import com.poc.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserEntity createUser(UserRequest userRequest) {
        UserEntity userByEmail = userRepository.findByEmail(userRequest.getEmail());

        if (Objects.nonNull(userByEmail)) {
            return userByEmail;
        }

        List<RoleEntity> roles = createRoles(userRequest.getRoles());

        String encryptedPassword = passwordEncoder.encode(userRequest.getPassword());
        UserEntity userEntity = UserEntityBuilder
                .create(userRequest.getEmail(), userRequest.getName(), encryptedPassword, roles);
        return userRepository.save(userEntity);
    }

    public Page<UserEntity> findUsers(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return userRepository.findAll(pageRequest);
    }

    public List<UserEntity> findUsers() {
        return userRepository.findAll();
    }

    public UserEntity updateUser(Long id, UserRequest userRequest) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        deleteRoles(userEntity.getRoles());

        List<RoleEntity> newRoles = createRoles(userRequest.getRoles());

        String encryptedPassword = passwordEncoder.encode(userRequest.getPassword());
        UserEntity updateUserEntity = UserEntityBuilder
                .create(userEntity.getId(), userRequest.getEmail(), userRequest.getName(), encryptedPassword, newRoles);
        return userRepository.save(updateUserEntity);
    }

    private List<RoleEntity> createRoles(List<RoleEnum> roles) {
        return roles.stream()
                .map(roleEnum -> new RoleEntity(roleEnum.name()))
                .map(roleRepository::save)
                .collect(Collectors.toList());
    }

    private Long deleteRoles(List<RoleEntity> roles) {
        return roles.stream()
                .peek(roleRepository::delete)
                .count();
    }
}
