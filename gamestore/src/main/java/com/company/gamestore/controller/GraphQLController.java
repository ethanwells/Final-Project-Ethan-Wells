package com.company.gamestore.controller;


import com.company.gamestore.model.Console;
import com.company.gamestore.model.Game;
import com.company.gamestore.repository.*;
import com.company.gamestore.model.Tshirt;
import com.company.gamestore.repository.ConsoleRepository;
import com.company.gamestore.repository.GameRepository;
import com.company.gamestore.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class GraphQLController {
    @Autowired
    GameRepository gameRepository;

    @Autowired
    ConsoleRepository consoleRepository;

    @Autowired
    TshirtRepository tshirtRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    FeeRepository feeRepository;

    @Autowired
    TaxRepository taxRepository;



    // General CRUD for Game, Console, and Tshirt

    // Game
    @QueryMapping
    public Game findGameById(@Argument int id) {
        Optional<Game> returnVal = this.gameRepository.findById(id);
        return returnVal.orElse(null);
    }

    // Console
    @QueryMapping
    public Console findConsoleById(@Argument int id) {
        Optional<Console> returnVal = this.consoleRepository.findById(id);
        return returnVal.orElse(null);
    }

    @QueryMapping
    public List<Game> getAllGames() {return gameRepository.findAll();}

    @QueryMapping
    public List<Console> getAllConsoles() {return consoleRepository.findAll();}


    @SchemaMapping

    public List<Game> findGameByStudio(String studio) {
        Optional<List<Game>> returnVal = gameRepository.findGameByStudio(studio);

        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    // return game by ESRB rating
    @SchemaMapping
    public List<Game> findGameByEsrbRatingString(String esrbRating) {
        Optional<List<Game>> returnVal = gameRepository.findGameByEsrbRating(esrbRating);

        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    // return game by title
    @SchemaMapping
    public List<Game> findGameByTitle(String title) {
        Optional<List<Game>> returnVal = gameRepository.findGameByTitle(title);

        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }


    // As a user, I would like to be able to search for consoles by manufacturer.

    // return console by manufacturer
    @SchemaMapping
    public List<Console> findConsoleByManufacturer(String manufacturer) {
        Optional<List<Console>> returnVal = consoleRepository.findConsoleByManufacturer(manufacturer);

        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }



}