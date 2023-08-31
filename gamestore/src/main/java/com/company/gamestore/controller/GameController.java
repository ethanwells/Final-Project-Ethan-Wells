package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class GameController {
    @Autowired
    private GameRepository gameRepo;

    @GetMapping("/games")
    public List<Game> getAllGames() {
        return gameRepo.findAll();
    }

    @GetMapping("/games/{gameId}")
    public Optional<Game> getGameById(@PathVariable("gameId") int gameId) {
        return gameRepo.findById(gameId);
    }

    @GetMapping("/games/by-studio/{studio}")
    public List<Game> getGamesByStudio(@PathVariable("studio") String studio) {
        return gameRepo.findGameByStudio(studio).get();
    }

    @GetMapping("/games/by-rating/{esrbRating}")
    public List<Game> getGamesByESRB(@PathVariable("esrbRating") String esrbRating) {
        return gameRepo.findGameByEsrbRating(esrbRating).get();
    }

    @GetMapping("/games/by-title/{title}")
    public List<Game> getGamesByTitle(@PathVariable("title") String title) {
        return gameRepo.findGameByTitle(title).get();
    }


    @PostMapping("/games")
    @ResponseStatus(HttpStatus.CREATED)
    public Game addGame(@Valid @RequestBody Game game) {
        return gameRepo.save(game);
    }

    @PutMapping("/games")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(@Valid @RequestBody Game game) {
        gameRepo.save(game);
    }

    @DeleteMapping("/games/{gameId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable("gameId") int gameId) {
        gameRepo.deleteById(gameId);
    }
}
