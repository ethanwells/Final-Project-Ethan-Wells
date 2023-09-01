package com.company.gamestore.controller;

import com.company.gamestore.model.Invoice;
import com.company.gamestore.repository.InvoiceRepository;
import com.company.gamestore.service.InvoiceServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
@AutoConfigureMockMvc
public class InvoiceControllerTest {

    @MockBean
    private InvoiceRepository invoiceRepository;

    @MockBean
    private InvoiceServiceLayer invoiceServiceLayer;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        invoiceRepository.deleteAll();
    }

    // Create
    @Test
    public void testCreateInvoice() throws Exception {
        Invoice partialData = new Invoice();
        partialData.setName("John Doe");
        partialData.setStreet("123 Main St");
        partialData.setCity("Anytown");
        partialData.setState("CA");
        partialData.setZipcode("12345");
        partialData.setItemType("Consoles");
        partialData.setItemId(1);
        partialData.setQuantity(2);

        Invoice completedData = new Invoice();
        completedData.setInvoiceId(1);
        completedData.setName("John Doe");
        completedData.setStreet("123 Main St");
        completedData.setCity("Anytown");
        completedData.setState("CA");
        completedData.setZipcode("12345");
        completedData.setItemType("Consoles");
        completedData.setItemId(1);
        completedData.setUnitPrice(new BigDecimal("499.99"));
        completedData.setQuantity(2);
        completedData.setSubtotal(new BigDecimal("999.98"));
        completedData.setTax(new BigDecimal("89.99"));
        completedData.setProcessingFee(new BigDecimal("10.00"));
        completedData.setTotal(new BigDecimal("1099.97"));


        when(invoiceServiceLayer.createInvoice(partialData)).thenReturn(completedData);
        ObjectMapper objectMapper = new ObjectMapper();


        mockMvc.perform(
                        post("/invoice")
                                .content(objectMapper.writeValueAsString(partialData))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());


    }


    // Read By ID
    @Test
    public void testGetInvoiceById() throws Exception {
        // ACT
        mockMvc.perform(
                        get("/invoice/1")  // Perform the GET request
                                .contentType(MediaType.APPLICATION_JSON))  // Tell the server it's in JSON format
                .andDo(print())  // Print results to invoice
                .andExpect(status().isOk());
    }

    @Test
    public void testGetInvoiceByName() throws Exception {
        // ACT
        mockMvc.perform(
                        get("/invoice/by-name")  // Perform the GET request
                                .param("name", "name1")  // Add query parameter
                                .contentType(MediaType.APPLICATION_JSON))  // Tell the server it's in JSON format
                .andDo(print())  // Print results to console
                .andExpect(status().isOk());
    }

    // Read All
    @Test
    public void testGetAllInvoices() throws Exception {
        // ACT
        mockMvc.perform(
                        get("/invoices")  // Perform the GET request
                                .contentType(MediaType.APPLICATION_JSON))  // Tell the server it's in JSON format
                .andDo(print())  // Print results to invoice
                .andExpect(status().isOk());
    }

    // Update
    @Test
    public void updateInvoiceTest() throws Exception {
        // ARRANGE
        int invoiceIdToUpdate = 1; // Assuming the ID is 1 for this example
        Invoice invoice = new Invoice();
        invoice.setName("name1");
        invoice.setStreet("street1");
        invoice.setCity("city1");
        invoice.setState("state1");
        invoice.setZipcode("zipcode1");
        invoice.setItemType("itemType1");
        invoice.setItemId(3);
        invoice.setUnitPrice(new BigDecimal("111.11"));
        invoice.setQuantity(5);
        invoice.setSubtotal(new BigDecimal("333.33"));
        invoice.setTax(new BigDecimal("535.55"));
        invoice.setProcessingFee(new BigDecimal("777.77"));
        invoice.setTotal(new BigDecimal("131.31"));

        // Mock the `existsById` method since your controller is using it
        when(invoiceRepository.existsById(invoiceIdToUpdate)).thenReturn(true);

        // Mock the `save` method to return the saved invoice
        when(invoiceRepository.save(any(Invoice.class))).thenReturn(invoice);

        ObjectMapper objectMapper = new ObjectMapper();

        // ACT & ASSERT
        mockMvc.perform(
                        put("/invoice/" + invoiceIdToUpdate)  // Corrected the URL to match the controller
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(invoice)))
                .andDo(print())
                .andExpect(status().isNoContent()); // 204 status code
    }


    // Delete by ID
    @Test
    public void deleteInvoiceTest() throws Exception {
        // ARRANGE
        int invoiceIdToDelete = 1;

        // Set up mock repository behavior
        when(invoiceRepository.existsById(invoiceIdToDelete)).thenReturn(true);
        doNothing().when(invoiceRepository).deleteById(invoiceIdToDelete);

        // ACT
        mockMvc.perform(
                        delete("/invoice/1"))  // Perform the delete request
                .andDo(print())  // Print results to invoice
                .andExpect(status().isNoContent());  // ASSERT (status code is 204)
    }
}
