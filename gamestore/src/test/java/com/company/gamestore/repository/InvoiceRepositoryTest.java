package com.company.gamestore.repository;

import com.company.gamestore.model.Invoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class InvoiceRepositoryTest {

    @Autowired
    InvoiceRepository invoiceRepository;

    @BeforeEach
    public void setUp() throws Exception {
        invoiceRepository.deleteAll();
    }

    // Create
    @Test
    public void shouldAddInvoice() {
        // Arrange
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

        // Act
        invoice = invoiceRepository.save(invoice);

        Optional<Invoice> invoice1 = invoiceRepository.findById(invoice.getInvoiceId());

        // Assert
        assertEquals(invoice, invoice1.get());
    }

    // Read By ID
    @Test
    public void shouldGetInvoiceById() {
        // Arrange
        Invoice invoice1 = new Invoice();
        invoice1.setName("name1");
        invoice1.setStreet("street1");
        invoice1.setCity("city1");
        invoice1.setState("state1");
        invoice1.setZipcode("zipcode1");
        invoice1.setItemType("itemType1");
        invoice1.setItemId(3);
        invoice1.setUnitPrice(new BigDecimal("111.11"));
        invoice1.setQuantity(5);
        invoice1.setSubtotal(new BigDecimal("333.33"));
        invoice1.setTax(new BigDecimal("535.55"));
        invoice1.setProcessingFee(new BigDecimal("777.77"));
        invoice1.setTotal(new BigDecimal("131.31"));

        // Act
        invoice1 = invoiceRepository.save(invoice1);
        Optional<Invoice> foundInvoice = invoiceRepository.findById(invoice1.getInvoiceId());

        // Assert
        assertEquals(foundInvoice.get(), invoice1);
    }


    // Read All
    @Test
    public void shouldGetAllInvoices() {
        // Arrange
        Invoice invoice1 = new Invoice();
        invoice1.setName("name1");
        invoice1.setStreet("street1");
        invoice1.setCity("city1");
        invoice1.setState("state1");
        invoice1.setZipcode("zipcode1");
        invoice1.setItemType("itemType1");
        invoice1.setItemId(3);
        invoice1.setUnitPrice(new BigDecimal("111.11"));
        invoice1.setQuantity(5);
        invoice1.setSubtotal(new BigDecimal("333.33"));
        invoice1.setTax(new BigDecimal("535.55"));
        invoice1.setProcessingFee(new BigDecimal("777.77"));
        invoice1.setTotal(new BigDecimal("131.31"));

        Invoice invoice2 = new Invoice();
        invoice2.setName("name2");
        invoice2.setStreet("street2");
        invoice2.setCity("city2");
        invoice2.setState("state2");
        invoice2.setZipcode("zipcode2");
        invoice2.setItemType("itemType2");
        invoice2.setItemId(23);
        invoice2.setUnitPrice(new BigDecimal("211.11"));
        invoice2.setQuantity(25);
        invoice2.setSubtotal(new BigDecimal("233.33"));
        invoice2.setTax(new BigDecimal("235.55"));
        invoice2.setProcessingFee(new BigDecimal("277.77"));
        invoice2.setTotal(new BigDecimal("231.31"));

        // Act
        invoice1 = invoiceRepository.save(invoice1);
        invoice2 = invoiceRepository.save(invoice2);
        List<Invoice> foundInvoices = invoiceRepository.findAll();

        // Assert
        assertEquals(2, foundInvoices.size());
        assertTrue(foundInvoices.contains(invoice1));
        assertTrue(foundInvoices.contains(invoice2));
    }


    // Update
    @Test
    public void shouldUpdateInvoice() {
        // Arrange
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

        invoice = invoiceRepository.save(invoice);

        invoice.setName("name2");
        invoice.setStreet("street2");
        invoice.setCity("city2");
        invoice.setState("state2");
        invoice.setZipcode("zipcode2");
        invoice.setItemType("itemType2");
        invoice.setItemId(23);
        invoice.setUnitPrice(new BigDecimal("211.11"));
        invoice.setQuantity(25);
        invoice.setSubtotal(new BigDecimal("233.33"));
        invoice.setTax(new BigDecimal("235.55"));
        invoice.setProcessingFee(new BigDecimal("277.77"));
        invoice.setTotal(new BigDecimal("231.31"));

        invoice = invoiceRepository.save(invoice);

        // Act
        Optional<Invoice> invoice1 = invoiceRepository.findById(invoice.getInvoiceId());

        // Assert
        assertEquals(invoice1.get(), invoice);
    }


    // Delete by ID
    @Test
    public void shouldDeleteInvoiceById() {
        // Arrange
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

        invoice = invoiceRepository.save(invoice);

        // Act
        Optional<Invoice> foundInvoice = invoiceRepository.findById(invoice.getInvoiceId());

        // Assert
        assertTrue(foundInvoice.isPresent());
        assertEquals(foundInvoice.get(), invoice);

        // Act
        invoiceRepository.deleteById(invoice.getInvoiceId());

        foundInvoice = invoiceRepository.findById(invoice.getInvoiceId());

        // Assert
        assertFalse(foundInvoice.isPresent());
    }
}
