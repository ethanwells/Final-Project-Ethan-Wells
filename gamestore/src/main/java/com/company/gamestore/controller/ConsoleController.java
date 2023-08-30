package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ConsoleController {

    @Autowired
    ConsoleRepository consoleRepository;

    @GetMapping("/console/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Console getConsoleById(@PathVariable int id) {

        Optional<Console> returnVal = consoleRepository.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }


    @GetMapping("/console/manufacturer")
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getConsoleByManufacturer(@RequestParam("manufacturer") String manufacturer) {

        Optional<List<Console>> returnVal = consoleRepository.findConsoleByManufacturer(manufacturer);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    //    Find all consoles
    @GetMapping("console/consoles")
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getConsoles() {

        return consoleRepository.findAll();
    }

    //    Create a new console.
    @PostMapping("/console")
    @ResponseStatus(HttpStatus.CREATED)
    public Console addConsole(@RequestBody Console console) {
        return consoleRepository.save(console);
    }


    //    Update an existing console
    @PutMapping("/console")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsole(@RequestBody Console console) {
        consoleRepository.save(console);
    }


    //    Delete an existing console.
    @DeleteMapping("/console/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable int id) {
        consoleRepository.deleteById(id);
    }

}
