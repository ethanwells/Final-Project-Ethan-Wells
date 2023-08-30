package com.company.gamestore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Console {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer consoleId;

    @NotBlank(message = "model is required")
    @Size(max = 50, message = "model must not exceed 50 characters")
    private String model; // not null, required, 50 chars

    @NotBlank(message = "manufacturer is required")
    @Size(max = 50, message = "manufacturer name must not exceed 50 characters")
    private String manufacturer; // not null, required, 50 chars

    @Size(max = 20, message = "memoryAmount must not exceed 20 characters")
    private String memoryAmount; // 20 chars


    @Size(max = 20, message = "manufacturer name must not exceed 20 characters")
    private String processor; // 20 chars

    @NotNull(message = "Price is required")
    @Digits(integer=3, fraction=2, message = "Price should have up to 3 integer digits and 2 fraction digits.")
    private BigDecimal price; // not null, required, decimal(5, 2)

    @Min(value = 0, message = "Quantity must be a non-negative value")
    private Integer quantity; // not null, required

    public Console() {}

    public Console(Integer consoleId, String model, String manufacturer, String memoryAmount, String processor, BigDecimal price, Integer quantity) {
        this.consoleId = consoleId;
        this.model = model;
        this.manufacturer = manufacturer;
        this.memoryAmount = memoryAmount;
        this.processor = processor;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getConsoleId() {
        return consoleId;
    }

    public void setConsoleId(Integer consoleId) {
        this.consoleId = consoleId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMemoryAmount() {
        return memoryAmount;
    }

    public void setMemoryAmount(String memoryAmount) {
        this.memoryAmount = memoryAmount;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Console{" +
                "consoleId=" + consoleId +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", memoryAmount='" + memoryAmount + '\'' +
                ", processor='" + processor + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Console console = (Console) o;
        return Objects.equals(consoleId, console.consoleId) && Objects.equals(model, console.model) && Objects.equals(manufacturer, console.manufacturer) && Objects.equals(memoryAmount, console.memoryAmount) && Objects.equals(processor, console.processor) && Objects.equals(price, console.price) && Objects.equals(quantity, console.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consoleId, model, manufacturer, memoryAmount, processor, price, quantity);
    }
}
