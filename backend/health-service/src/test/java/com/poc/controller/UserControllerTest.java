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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

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
    public void shouldCreateAdminAccessToken() throws Exception {
        String content = String.format("grant_type=password&username=%s&password=%s", ADMIN_USERNAME, ADMIN_PASSWORD);

        mockMvc.perform(post("/oauth/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .header(HttpHeaders.AUTHORIZATION, BASIC_HEADER)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("access_token").isNotEmpty())
                .andExpect(jsonPath("token_type").value("bearer"));
    }

    @Test
    public void shouldCreateUserAccessToken() throws Exception {
        String content = String.format("grant_type=password&username=%s&password=%s", USER_USERNAME, USER_PASSWORD);

        mockMvc.perform(post("/oauth/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .header(HttpHeaders.AUTHORIZATION, BASIC_HEADER)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("access_token").isNotEmpty())
                .andExpect(jsonPath("token_type").value("bearer"));
    }

    @Test
    public void shouldGetUsers() throws Exception {
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

        String authorization = "Bearer " + accessTokenDTO.getAccess_token();

        mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, authorization))
                .andExpect(status().isOk())
                .andExpect(jsonPath("content[0].email").value("admin@gmail.com"))
                .andExpect(jsonPath("content[0].roles[0].name").value("ROLE_ADMIN"));
    }

    @Test
    public void shouldGetAuthUser() throws Exception {
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

        String authorization = "Bearer " + accessTokenDTO.getAccess_token();

        mockMvc.perform(get("/user-auth")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, authorization))
                .andExpect(status().isOk())
                .andExpect(jsonPath("username").value("usuario@gmail.com"))
                .andExpect(jsonPath("roles[0].authority").value("ROLE_USER"));
    }
}