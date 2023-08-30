package com.company.gamestore.controller;


import com.company.gamestore.model.Console;
import com.company.gamestore.model.Game;
import com.company.gamestore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;


// General CRUD:

    // GAME
    // As a user, I would like to be able to create, read, update, and delete game information.

    // CONSOLE
    // As a user, I would like to be able to create, read, update, and delete console information.

    // TSHIRT
    // As a user, I would like to be able to create, read, update, and delete T-shirt information.


// GraphQL Custom Methods

    // As a user, I would like to be able to search for games by studio, ESRB rating, and title.

    // As a user, I would like to be able to search for consoles by manufacturer.

    // As a user, I would like to be able to search for games by color and size.


// todo: not sure how to implement the following with graphql:

    // As a user, I would like to be able to purchase a specified quantity of products (games, consoles, T-shirts) and an invoice will be created that includes any taxes and processing fees.


@Controller
public class GraphController {
    @Autowired
    GameRepository gameRepository;

    @Autowired
    ConsoleRepository consoleRepository;

    @Autowired
    TshirtRepository tshirtRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    ProcessingFeeRepository feeRepository;

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
        return returnVal.isPresent() ? (Console)returnVal.get() : null;
    }

    // Tshirt
    @QueryMapping
    public Tshirt findTshirtById(@Argument int id) {
        Optional<Tshirt> returnVal = this.tshirtRepository.findById(id);
        return returnVal.isPresent() ? (Tshirt)returnVal.get() : null;
    }




    // GraphQL Custom Methods


    // As a user, I would like to be able to search for games by studio, ESRB rating, and title.

    // return game by studio
    @SchemaMapping
    public Game game(Studio studio) {
        Optional<Game> returnVal = gameRepository.findById(studio.getId());
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    // return game by ESRB rating
    @SchemaMapping
    public Game game(Esrb_rating esrb_rating) {
        Optional<Game> returnVal = gameRepository.findById(esrb_rating.getId());
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    // return game by title
    @SchemaMapping
    public Game game(Title title) {
        Optional<Game> returnVal = gameRepository.findById(title.getId());
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }


    // As a user, I would like to be able to search for consoles by manufacturer.

    // return console by manufacturer
    @SchemaMapping
    public Console console(Manufacturer manufacturer) {
        Optional<Console> returnVal = consoleRepository.findById(Manufacturer.getId());
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    // As a user, I would like to be able to search for games by color and size.

    // return game by color
    @SchemaMapping
    public Game game(Color color) {
        Optional<Game> returnVal = gameRepository.findById(color.getId());
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    // return game by size
    @SchemaMapping
    public Game game(Size size) {
        Optional<Game> returnVal = gameRepository.findById(size.getId());
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }


}