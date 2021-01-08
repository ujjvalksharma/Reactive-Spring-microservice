package com.example.postservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.postservice.model.Post;

public interface PostRepository extends MongoRepository<Post, Integer>{

	@Query("{ 'userId' : ?0 }")
	List<Post> findPostByUserId(int userId);
}
