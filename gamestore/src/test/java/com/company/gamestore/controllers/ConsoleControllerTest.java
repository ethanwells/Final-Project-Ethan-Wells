package com.company.gamestore.controllers;

import com.company.gamestore.model.Console;
import com.company.gamestore.repository.ConsoleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ConsoleControllerTest {

    @MockBean
    private ConsoleRepository consoleRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        consoleRepository.deleteAll();
    }

    // Create
    @Test
    public void testCreateConsole() throws Exception {
        // ARRANGE
        Console console = new Console();
        console.setModel("model");
        console.setManufacturer("manufacturer");
        console.setMemoryAmount("memoryAmount");
        console.setProcessor("processor");
        console.setPrice(new BigDecimal("537.71"));

        Mockito.when(consoleRepository.save(Mockito.any(Console.class)))
                .thenReturn(console);

        ObjectMapper objectMapper = new ObjectMapper();

        // ACT
        mockMvc.perform(
                        post("/console")  // Perform the POST request
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(console)))  // Request payload as JSON
                .andDo(print())  // Print results to console
                .andExpect(status().isCreated());  // 201 code
    }


    // Read By ID
    @Test
    public void testGetConsoleById() throws Exception {
        // ACT
        mockMvc.perform(
                        get("/console/1")  // Perform the GET request
                                .contentType(MediaType.APPLICATION_JSON))  // Tell the server it's in JSON format
                .andDo(print())  // Print results to console
                .andExpect(status().isOk());
    }

    // Read All
    @Test
    public void testGetAllConsoles() throws Exception {
        // ACT
        mockMvc.perform(
                        get("/console/consoles")  // Perform the GET request
                                .contentType(MediaType.APPLICATION_JSON))  // Tell the server it's in JSON format
                .andDo(print())  // Print results to console
                .andExpect(status().isOk());
    }

    // Update
    @Test
    public void updateConsoleTest() throws Exception {
        //ARRANGE
        Console console = new Console();
        console.setModel("model1");
        console.setManufacturer("manufacturer1");
        console.setMemoryAmount("memoryAmount1");
        console.setProcessor("processor1");
        console.setPrice(new BigDecimal("537.71"));

        Mockito.when(consoleRepository.save(Mockito.any(Console.class)))
                .thenReturn(console);

        ObjectMapper objectMapper = new ObjectMapper();

        // ACT
        mockMvc.perform(
                        put("/console", console.getConsoleId())  // Perform the PUT request
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(console)))
                .andDo(print())  // Print results to console
                .andExpect(status().isNoContent());  // ASSERT (status code is 204)
    }

    // Delete by ID
    @Test
    public void deleteConsoleTest() throws Exception {
        // ACT
        mockMvc.perform(
                        delete("/console/1"))  // Perform the delete request
                .andDo(print())  // Print results to console
                .andExpect(status().isNoContent());  // ASSERT (status code is 204)
    }
}
