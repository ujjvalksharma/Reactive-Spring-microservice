package com.example.mongoservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.mongoservice.model.User;

public interface UserRepository extends MongoRepository<User, Integer>{

}
