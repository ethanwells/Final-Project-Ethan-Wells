package com.company.gamestore.repository;
import com.company.gamestore.model.Console;
import com.company.gamestore.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsoleRepository extends JpaRepository<Console, Integer> {
    public Optional<List<Console>> findConsoleByManufacturer (String manufacturer);


}