package com.poc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.controller.dto.AccessTokenDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerSpringTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String BASIC_HEADER = "Basic Y2xpZW50OjEyMw==";
    private static final String ADMIN_USERNAME = "admin@gmail.com";
    private static final String ADMIN_PASSWORD = "admin";
    private static final String USER_USERNAME = "usuario@gmail.com";
    private static final String USER_PASSWORD = "user";

    @Test
    void shouldCreatePatient() throws Exception {
        String authorization = createAdminAccessToken();

        mockMvc.perform(post("/v1/patients")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(Resources.VALID_CREATE_PATIENT_REQUEST_BODY)
                .header(HttpHeaders.AUTHORIZATION, authorization))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").isNotEmpty())
                .andExpect(jsonPath("name").value("Pietro Davi Miguel dos Santos"))
                .andExpect(jsonPath("cpf").value("07644953728"))
                .andExpect(jsonPath("rg").value("157653389"))
                .andExpect(jsonPath("birthdate").value("1945-04-22"))
                .andExpect(jsonPath("sex").value("MALE"))
                .andExpect(jsonPath("phone").isEmpty());
    }

    @Test
    void shouldReturnErrorToCreatePatientWithAccessDenied() throws Exception {
        String authorization = createUserAccessToken();

        mockMvc.perform(post("/v1/patients")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(Resources.VALID_CREATE_PATIENT_REQUEST_BODY)
                .header(HttpHeaders.AUTHORIZATION, authorization))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("code").value("998"))
                .andExpect(jsonPath("message").value("Seu usuário não tem permissão para executar esta tarefa"));
    }

    @Test
    void shouldReturnErrorToCreatePatientWithInvalidName() throws Exception {
        String authorization = createAdminAccessToken();

        mockMvc.perform(post("/v1/patients")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(Resources.INVALID_NAME_CREATE_PATIENT_REQUEST_BODY)
                .header(HttpHeaders.AUTHORIZATION, authorization))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("code").value("997"))
                .andExpect(jsonPath("message").value("Nome do paciente não pode vazio"));
    }

    @Test
    void shouldReturnErrorToCreatePatientWithInvalidBirthdate() throws Exception {
        String authorization = createAdminAccessToken();

        mockMvc.perform(post("/v1/patients")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(Resources.INVALID_BIRTHDATE_CREATE_PATIENT_REQUEST_BODY)
                .header(HttpHeaders.AUTHORIZATION, authorization))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("code").value("6"))
                .andExpect(jsonPath("message").value("Data de nascimento do paciente inválida"));
    }

    private String createAdminAccessToken() throws Exception {
        String content = String.format("grant_type=password&username=%s&password=%s", ADMIN_USERNAME, ADMIN_PASSWORD);

        ResultActions resultActions = mockMvc.perform(post("/oauth/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .header(HttpHeaders.AUTHORIZATION, BASIC_HEADER)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("access_token").isNotEmpty());

        MvcResult mvcResult = resultActions.andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        AccessTokenDTO accessTokenDTO = objectMapper.readValue(contentAsString, AccessTokenDTO.class);

        return "Bearer " + accessTokenDTO.getAccess_token();
    }

    private String createUserAccessToken() throws Exception {
        String content = String.format("grant_type=password&username=%s&password=%s", USER_USERNAME, USER_PASSWORD);

        ResultActions resultActions = mockMvc.perform(post("/oauth/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .header(HttpHeaders.AUTHORIZATION, BASIC_HEADER)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("access_token").isNotEmpty());

        MvcResult mvcResult = resultActions.andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        AccessTokenDTO accessTokenDTO = objectMapper.readValue(contentAsString, AccessTokenDTO.class);

        return "Bearer " + accessTokenDTO.getAccess_token();
    }

    private class Resources {
        static final String VALID_CREATE_PATIENT_REQUEST_BODY =
                "{\n" +
                "  \"birthdate\": \"1945-04-22\",\n" +
                "  \"cpf\": \"07644953728\",\n" +
                "  \"name\": \"Pietro Davi Miguel dos Santos\",\n" +
                "  \"phone\": null,\n" +
                "  \"rg\": \"157653389\",\n" +
                "  \"sex\": \"MALE\"\n" +
                "}";

        static final String INVALID_NAME_CREATE_PATIENT_REQUEST_BODY =
                "{\n" +
                "  \"birthdate\": \"1945-04-22\",\n" +
                "  \"cpf\": \"07644953728\",\n" +
                "  \"name\": \"\",\n" +
                "  \"phone\": null,\n" +
                "  \"rg\": \"157653389\",\n" +
                "  \"sex\": \"MALE\"\n" +
                "}";

        static final String INVALID_BIRTHDATE_CREATE_PATIENT_REQUEST_BODY =
                "{\n" +
                "  \"birthdate\": \"2100-04-22\",\n" +
                "  \"cpf\": \"07644953728\",\n" +
                "  \"name\": \"Pietro Davi Miguel dos Santos\",\n" +
                "  \"phone\": null,\n" +
                "  \"rg\": \"157653389\",\n" +
                "  \"sex\": \"MALE\"\n" +
                "}";
    }
}
