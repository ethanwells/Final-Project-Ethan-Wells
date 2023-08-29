package com.company.gamestore.repository;

import com.company.gamestore.model.Tshirt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TshirtRepository extends JpaRepository<Tshirt, Integer> {

    Optional<List<Tshirt>> findBySize(String size);
    Optional<List<Tshirt>> findByColor(String color);


}
