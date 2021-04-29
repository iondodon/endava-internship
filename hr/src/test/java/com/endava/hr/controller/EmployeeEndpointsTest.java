package com.endava.hr.controller;

import com.endava.hr.entity.Employee;
import com.endava.hr.entity.dto.EmployeeDto;
import com.endava.hr.repository.EmployeeRepository;
import com.endava.hr.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDate;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeEndpointsTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    @DisplayName("When get all employee first employee should be with First name Anaaa.")
    void testGetAllEmployees() throws Exception {
        mockMvc.perform(get("/employees").param("extend", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[0].firstName").value("Anaaa"));
    }

    @Test
    @DisplayName("When get single employee by ID, correct employee should be returned.")
    void testGetSingleEmployee() throws Exception {
        mockMvc.perform(get("/employees/{id}", 14).param("extend", "false"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.firstName").value("Andrei"));
    }

    @Test
    @DisplayName("When a new employee is created, it is returned and 201 status code is being set.")
    void createNewEmployee() throws Exception {
        mockMvc.perform(
                post("/employees")
                .content(objectMapper.writeValueAsString(new EmployeeDto(null, "A", "B", "a@b", "059473845",
                        LocalDate.now(), "1", 1.1f, 0.1f, null, 1L)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("a@b"));
    }

    @Test
    @DisplayName("When employee is updated, the updated employee is returned and 200 status code is set.")
    void updateEmployee() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto(1L, "Ion", "Dodon", "iondodon2@gmail.com", "069473485",
                LocalDate.now(), "1", 1.11f, 0.11f, null, 1L);

        mockMvc.perform(
                put("/employees")
                .content(objectMapper.writeValueAsString(employeeDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Ion"));
    }

    @Test
    @DisplayName("Should return 200 status code if employee is found and deleted.")
    void deleteById() throws Exception {
        mockMvc.perform(delete("/employees/{id}", 18))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should return 400 status code if employee not found.")
    void deleteByInvalidId() throws Exception {
        mockMvc.perform(delete("/employees/{id}", 180))
                .andExpect(status().isBadRequest());
    }
}