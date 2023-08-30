package com.company.gamestore.repository;
import com.company.gamestore.model.Tax;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaxRepository extends JpaRepository<Tax, Integer> {
    public Optional<Tax> findTaxByState (String state);

}