package com.poc.service;

import com.poc.controller.request.UserRequest;
import com.poc.dto.UserDTO;
import com.poc.entity.RoleEntity;
import com.poc.entity.UserEntity;
import com.poc.exception.UserNotFoundException;
import com.poc.repository.RoleRepository;
import com.poc.repository.UserRepository;
import com.poc.stub.RoleStub;
import com.poc.stub.UserStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Spy
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Captor
    private ArgumentCaptor<RoleEntity> roleArgumentCaptor;

    @Captor
    private ArgumentCaptor<UserEntity> userArgumentCaptor;

    @Test
    void shouldReturnAlreadyCreatedUser() {
        UserRequest userRequest = UserStub.createRequest();
        UserEntity userEntity = UserStub.createEntity();

        given(userRepository.findByEmail("test@gmail.com")).willReturn(userEntity);

        UserDTO userDTO = userService.createUser(userRequest);

        assertEquals(1L, userDTO.getId());
        assertEquals("test@gmail.com", userDTO.getEmail());
        assertEquals("ROLE_ADMIN", userDTO.getRoles().get(0).getName());
        verify(userRepository, never()).save(any());
    }

    @Test
    void shouldUpdateUser() {
        UserRequest userRequest = UserStub.createRequest();
        UserEntity userEntity = UserStub.createEntity();
        RoleEntity roleEntity = RoleStub.createEntity();

        given(userRepository.findById(1L)).willReturn(Optional.of(userEntity));
        doNothing().when(roleRepository).delete(roleEntity);
        given(roleRepository.save(roleArgumentCaptor.capture())).willReturn(roleEntity);
        given(userRepository.save(userArgumentCaptor.capture())).willReturn(userEntity);

        UserDTO userDTO = userService.updateUser(1L, userRequest);

        assertEquals(1L, userDTO.getId());
        assertEquals("test@gmail.com", userDTO.getEmail());
        assertEquals("ROLE_ADMIN", userDTO.getRoles().get(0).getName());

        RoleEntity roleValue = roleArgumentCaptor.getValue();
        assertEquals("ROLE_ADMIN", roleValue.getName());

        UserEntity userValue = userArgumentCaptor.getValue();
        assertEquals("test@gmail.com", userValue.getEmail());
        assertEquals("ROLE_ADMIN", userValue.getRoles().get(0).getName());
    }

    @Test
    void shouldThrowErrorForNotFoundUserToUpdate() {
        UserRequest userRequest = UserStub.createRequest();

        given(userRepository.findById(1L)).willReturn(Optional.empty());

        UserNotFoundException error = assertThrows(UserNotFoundException.class,
                () -> userService.updateUser(1L, userRequest));

        assertEquals("5", error.getErrorDTO().getCode());
        assertEquals("Usuário não encontrado", error.getErrorDTO().getMessage());
    }

}