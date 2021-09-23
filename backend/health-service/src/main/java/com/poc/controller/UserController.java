package com.poc.controller;

import com.poc.constant.RoleConstant;
import com.poc.controller.request.UserRequest;
import com.poc.dto.UserDTO;
import com.poc.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController implements BaseVersion {

    private final UserService userService;

    @Secured(value = {RoleConstant.ADMIN})
    @PostMapping(value = "/users")
    public ResponseEntity<UserDTO> createUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
                                              @RequestBody UserRequest userRequest) {
        UserDTO savedUser = userService.createUser(userRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @Secured(value = {RoleConstant.ADMIN})
    @PutMapping(value = "/users/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
                                              @PathVariable("id") Long id,
                                              @RequestBody UserRequest userRequest) {
        UserDTO updatedUser = userService.updateUser(id, userRequest);

        return ResponseEntity.ok(updatedUser);
    }

    @Secured(value = {RoleConstant.ADMIN, RoleConstant.USER})
    @GetMapping(value = "/users")
    public ResponseEntity<Page<UserDTO>> getUsers(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "20") int size) {
        Page<UserDTO> users = userService.findUsers(page, size);
        return ResponseEntity.ok(users);
    }

    @Secured(value = {RoleConstant.ADMIN, RoleConstant.USER})
    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
                                               @PathVariable("id") Long id) {
        UserDTO user = userService.findUserById(id);
        return ResponseEntity.ok(user);
    }

    @Secured(value = {RoleConstant.ADMIN, RoleConstant.USER})
    @GetMapping(value = "/user-auth")
    public ResponseEntity<UserDTO> getUserAuth(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        UserDTO user = userService.getUserFromToken();

        return ResponseEntity.ok(user);
    }

}
