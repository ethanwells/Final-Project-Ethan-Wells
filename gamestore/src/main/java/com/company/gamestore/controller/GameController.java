package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.repository.GameRepository;
import org.aspectj.lang.annotation.DeclareWarning;
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

    @GetMapping("/game/games")
    public List<Game> getAllGames() {
        return gameRepo.findAll();
    }

    @GetMapping("/game/{gameId}")
    public Optional<Game> getGameById(@PathVariable("gameId") int gameId) {
        return gameRepo.findById(gameId);
    }

    @GetMapping("/game/by-studio")
    public List<Game> getGamesByStudio(@RequestParam("studio") String studio) {
        return gameRepo.findGameByStudio(studio).get();
    }

    @GetMapping("/game/by-rating")
    public List<Game> getGamesByESRB(@RequestParam("esrbRating") String esrbRating) {
        return gameRepo.findGameByEsrbRating(esrbRating).get();
    }

    @GetMapping("/game/by-title")
    public List<Game> getGamesByTitle(@RequestParam("title") String title) {
        return gameRepo.findGameByTitle(title).get();
    }


    @PostMapping("/game")
    @ResponseStatus(HttpStatus.CREATED)
    public Game addGame(@Valid @RequestBody Game game) {
        return gameRepo.save(game);
    }

    @PutMapping("/game")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(@Valid @RequestBody Game game) {
        gameRepo.save(game);
    }

    @DeleteMapping("/game/{gameId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable("gameId") int gameId) {
        gameRepo.deleteById(gameId);
    }
}
