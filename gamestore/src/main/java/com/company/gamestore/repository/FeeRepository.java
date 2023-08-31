package com.company.gamestore.repository;
import com.company.gamestore.model.Fee;
import com.company.gamestore.model.Tax;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeeRepository extends JpaRepository<Fee, Integer> {
    public Optional<Fee> findFeeByProductType (String type);
    void deleteByProductType(String type);
}