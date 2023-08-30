package com.company.gamestore.repository;

import com.company.gamestore.model.Tshirt;
import com.company.gamestore.repository.TshirtRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TshirtRepositoryTest {
    @Autowired
    TshirtRepository tshirtRepository;


    @BeforeEach
    public void setUp() throws Exception {
        tshirtRepository.deleteAll();
    }

    // Create
    @Test
    public void shouldAddTshirt() {
        // Arrange
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("white");
        tshirt.setDescription("Embroidered Superman Logo");
        tshirt.setPrice(BigDecimal.valueOf(87.99));
        tshirt.setQuantity(15);
        tshirt.setSize("med");

        // Act
        tshirt = tshirtRepository.save(tshirt);

        Optional<Tshirt> tshirt1 = tshirtRepository.findById(tshirt.getTshirt_id());

        // Assert
        assertEquals(tshirt, tshirt1.get());
    }

    // Read By ID
    @Test
    public void shouldGetTshirtById() {
        // Arrange
        Tshirt tshirt1 = new Tshirt();
        tshirt1.setColor("white");
        tshirt1.setDescription("Embroidered Superman Logo");
        tshirt1.setPrice(BigDecimal.valueOf(87.99));
        tshirt1.setQuantity(15);
        tshirt1.setSize("med");

        Tshirt tshirt2 = new Tshirt();
        tshirt2.setColor("black");
        tshirt2.setDescription("Printed Wall-E Logo");
        tshirt2.setPrice(BigDecimal.valueOf(64.99));
        tshirt2.setQuantity(30);
        tshirt2.setSize("lrg");

        // Act
        tshirt1 = tshirtRepository.save(tshirt1);
        tshirt2 = tshirtRepository.save(tshirt2);
        Optional<Tshirt> findTshirt = tshirtRepository.findById(tshirt1.getTshirt_id());

        // Assert
        assertEquals(findTshirt.get(), tshirt1);
    }

    // Read By Color
    @Test
    public void shouldGetTshirtByColor() {
        // Arrange
        Tshirt tshirt1 = new Tshirt();
        tshirt1.setColor("white");
        tshirt1.setDescription("Embroidered Superman Logo");
        tshirt1.setPrice(BigDecimal.valueOf(87.99));
        tshirt1.setQuantity(15);
        tshirt1.setSize("med");

        Tshirt tshirt2 = new Tshirt();
        tshirt2.setColor("black");
        tshirt2.setDescription("Printed Wall-E Logo");
        tshirt2.setPrice(BigDecimal.valueOf(64.99));
        tshirt2.setQuantity(30);
        tshirt2.setSize("lrg");

        // Act
        tshirt1 = tshirtRepository.save(tshirt1);
        tshirt2 = tshirtRepository.save(tshirt2);
        Optional<List<Tshirt>> foundShirtsList = tshirtRepository.findByColor(tshirt1.getColor());

        // Assert
        assertEquals(foundShirtsList.get().size(), 1);
    }

    // Read By Size
    @Test
    public void shouldGetTshirtBySize() {
        // Arrange
        Tshirt tshirt1 = new Tshirt();
        tshirt1.setColor("white");
        tshirt1.setDescription("Embroidered Superman Logo");
        tshirt1.setPrice(BigDecimal.valueOf(87.99));
        tshirt1.setQuantity(15);
        tshirt1.setSize("med");

        Tshirt tshirt2 = new Tshirt();
        tshirt2.setColor("black");
        tshirt2.setDescription("Printed Wall-E Logo");
        tshirt2.setPrice(BigDecimal.valueOf(64.99));
        tshirt2.setQuantity(30);
        tshirt2.setSize("lrg");

        // Act
        tshirt1 = tshirtRepository.save(tshirt1);
        tshirt2 = tshirtRepository.save(tshirt2);
        Optional<List<Tshirt>> foundShirtsList = tshirtRepository.findBySize(tshirt1.getSize());

        // Assert
        assertEquals(foundShirtsList.get().size(), 1);
    }


    // Read All
    @Test
    public void shouldGetAllAuthors() {
        // Arrange
        Tshirt tshirt1 = new Tshirt();
        tshirt1.setColor("white");
        tshirt1.setDescription("Embroidered Superman Logo");
        tshirt1.setPrice(BigDecimal.valueOf(87.99));
        tshirt1.setQuantity(15);
        tshirt1.setSize("med");

        Tshirt tshirt2 = new Tshirt();
        tshirt2.setColor("black");
        tshirt2.setDescription("Printed Wall-E Logo");
        tshirt2.setPrice(BigDecimal.valueOf(64.99));
        tshirt2.setQuantity(30);
        tshirt2.setSize("lrg");

        // Act
        tshirt1 = tshirtRepository.save(tshirt1);
        tshirt2 = tshirtRepository.save(tshirt2);
        List<Tshirt> foundTshirts = tshirtRepository.findAll();

        // Assert
        assertEquals(2, foundTshirts.size());
        assertTrue(foundTshirts.contains(tshirt1));
        assertTrue(foundTshirts.contains(tshirt1));
    }


    // Update
    @Test
    public void shouldUpdateAuthor() {
        // Arrange
        Tshirt tshirt1 = new Tshirt();
        tshirt1.setColor("white");
        tshirt1.setDescription("Embroidered Superman Logo");
        tshirt1.setPrice(BigDecimal.valueOf(87.99));
        tshirt1.setQuantity(15);
        tshirt1.setSize("med");

        tshirt1 = tshirtRepository.save(tshirt1);

        tshirt1.setColor("blue");
        tshirt1.setDescription("Embroidered swimming turtle");
        tshirt1.setPrice(BigDecimal.valueOf(90.99));
        tshirt1.setQuantity(40);
        tshirt1.setSize("med");

        tshirt1 = tshirtRepository.save(tshirt1);

        // Act
        Optional<Tshirt> tshirt = tshirtRepository.findById(tshirt1.getTshirt_id());

        // Assert
        assertEquals(tshirt.get(), tshirt1);
    }


    // Delete by ID
    @Test
    public void shouldDeleteAuthorById() {
        // Arrange
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("white");
        tshirt.setDescription("Embroidered Superman Logo");
        tshirt.setPrice(BigDecimal.valueOf(87.99));
        tshirt.setQuantity(15);
        tshirt.setSize("med");

        tshirt = tshirtRepository.save(tshirt);

        // Act
        Optional<Tshirt> foundShirt = tshirtRepository.findById(tshirt.getTshirt_id());

        // Assert
        assertTrue(foundShirt.isPresent());
        assertEquals(foundShirt.get(), tshirt);

        // Act
        tshirtRepository.deleteById(tshirt.getTshirt_id());

        foundShirt = tshirtRepository.findById(tshirt.getTshirt_id());

        // Assert
        assertFalse(foundShirt.isPresent());
    }
}
