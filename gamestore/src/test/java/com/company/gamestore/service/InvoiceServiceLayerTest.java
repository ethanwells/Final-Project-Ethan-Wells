package com.company.gamestore.service;

import com.company.gamestore.model.*;
import com.company.gamestore.repository.*;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InvoiceServiceLayerTest {

    private ConsoleRepository consoleRepository;

    private FeeRepository feeRepository;

    private GameRepository gameRepository;

    private InvoiceRepository invoiceRepository;

    private TaxRepository taxRepository;

    private TshirtRepository tshirtRepository;
    private InvoiceServiceLayer invoiceServiceLayer;



    @BeforeEach
    private void setUpMock(){
        setUpConsoleRepositoryMock();
        setUpFeeRepositoryMock();
        setUpGameRepositoryMock();
        setUpInvoiceRepositoryMock();
        setUpTshirtRepositoryMock();
        setUpTaxRepositoryMock();

        invoiceServiceLayer = new InvoiceServiceLayer(gameRepository, taxRepository, feeRepository, invoiceRepository, tshirtRepository, consoleRepository);


    }
    private void setUpConsoleRepositoryMock(){
        consoleRepository = mock(ConsoleRepository.class);

        // Create a sample console object
        Console console1 = new Console(
                1, // consoleId
                "PlayStation 5", // model
                "Sony", // manufacturer
                "1TB", // memoryAmount
                "Octa-core", // processor
                new BigDecimal("499.99"), // price
                100 // quantity
        );

        // Create a list of mock consoles
        List<Console> mockConsoles = new ArrayList<>();
        mockConsoles.add(console1);


        // Set up behavior for the save method
        doReturn(console1).when(consoleRepository).save(any(Console.class));
        doReturn(Optional.of(console1)).when(consoleRepository).findById(1);
        doReturn(mockConsoles).when(consoleRepository).findAll();


    }

    private void setUpFeeRepositoryMock(){
        feeRepository = mock(FeeRepository.class);

        // Create a sample fee object
        Fee fee1 = new Fee("Consoles", new BigDecimal("14.99"));


        // Create a list of mock fees
        List<Fee> mockFees = new ArrayList<>();
        mockFees.add(fee1);

        // Set up behavior for the save method
        doReturn(fee1).when(feeRepository).save(any(Fee.class));
        doReturn(Optional.of(fee1)).when(feeRepository).findFeeByProductType("Consoles");
        doReturn(mockFees).when(feeRepository).findAll();
    }

    private void setUpGameRepositoryMock(){
        gameRepository = mock(GameRepository.class);

        // Create a sample game object
        Game game1 = new Game(
                "The Legend of Zelda: Breath of the Wild",
                "E10+", // ESRB rating
                "An open-world action-adventure game.", // description
                new BigDecimal("59.99"), // price
                "Nintendo", // studio
                50 // quantity
        );

        // Create a list of mock games
        List<Game> mockGames = new ArrayList<>();
        mockGames.add(game1);

        // Set up behavior for the save method
        doReturn(game1).when(gameRepository).save(any(Game.class));
        doReturn(Optional.of(game1)).when(gameRepository).findById(anyInt());
        doReturn(mockGames).when(gameRepository).findAll();

        // You can also set up additional behaviors as needed
    }

    private void setUpInvoiceRepositoryMock(){
        invoiceRepository = mock(InvoiceRepository.class);

        // Create a sample invoice object
        Invoice invoice1 = new Invoice();
        invoice1.setName("John Doe");
        invoice1.setStreet("123 Main St");
        invoice1.setCity("Anytown");
        invoice1.setState("CA");
        invoice1.setZipcode("12345");
        invoice1.setItemId(1);
        invoice1.setItemType("Consoles");
        invoice1.setQuantity(2);




        Invoice invoice2 = new Invoice();
        invoice2.setInvoiceId(1);
        invoice2.setName("John Doe");
        invoice2.setStreet("123 Main St");
        invoice2.setCity("Anytown");
        invoice2.setState("CA");
        invoice2.setZipcode("12345");
        invoice2.setItemType("Consoles");
        invoice2.setItemId(1);
        invoice2.setUnitPrice(new BigDecimal("499.99"));
        invoice2.setQuantity(2);
        invoice2.setSubtotal(new BigDecimal("999.98"));
        invoice2.setTax(new BigDecimal("79.9984"));
        invoice2.setProcessingFee(new BigDecimal("14.99"));
        invoice2.setTotal(new BigDecimal("1094.97"));



        // Create a list of mock invoices
        List<Invoice> mockInvoices = new ArrayList<>();
        mockInvoices.add(invoice1);

        // Set up behavior for the save method
        doReturn(invoice2).when(invoiceRepository).save(any(Invoice.class));
        doReturn(Optional.of(invoice2)).when(invoiceRepository).findById(1);
        doReturn(mockInvoices).when(invoiceRepository).findAll();
    }

    private void setUpTaxRepositoryMock(){
        taxRepository = mock(TaxRepository.class);

        // Create a sample tax object
        Tax tax1 = new Tax(
                "CA", // state
                new BigDecimal("0.08") // rate
        );

        // Create a list of mock taxes
        List<Tax> mockTaxes = new ArrayList<>();
        mockTaxes.add(tax1);

        // Set up behavior for the save method
        doReturn(tax1).when(taxRepository).save(any(Tax.class));
        doReturn(Optional.of(tax1)).when(taxRepository).findTaxByState("CA");
        doReturn(mockTaxes).when(taxRepository).findAll();

    }

    private void setUpTshirtRepositoryMock(){
        tshirtRepository = mock(TshirtRepository.class);

        // Create a sample t-shirt object
        Tshirt tshirt1 = new Tshirt();
        tshirt1.setTshirt_id(1);
        tshirt1.setSize("Medium");
        tshirt1.setColor("Black");
        tshirt1.setDescription("A comfortable t-shirt.");
        tshirt1.setPrice(new BigDecimal("19.99"));
        tshirt1.setQuantity(50);

        // Create a list of mock t-shirts
        List<Tshirt> mockTshirts = new ArrayList<>();
        mockTshirts.add(tshirt1);

        // Set up behavior for the save method
        doReturn(tshirt1).when(tshirtRepository).save(any(Tshirt.class));
        doReturn(Optional.of(tshirt1)).when(tshirtRepository).findById(anyInt());
        doReturn(mockTshirts).when(tshirtRepository).findAll();
    }

    @Test
    public void testCreateInvoice(){
        Invoice invoice1 = new Invoice();
        invoice1.setName("John Doe");
        invoice1.setStreet("123 Main St");
        invoice1.setCity("Anytown");
        invoice1.setState("CA");
        invoice1.setZipcode("12345");
        invoice1.setItemId(1);
        invoice1.setItemType("Consoles");
        invoice1.setQuantity(2);

        Invoice actual = invoiceServiceLayer.createInvoice(invoice1);

        Invoice expected = new Invoice();
        expected.setInvoiceId(1);
        expected.setName("John Doe");
        expected.setStreet("123 Main St");
        expected.setCity("Anytown");
        expected.setState("CA");
        expected.setZipcode("12345");
        expected.setItemType("Consoles");
        expected.setItemId(1);
        expected.setUnitPrice(new BigDecimal("499.99"));
        expected.setQuantity(2);
        expected.setSubtotal(new BigDecimal("999.98"));
        expected.setTax(new BigDecimal("79.00"));
        expected.setProcessingFee(new BigDecimal("14.99"));
        expected.setTotal(new BigDecimal("1094.97"));



        assertEquals(expected, actual);
    }

    @Test
    public void testSupplyMeetsDemand() {
        assertTrue(invoiceServiceLayer.supplyMeetsDemand(50, 10, "T-shirt"));
        assertFalse(invoiceServiceLayer.supplyMeetsDemand(10, 50, "Game"));
    }

}
