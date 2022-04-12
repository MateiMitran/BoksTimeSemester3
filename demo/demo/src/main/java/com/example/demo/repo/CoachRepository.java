package com.example.demo.repo;

import com.example.demo.model.Coach;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepository extends MongoRepository<Coach, Integer> {

    Coach findById(int id);
    Coach findByName(String name);
}
