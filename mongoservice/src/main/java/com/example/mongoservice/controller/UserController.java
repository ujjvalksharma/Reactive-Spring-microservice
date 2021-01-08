package com.example.mongoservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.mongoservice.model.User;
import com.example.mongoservice.repository.UserRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.example.mongoservice.model.Post;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
@RequestMapping("/User")
@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
//	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;

	@PostMapping("/add")
	public User saveUser(@RequestBody User user) {
		return userRepository.save(user);
		
	}
 
	@GetMapping(value = "/findAll")
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/findAllBooks/{id}")
	public Optional<User> getUser(@PathVariable(name = "id") int id) {
		return userRepository.findById(id);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
		return "book deleted with id : " + id;
	}
	
	
	/*@GetMapping("/test/")
	public String verifyUserService() { // service is checking get method for another service using rest template
		
		return "User service is working. "+restTemplate.getForObject("http://localhost:8082/Post/test", String.class);
	}
	*/

	/*	@PostMapping("/addPost/{userId}") // service is checking post method for another service using rest template
	public Post addPost(@PathVariable(name = "userId")int userId,@RequestBody Post post) {
		
		Post postTemp= restTemplate.postForObject("http://localhost:8082/Post/add/"+userId,post , Post.class);
		System.out.println(postTemp);
		return postTemp;
	}
	
	*/
	
	@GetMapping("/test")
	public String verifyUserService() { // service is checking get method for another service using web client(reactive springboot)
		
		return webClientBuilder.build()
		.get()
		.uri("http://user-post-service/Post/test")
        .retrieve()
		.bodyToMono(String.class)
		.block();  
	}
	
	@PostMapping("/addPost/{userId}") // service is checking post method for another service using web client(reactive springboot)
	public Post addPost(@PathVariable(name = "userId")int userId,@RequestBody Post post) {
	
		return webClientBuilder
		.build()
		.post()
		.uri("http://user-post-service/Post/add/"+userId)
		.bodyValue(post)
		.retrieve()
		.bodyToMono(Post.class)
		.block();
		
	}
	
	@HystrixCommand(defaultFallback = "errorMethod",
		
	commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumneThreshold", value = "5"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
	})
	@GetMapping("/findPost/{userId}") // service is checking post method for another service using web client(reactive springboot)
	public Post getPost(@PathVariable(name = "userId")int userId) {
	
		return webClientBuilder
				.build()
				.get()
				.uri("http://user-post-service/Post/find/"+userId)
				.retrieve()
				.bodyToMono(Post.class)
				.block();
				
		
	}
	
	public String errorMethod() {
		return "server is busy!! try again!!";
	}
}
