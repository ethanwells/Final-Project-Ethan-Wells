package com.company.gamestore.repository;
import com.company.gamestore.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Integer> {
    public Optional<List<Game>> findGameByStudio (String studio);
    public Optional<List<Game>> findGameByEsrbRating (String esrbRating);
    public Optional<List<Game>> findGameByTitle (String title);
}