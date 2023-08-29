package com.company.gamestore.repository;
import com.company.gamestore.model.Console;
import com.company.gamestore.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsoleRepository extends JpaRepository<Console, Integer> {


}