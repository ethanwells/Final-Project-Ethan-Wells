package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
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

    @GetMapping("/game/games")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getAllGames() {
        return gameRepo.findAll();
    }

    @GetMapping("/game/{gameId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Game> getGameById(@PathVariable("gameId") int gameId) {
        return gameRepo.findById(gameId);
    }

    @GetMapping("/game/by-studio")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGamesByStudio(@RequestParam("studio") String studio) {
        Optional<List<Game>> returnVal = gameRepo.findGameByStudio(studio);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    @GetMapping("/game/by-rating")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGamesByESRB(@RequestParam("esrbRating") String esrbRating) {
        Optional<List<Game>> returnVal = gameRepo.findGameByEsrbRating(esrbRating);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    @GetMapping("/game/by-title")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGamesByTitle(@RequestParam("title") String title) {

        Optional<List<Game>> returnVal = gameRepo.findGameByTitle(title);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
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
