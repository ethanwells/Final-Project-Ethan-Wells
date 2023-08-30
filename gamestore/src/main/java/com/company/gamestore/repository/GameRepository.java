package com.company.gamestore.repository;
import com.company.gamestore.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Integer> {
    public List<Game> findGameByStudio (String studio);
    public List<Game> findGameByESRB (String esrbRating);
    public List<Game> findGameByTitle (String title);
}