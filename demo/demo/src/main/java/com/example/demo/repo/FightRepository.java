package com.example.demo.repo;

import com.example.demo.model.Fight;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FightRepository extends MongoRepository<Fight, Integer> {

    Fight findById(int id);
    Fight findByTitle(String title);
}
