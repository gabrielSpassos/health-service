package com.poc.config;

import com.google.common.collect.Lists;
import com.poc.constant.RoleEnum;
import com.poc.constant.SexEnum;
import com.poc.controller.request.PatientRequest;
import com.poc.controller.request.UserRequest;
import com.poc.dto.PatientDTO;
import com.poc.entity.UserEntity;
import com.poc.service.PatientService;
import com.poc.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class DataInitializr implements ApplicationListener<ContextRefreshedEvent> {

    private final UserService userService;
    private final PatientService patientService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        createUsersIfEmpty();
        createPatientsIfEmpty();
    }

    private void createUsersIfEmpty() {
        List<UserEntity> users = userService.findUsers();

        if (users.isEmpty()) {
            createUser("admin@gmail.com", "admin", "admin", RoleEnum.ROLE_ADMIN);
            createUser("usuario@gmail.com", "user", "user", RoleEnum.ROLE_USER);
        }
    }

    private void createPatientsIfEmpty() {
        Page<PatientDTO> patients = patientService.getPatients(0, 20);

        if (patients.isEmpty()) {
            createPatient("Amanda Maria da Silva", "80447242067",
                    "460844167", LocalDate.parse("1950-06-25"), SexEnum.FEMALE, "51988443708");
            createPatient("Alexandre Juan Santos", "99206991019",
                    "215631924", LocalDate.parse("1954-11-13"), SexEnum.MALE, "53986399532");
            createPatient("Joana Rosa Novaes", "99570581000",
                    "345328425", LocalDate.parse("1943-11-13"), SexEnum.FEMALE, null);
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

    private void createPatient(String name, String cpf, String rg, LocalDate birthdate, SexEnum sex, String phone) {
        PatientRequest patientRequest = new PatientRequest();
        patientRequest.setName(name);
        patientRequest.setCpf(cpf);
        patientRequest.setRg(rg);
        patientRequest.setBirthdate(birthdate);
        patientRequest.setSex(sex);
        patientRequest.setPhone(phone);

        patientService.createPatient(patientRequest);
    }
}
