package com.company.gamestore.controller;

import com.company.gamestore.model.Tshirt;
import com.company.gamestore.repository.TshirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
public class TshirtController {


        @Autowired
        TshirtRepository tshirtRepository;

        //    Find tshirt by id.
        @GetMapping("/tshirt/{id}")
        @ResponseStatus(HttpStatus.OK)
        public Tshirt getTshirtById(@PathVariable int id) {

            Optional<Tshirt> returnVal = tshirtRepository.findById(id);
            if (returnVal.isPresent()) {
                return returnVal.get();
            } else {
                return null;
            }
        }

        //    Find tshirt by color
        @GetMapping("/tshirt/color/{color}")
        @ResponseStatus(HttpStatus.OK)
        public List<Tshirt> getTshirtByColor(@PathVariable String color) {
            Optional<List<Tshirt>> foundShirts = tshirtRepository.findByColor(color);
            if (foundShirts.isPresent()) {
                return foundShirts.get();
            } else {
                return null;
            }
        }

        //    Find tshirt by size
        @GetMapping("/tshirt/size/{size}")
        @ResponseStatus(HttpStatus.OK)
        public List<Tshirt> getTshirtBySize(@PathVariable String size) {
            Optional<List<Tshirt>> foundShirts = tshirtRepository.findByColor(size);
            if (foundShirts.isPresent()) {
                return foundShirts.get();
            } else {
                return null;
            }
        }

        //    Find all shirts
        @GetMapping("/tshirts")
        @ResponseStatus(HttpStatus.OK)
        public List<Tshirt> getTshirts() {

            return tshirtRepository.findAll();
        }

        //    Create a new tshirt record.
        @PostMapping("/tshirt")
        @ResponseStatus(HttpStatus.CREATED)
        public Tshirt createTshirt(@RequestBody Tshirt tshirt) {
            return tshirtRepository.save(tshirt);
        }


        //    Update an existing tshirt record
        @PutMapping("/tshirt")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void updateTshirt(@RequestBody Tshirt tshirt) {
            tshirtRepository.save(tshirt);
        }


        //    Delete an existing author record.
        @DeleteMapping("/tshirt/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deleteTshirt(@PathVariable int id) {
            tshirtRepository.deleteById(id);
        }

}
