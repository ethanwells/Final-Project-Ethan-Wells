package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.repository.GameRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {

    @MockBean
    private GameRepository gameRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        gameRepository.deleteAll();
    }

    // Create
    @Test
    public void testCreateGame() throws Exception {
        // ARRANGE
        Game game = new Game();
        game.setTitle("title1");
        game.setEsrbRating("rating1");
        game.setDescription("description1");
        game.setPrice(new BigDecimal("13.57"));
        game.setStudio("studio1");
        game.setQuantity(3);

        Mockito.when(gameRepository.save(Mockito.any(Game.class)))
                .thenReturn(game);

        ObjectMapper objectMapper = new ObjectMapper();

        // ACT
        mockMvc.perform(
                        post("/game")  // Perform the POST request
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(game)))  // Request payload as JSON
                .andDo(print())  // Print results to game
                .andExpect(status().isCreated());  // 201 code
    }


    // Read By ID
    @Test
    public void testGetGameById() throws Exception {
        // ACT
        mockMvc.perform(
                        get("/game/1")  // Perform the GET request
                                .contentType(MediaType.APPLICATION_JSON))  // Tell the server it's in JSON format
                .andDo(print())  // Print results to game
                .andExpect(status().isOk());
    }

    // Read by studio
    @Test
    public void testGetGameByStudio() throws Exception {
        // ACT
        mockMvc.perform(
                        get("/game/by-studio")  // Perform the GET request
                                .param("studio", "studio1")  // Add query parameter
                                .contentType(MediaType.APPLICATION_JSON))  // Tell the server it's in JSON format
                .andDo(print())  // Print results to console
                .andExpect(status().isOk());
    }

    // Read by rating
    @Test
    public void testGetGameByRating() throws Exception {
        // ACT
        mockMvc.perform(
                        get("/game/by-rating")  // Perform the GET request
                                .param("esrbRating", "esrbRating1")  // Add query parameter
                                .contentType(MediaType.APPLICATION_JSON))  // Tell the server it's in JSON format
                .andDo(print())  // Print results to console
                .andExpect(status().isOk());
    }

    // Read by title
    @Test
    public void testGetGameByTitle() throws Exception {
        // ACT
        mockMvc.perform(
                        get("/game/by-title")  // Perform the GET request
                                .param("title", "title1")  // Add query parameter
                                .contentType(MediaType.APPLICATION_JSON))  // Tell the server it's in JSON format
                .andDo(print())  // Print results to console
                .andExpect(status().isOk());
    }








    // Read All
    @Test
    public void testGetAllGames() throws Exception {
        // ACT
        mockMvc.perform(
                        get("/game/games")  // Perform the GET request
                                .contentType(MediaType.APPLICATION_JSON))  // Tell the server it's in JSON format
                .andDo(print())  // Print results to game
                .andExpect(status().isOk());
    }

    // Update
    @Test
    public void updateGameTest() throws Exception {
        //ARRANGE
        Game game = new Game();
        game.setTitle("title1");
        game.setEsrbRating("rating1");
        game.setDescription("description1");
        game.setPrice(new BigDecimal("13.57"));
        game.setStudio("studio1");
        game.setQuantity(3);

        Mockito.when(gameRepository.save(Mockito.any(Game.class)))
                .thenReturn(game);

        ObjectMapper objectMapper = new ObjectMapper();

        // ACT
        mockMvc.perform(
                        put("/game", game.getGameId())  // Perform the PUT request
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(game)))
                .andDo(print())  // Print results to game
                .andExpect(status().isNoContent());  // ASSERT (status code is 204)
    }

    // Delete by ID
    @Test
    public void deleteGameTest() throws Exception {
        // ACT
        mockMvc.perform(
                        delete("/game/1"))  // Perform the delete request
                .andDo(print())  // Print results to game
                .andExpect(status().isNoContent());  // ASSERT (status code is 204)
    }
}
