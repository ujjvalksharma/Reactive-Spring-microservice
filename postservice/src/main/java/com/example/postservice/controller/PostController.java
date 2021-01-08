package com.example.postservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.postservice.model.Post;
import com.example.postservice.repository.PostRepository;

@RestController
@RequestMapping("/Post")
public class PostController {
	
	
	@Autowired
	PostRepository postRepository;
	
	@PostMapping("/add/{userId}")
	public Post addPost(@PathVariable(name = "userId")int userId,@RequestBody Post post) {
		post.setUserId(userId);
	return	postRepository.save(post);
		
	}
	
	@GetMapping("/find/{userId}")
	public List<Post> getPost(@PathVariable(name = "userId") int userId) {
		return postRepository.findPostByUserId(userId);
	}
	
	
	@GetMapping("/test")
	public String verifyService() {
		return "post service is working";
	}

}
