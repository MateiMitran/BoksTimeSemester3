package com.example.demo.repo;

import com.example.demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository<User, Long> {


    User findByUsername(String username);

    User findByEmail(String email);

    User findById(long id);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
