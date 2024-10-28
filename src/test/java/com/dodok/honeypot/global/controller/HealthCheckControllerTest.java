package com.dodok.honeypot.global.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HealthCheckControllerTest.class)
class HealthCheckControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    JpaMetamodelMappingContext jpaMetamodelMappingContext; // jpaMappingContext 빈 모킹

    @Test
    @DisplayName("HealthTest 컨트롤러를 테스트한다.")
    void healthCheck() throws Exception {
        //given
        //when
        //then
        mockMvc.perform(get("/api/health"))
                .andExpect(status().isOk());
    }
}