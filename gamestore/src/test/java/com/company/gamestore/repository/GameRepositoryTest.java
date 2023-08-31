package com.company.gamestore.repository;

import com.company.gamestore.model.Game;
import com.company.gamestore.model.Game;
import com.company.gamestore.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GameRepositoryTest {

    @Autowired
    GameRepository gameRepository;

    @BeforeEach
    public void setUp() throws Exception{
        gameRepository.deleteAll();
    }

    //ADD
    @Test
    public void shouldAddGame(){

        //Arrange
        Game game1 = new Game();
        game1.setTitle("title1");
        game1.setEsrbRating("rating1");
        game1.setDescription("description1");
        game1.setPrice(new BigDecimal("13.57"));
        game1.setStudio("studio1");
        game1.setQuantity(3);

        gameRepository.save(game1);

        //Act
        Optional<Game> game2 = gameRepository.findById(game1.getGameId());

        //Assert
        assertEquals(game2.get(), game1);
    }

    // Read By ID
    @Test
    public void shouldGetGameById() {
        // Arrange
        Game game1 = new Game();
        game1.setTitle("title1");
        game1.setEsrbRating("rating1");
        game1.setDescription("description1");
        game1.setPrice(new BigDecimal("13.57"));
        game1.setStudio("studio1");
        game1.setQuantity(3);

        // Act
        game1 = gameRepository.save(game1);
        Optional<Game> foundGame = gameRepository.findById(game1.getGameId());

        // Assert
        assertEquals(foundGame.get(), game1);
    }

    // Read By Studio
    @Test
    public void shouldGetGameByStudio() {
        // Arrange
        Game game1 = new Game();
        game1.setTitle("title1");
        game1.setEsrbRating("rating1");
        game1.setDescription("description1");
        game1.setPrice(new BigDecimal("13.57"));
        game1.setStudio("studio1");
        game1.setQuantity(3);

        // Act
        game1 = gameRepository.save(game1);
        Optional<List<Game>> foundGame = gameRepository.findGameByStudio(game1.getStudio());

        // Assert
        assertTrue(foundGame.get().contains(game1));
    }

    // Read By Rating
    @Test
    public void shouldGetGameByRating() {
        // Arrange
        Game game1 = new Game();
        game1.setTitle("title1");
        game1.setEsrbRating("rating1");
        game1.setDescription("description1");
        game1.setPrice(new BigDecimal("13.57"));
        game1.setStudio("studio1");
        game1.setQuantity(3);

        // Act
        game1 = gameRepository.save(game1);
        Optional<List<Game>> foundGame = gameRepository.findGameByEsrbRating(game1.getEsrbRating());

        // Assert
        assertTrue(foundGame.get().contains(game1));
    }

    // Read By Title
    @Test
    public void shouldGetGameByTitle() {
        // Arrange
        Game game1 = new Game();
        game1.setTitle("title1");
        game1.setEsrbRating("rating1");
        game1.setDescription("description1");
        game1.setPrice(new BigDecimal("13.57"));
        game1.setStudio("studio1");
        game1.setQuantity(3);

        // Act
        game1 = gameRepository.save(game1);
        Optional<List<Game>> foundGame = gameRepository.findGameByTitle(game1.getTitle());

        // Assert
        assertTrue(foundGame.get().contains(game1));
    }

    // Read All
    @Test
    public void shouldGetAllGames() {
        // Arrange
        Game game1 = new Game();
        game1.setTitle("title1");
        game1.setEsrbRating("rating1");
        game1.setDescription("description1");
        game1.setPrice(new BigDecimal("13.57"));
        game1.setStudio("studio1");
        game1.setQuantity(3);

        Game game2 = new Game();
        game1.setTitle("title1");
        game1.setEsrbRating("rating1");
        game1.setDescription("description1");
        game1.setPrice(new BigDecimal("13.57"));
        game1.setStudio("studio1");
        game1.setQuantity(3);

        // Act
        game1 = gameRepository.save(game1);
        game2 = gameRepository.save(game2);
        List<Game> foundGames = gameRepository.findAll();

        // Assert
        assertEquals(2, foundGames.size());
        assertTrue(foundGames.contains(game1));
        assertTrue(foundGames.contains(game2));
    }

    //Update
    @Test
    public void shouldUpdateGame(){

        //Arrange
        Game game1 = new Game();
        game1.setTitle("title1");
        game1.setEsrbRating("rating1");
        game1.setDescription("description1");
        game1.setPrice(new BigDecimal("13.57"));
        game1.setStudio("studio1");
        game1.setQuantity(3);

        gameRepository.save(game1);

        //Act
        Optional<Game> game2 = gameRepository.findById(game1.getGameId());

        //Assert
        assertEquals(game2.get(),game1);

    }

    //Delete
    @Test
    public void shouldDeleteGameByProductType(){
        Game game = new Game();
        game.setTitle("title1");
        game.setEsrbRating("rating1");
        game.setDescription("description1");
        game.setPrice(new BigDecimal("13.57"));
        game.setStudio("studio1");
        game.setQuantity(3);

        game = gameRepository.save(game);

        // Act
        Optional<Game> foundGame = gameRepository.findById(game.getGameId());

        // Assert
        assertTrue(foundGame.isPresent());
        assertEquals(foundGame.get(), game);

        // Act
        gameRepository.deleteById(game.getGameId());

        foundGame = gameRepository.findById(game.getGameId());

        // Assert
        assertFalse(foundGame.isPresent());

    }

}
