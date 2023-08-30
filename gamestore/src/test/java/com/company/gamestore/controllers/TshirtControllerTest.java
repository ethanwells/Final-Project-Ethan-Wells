package com.company.gamestore.controllers;

import com.company.gamestore.model.Tshirt;
import com.company.gamestore.repository.TshirtRepository;
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
public class TshirtControllerTest {

    @MockBean
    private TshirtRepository tshirtRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        tshirtRepository.deleteAll();
    }

    // Create
    @Test
    public void testCreateTshirt() throws Exception {
        // ARRANGE
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("white");
        tshirt.setDescription("Embroidered Superman Logo");
        tshirt.setPrice(BigDecimal.valueOf(87.99));
        tshirt.setQuantity(15);
        tshirt.setSize("med");

        Mockito.when(tshirtRepository.save(Mockito.any(Tshirt.class)))
                .thenReturn(tshirt);

        ObjectMapper objectMapper = new ObjectMapper();

        // ACT
        mockMvc.perform(
                        post("/tshirt")  // Perform the POST request
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(tshirt)))  // Request payload as JSON
                .andDo(print())  // Print results to console
                .andExpect(status().isCreated());  // 201 code
    }


    // Read By ID
    @Test
    public void testGetTshirtById() throws Exception {
        // ACT
        mockMvc.perform(
                        get("/tshirt/1")  // Perform the GET request
                                .contentType(MediaType.APPLICATION_JSON))  // Tell the server it's in JSON format
                .andDo(print())  // Print results to console
                .andExpect(status().isOk());
    }

    @Test
    public void testGetTshirtByColor() throws Exception {
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("white");
        tshirt.setDescription("Embroidered Superman Logo");
        tshirt.setPrice(BigDecimal.valueOf(87.99));
        tshirt.setQuantity(15);
        tshirt.setSize("med");

        Tshirt tshirt2 = new Tshirt();
        tshirt2.setColor("black");
        tshirt2.setDescription("Embroidered Superman Logo");
        tshirt2.setPrice(BigDecimal.valueOf(87.99));
        tshirt2.setQuantity(15);
        tshirt2.setSize("lrg");
        // ARRANGE
        mockMvc.perform(
                        get("/tshirt/color/white")   // Perform the GET request
                                .contentType(MediaType.APPLICATION_JSON))   // Tell the server it's in JSON format
                .andDo(print()) // Print results to console
                .andExpect(status().isOk());
    }

    @Test
    public void testGetTshirtBySize() throws Exception {
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("white");
        tshirt.setDescription("Embroidered Superman Logo");
        tshirt.setPrice(BigDecimal.valueOf(87.99));
        tshirt.setQuantity(15);
        tshirt.setSize("med");

        Tshirt tshirt2 = new Tshirt();
        tshirt2.setColor("black");
        tshirt2.setDescription("Embroidered Superman Logo");
        tshirt2.setPrice(BigDecimal.valueOf(87.99));
        tshirt2.setQuantity(15);
        tshirt2.setSize("lrg");
        // ARRANGE
        mockMvc.perform(
                        get("/tshirt/size/lrg")   // Perform the GET request
                                .contentType(MediaType.APPLICATION_JSON))   // Tell the server it's in JSON format
                .andDo(print()) // Print results to console
                .andExpect(status().isOk());
    }

    // Read All
    @Test
    public void testGetAllTshirts() throws Exception {
        // ACT
        mockMvc.perform(
                        get("/tshirts")  // Perform the GET request
                                .contentType(MediaType.APPLICATION_JSON))  // Tell the server it's in JSON format
                .andDo(print())  // Print results to console
                .andExpect(status().isOk());
    }

    // Update
    @Test
    public void updateTshirtTest() throws Exception {
        //ARRANGE
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("white");
        tshirt.setDescription("Embroidered Superman Logo");
        tshirt.setPrice(BigDecimal.valueOf(87.99));
        tshirt.setQuantity(15);
        tshirt.setSize("med");

        Mockito.when(tshirtRepository.save(Mockito.any(Tshirt.class)))
                .thenReturn(tshirt);

        ObjectMapper objectMapper = new ObjectMapper();

        // ACT
        mockMvc.perform(
                        put("/tshirt", tshirt.getTshirt_id())  // Perform the PUT request
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(tshirt)))
                .andDo(print())  // Print results to console
                .andExpect(status().isNoContent());  // ASSERT (status code is 204)
    }

    // Delete by ID
    @Test
    public void deleteTshirtTest() throws Exception {
        // ACT
        mockMvc.perform(
                        delete("/tshirt/1"))  // Perform the delete request
                .andDo(print())  // Print results to console
                .andExpect(status().isNoContent());  // ASSERT (status code is 204)
    }
}
