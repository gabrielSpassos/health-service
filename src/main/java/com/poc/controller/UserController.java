package com.poc.controller;

import com.poc.constant.RoleConstant;
import com.poc.entity.UserEntity;
import com.poc.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @Secured(value = {RoleConstant.ADMIN})
    @PostMapping(value = "/users")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity) {
        UserEntity savedUser = userRepository.save(userEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @Secured(value = {RoleConstant.ADMIN})
    @PutMapping(value = "/users")
    public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity userEntity) {
        UserEntity updatedUser = userRepository.save(userEntity);

        return ResponseEntity.ok(updatedUser);
    }

    @Secured(value = {RoleConstant.ADMIN, RoleConstant.CUSTOMER})
    @GetMapping(value = "/users")
    public ResponseEntity<Page<UserEntity>> getUsers(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserEntity> users = userRepository.findAll(pageable);
        return ResponseEntity.ok(users);
    }

    @Secured(value = {RoleConstant.ADMIN, RoleConstant.CUSTOMER})
    @GetMapping(value = "/user-auth")
    @ResponseBody
    public UserEntity getUserAuth() {
        return (UserEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
