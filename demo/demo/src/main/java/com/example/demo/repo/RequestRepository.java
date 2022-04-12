package com.example.demo.repo;

import com.example.demo.model.Request;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends MongoRepository<Request, Integer> {

    Request findById(int id);
}
