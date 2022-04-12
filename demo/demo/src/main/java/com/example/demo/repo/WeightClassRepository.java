package com.example.demo.repo;

import com.example.demo.model.Weight_Class;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeightClassRepository extends MongoRepository<Weight_Class, Integer> {

    Weight_Class findById(int id);
    Weight_Class findByName(String name);
}
