package com.jay.EmiCalc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jay.EmiCalc.dto.EmiCalcRequest;
import com.jay.EmiCalc.dto.EmiCalcResponse;
import com.jay.EmiCalc.service.EmiCalcService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmiCalcController.class)
public class EmiCalcControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmiCalcService emiCalcService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void calculateEMI_ValidRequest_ReturnsOk() throws Exception {
        EmiCalcRequest request = new EmiCalcRequest();
        request.setPrincipal(100000);
        request.setAnnualInterestRate(10);
        request.setLoanTermYears(10);

        EmiCalcResponse response = new EmiCalcResponse(100.0, 12000.0, 2000.0);
        when(emiCalcService.calculateEMI(any(EmiCalcRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/emi/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.emi").value(100.0));
    }

    @Test
    void calculateEMI_InvalidRequest_ReturnsBadRequest() throws Exception {
        EmiCalcRequest request = new EmiCalcRequest();
        // Should fail
        request.setPrincipal(-100);
        
        mockMvc.perform(post("/api/emi/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
