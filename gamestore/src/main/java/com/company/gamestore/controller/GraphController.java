package com.company.gamestore.controller;


import com.company.gamestore.model.Console;
import com.company.gamestore.model.Game;
import com.company.gamestore.model.Tshirt;
import com.company.gamestore.repository.*;
import com.company.gamestore.repository.ConsoleRepository;
import com.company.gamestore.repository.GameRepository;
import com.company.gamestore.repository.InvoiceRepository;
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

    // As a user, I would like to be able to search for tshirts by color and size.



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
    FeeRepository feeRepository;

    @Autowired
    TaxRepository taxRepository;



    // General CRUD for Game, Console, and Tshirt

    // Game
    @QueryMapping
    public Game findGameById(@Argument int id) {
        Optional<Game> returnVal = this.gameRepository.findById(id);
        return returnVal.isPresent() ? (Game)returnVal.get() : null;
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

    // As a user, I would like to be able to search for games by color and size.

    // return tshirt by color
    @SchemaMapping
    public List<Tshirt> findTshirtByColor(String color) {
        Optional<List<Tshirt>> returnVal = tshirtRepository.findByColor(color);
  
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    // return tshirt by size
    @SchemaMapping
    public List<Tshirt> findTshirtBySize(String size) {
        Optional<List<Tshirt>> returnVal = tshirtRepository.findBySize(size);

        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }


}