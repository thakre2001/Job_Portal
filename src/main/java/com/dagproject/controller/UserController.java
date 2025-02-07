package com.dagproject.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dagproject.dto.LoginRequest;
import com.dagproject.entity.User;
import com.dagproject.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:8089")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/api/auth/register")
	protected ResponseEntity<?> addUser(@RequestBody User user) {
		User addedUser=userService.addUser(user);
		if(addedUser != null) {
			return ResponseEntity.ok(addedUser);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Not Added");
	}
	
	@PostMapping(value = "/api/login")
	protected ResponseEntity<?> login(@RequestBody LoginRequest loginRequest ) {
		String email=loginRequest.getEmail();
		String password=loginRequest.getPassword();
		User user = userService.login(email,password);
		if(user != null) {
			return ResponseEntity.ok(user);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	}
	
	protected List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping(value = "/api/user/{id}")
	protected ResponseEntity<?> findUserById(@PathVariable long id) {
		Optional<User> user= userService.findUserById(id);
		if(user.isPresent()) {
			return ResponseEntity.ok(user.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not present");
	}
	
	@PutMapping(value = "/api/update")
	protected ResponseEntity<?> updateUser(@RequestBody User user) {
		boolean updatedUser = userService.updateUser(user);
		if(updatedUser) {
			return ResponseEntity.ok("User Updated");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
	}
	
	@DeleteMapping(value = "/api/remove/{id}")
	protected ResponseEntity<?> removeUser(@PathVariable(name = "id") long id) {
		boolean removedUser = userService.removeUser(id);
		if(removedUser) {
			return ResponseEntity.ok("User removed");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	}

}
