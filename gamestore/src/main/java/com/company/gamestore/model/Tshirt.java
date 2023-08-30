package com.company.gamestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "Tshirt")
public class Tshirt {
    @Id
    @Column(name="tshirt_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tshirt_id;
    private String size;
    private String color;
    private String description;
    private BigDecimal price;
    private int quantity;

    public int getTshirt_id() {
        return tshirt_id;
    }

    public void setTshirt_id(int tshirt_id) {
        this.tshirt_id = tshirt_id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tshirt tshirt = (Tshirt) o;
        return getTshirt_id() == tshirt.getTshirt_id() && getQuantity() == tshirt.getQuantity() && getSize().equals(tshirt.getSize()) && getColor().equals(tshirt.getColor()) && getDescription().equals(tshirt.getDescription()) && getPrice().equals(tshirt.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTshirt_id(), getSize(), getColor(), getDescription(), getPrice(), getQuantity());
    }

    @Override
    public String toString() {
        return "Tshirt{" +
                "tshirt_id=" + tshirt_id +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
