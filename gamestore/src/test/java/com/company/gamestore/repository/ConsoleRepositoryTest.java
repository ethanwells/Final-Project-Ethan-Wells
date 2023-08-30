package com.company.gamestore.repository;

import com.company.gamestore.model.Console;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ConsoleRepositoryTest {

    @Autowired
    ConsoleRepository consoleRepository;

    @BeforeEach
    public void setUp() throws Exception {
        consoleRepository.deleteAll();
    }

    // Create
    @Test
    public void shouldAddConsole() {
        // Arrange
        Console console = new Console();
        console.setModel("model1");
        console.setManufacturer("manufacturer1");
        console.setMemoryAmount("memoryAmount1");
        console.setProcessor("processor1");
        console.setPrice(new BigDecimal("123.57"));

        // Act
        console = consoleRepository.save(console);

        Optional<Console> console1 = consoleRepository.findById(console.getConsoleId());

        // Assert
        assertEquals(console, console1.get());
    }

    // Read By ID
    @Test
    public void shouldGetConsoleById() {
        // Arrange
        Console console1 = new Console();
        console1.setModel("model1");
        console1.setManufacturer("manufacturer1");
        console1.setMemoryAmount("memoryAmount1");
        console1.setProcessor("processor1");
        console1.setPrice(new BigDecimal("123.57"));

        // Act
        console1 = consoleRepository.save(console1);
        Optional<Console> foundConsole = consoleRepository.findById(console1.getConsoleId());

        // Assert
        assertEquals(foundConsole.get(), console1);
    }

    // Read By Manufacturer
    @Test
    public void shouldGetConsoleByManufacturer() {
        // Arrange
        Console console1 = new Console();
        console1.setModel("model1");
        console1.setManufacturer("manufacturer1");
        console1.setMemoryAmount("memoryAmount1");
        console1.setProcessor("processor1");
        console1.setPrice(new BigDecimal("123.57"));

        // Act
        console1 = consoleRepository.save(console1);
        Optional<List<Console>> foundConsole = consoleRepository.findConsoleByManufacturer(console1.getManufacturer());

        // Assert
        assertTrue(foundConsole.get().contains(console1));
    }

    // Read All
    @Test
    public void shouldGetAllConsoles() {
        // Arrange
        Console console1 = new Console();
        console1.setModel("model1");
        console1.setManufacturer("manufacturer1");
        console1.setMemoryAmount("memoryAmount1");
        console1.setProcessor("processor1");
        console1.setPrice(new BigDecimal("123.57"));

        Console console2 = new Console();
        console2.setModel("model2");
        console2.setManufacturer("manufacturer2");
        console2.setMemoryAmount("memoryAmount2");
        console2.setProcessor("processor2");
        console2.setPrice(new BigDecimal("531.75"));

        // Act
        console1 = consoleRepository.save(console1);
        console2 = consoleRepository.save(console2);
        List<Console> foundConsoles = consoleRepository.findAll();

        // Assert
        assertEquals(2, foundConsoles.size());
        assertTrue(foundConsoles.contains(console1));
        assertTrue(foundConsoles.contains(console2));
    }


    // Update
    @Test
    public void shouldUpdateConsole() {
        // Arrange
        Console console = new Console();
        console.setModel("model1");
        console.setManufacturer("manufacturer1");
        console.setMemoryAmount("memoryAmount1");
        console.setProcessor("processor1");
        console.setPrice(new BigDecimal("123.57"));

        console = consoleRepository.save(console);

        console.setModel("model2");
        console.setManufacturer("manufacturer2");
        console.setMemoryAmount("memoryAmount2");
        console.setProcessor("processor2");
        console.setPrice(new BigDecimal("537.71"));

        console = consoleRepository.save(console);

        // Act
        Optional<Console> console1 = consoleRepository.findById(console.getConsoleId());

        // Assert
        assertEquals(console1.get(), console);
    }


    // Delete by ID
    @Test
    public void shouldDeleteConsoleById() {
        // Arrange
        Console console = new Console();
        console.setModel("model1");
        console.setManufacturer("manufacturer1");
        console.setMemoryAmount("memoryAmount1");
        console.setProcessor("processor1");
        console.setPrice(new BigDecimal("123.57"));

        console = consoleRepository.save(console);

        // Act
        Optional<Console> foundConsole = consoleRepository.findById(console.getConsoleId());

        // Assert
        assertTrue(foundConsole.isPresent());
        assertEquals(foundConsole.get(), console);

        // Act
        consoleRepository.deleteById(console.getConsoleId());

        foundConsole = consoleRepository.findById(console.getConsoleId());

        // Assert
        assertFalse(foundConsole.isPresent());
    }
}
