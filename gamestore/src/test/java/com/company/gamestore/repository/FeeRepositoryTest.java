package com.company.gamestore.repository;

import com.company.gamestore.model.Fee;
import com.company.gamestore.model.Game;
import com.company.gamestore.model.Fee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FeeRepositoryTest {

    @Autowired
    FeeRepository feeRepository;

    @BeforeEach
    public void setUp() throws Exception{
        feeRepository.deleteAll();
    }

    //ADD
    @Test
    public void shouldAddFee(){

        //Arrange
        Fee fee1 = new Fee();
        fee1.setFee(BigDecimal.valueOf(14.99));
        fee1.setProductType("Game");

        feeRepository.save(fee1);

        //Act
        Optional<Fee> fee2 = feeRepository.findFeeByProductType(fee1.getProductType());

        //Assert
        assertEquals(fee2.get(), fee1);
    }


    //Get by ID - id is product type
    @Test
    public void shouldGetFeeByProductType(){

        //Arrange
        Fee fee1 = new Fee();
        fee1.setFee(BigDecimal.valueOf(14.99));
        fee1.setProductType("Game");
        feeRepository.save(fee1);

        //Act
        Optional<Fee> foundFee = feeRepository.findFeeByProductType(fee1.getProductType());

        //Assert
        assertEquals(foundFee.get(), fee1);
    }

    //Get All
    @Test
    public void shouldGetAllFees(){

        //Arrange
        Fee fee1 = new Fee();
        fee1.setFee(BigDecimal.valueOf(14.99));
        fee1.setProductType("Game");
        feeRepository.save(fee1);

        //Act
        List<Fee> flist = feeRepository.findAll();

        //Assert
        assertEquals(flist.size(), 1);
    }

    //Update
    @Test
    public void shouldUpdateFee(){

        //Arrange
        Fee fee1 = new Fee();
        fee1.setFee(BigDecimal.valueOf(14.99));
        fee1.setProductType("Game");
        feeRepository.save(fee1);
        fee1.setProductType("T-shirt");
        fee1.setFee(BigDecimal.valueOf(1.98));
        feeRepository.save(fee1);

        //Act
        Optional<Fee> fee2 = feeRepository.findFeeByProductType(fee1.getProductType());

        //Assert
        assertEquals(fee2.get(),fee1);

    }

    //Delete
    @Test
    public void shouldDeleteFeeByProductType(){

        // Arrange
        Fee fee = new Fee();
        fee.setFee(BigDecimal.valueOf(14.99));
        fee.setProductType("Game");

        fee = feeRepository.save(fee);

        // Act
        Optional<Fee> foundFee = feeRepository.findFeeByProductType(fee.getProductType());

        // Assert
        assertTrue(foundFee.isPresent());
        assertEquals(foundFee.get(), fee);

        // Act
        feeRepository.deleteByProductType(fee.getProductType());

        foundFee = feeRepository.findFeeByProductType(fee.getProductType());

        // Assert
        assertFalse(foundFee.isPresent());

    }

}
