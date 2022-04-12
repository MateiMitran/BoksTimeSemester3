package com.example.demo.repo;

import com.example.demo.model.Fighter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FighterRepository extends MongoRepository<Fighter, Integer> {

    Fighter findById(int id);
    Fighter findByName(String name);
}
