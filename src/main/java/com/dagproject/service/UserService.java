package com.dagproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dagproject.entity.User;
import com.dagproject.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public User addUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public User login(String email, String password) {
		 Optional<User> optionalUser = Optional.ofNullable(userRepository.findUserByEmail(email));
		if(optionalUser.isPresent()) {
			User user=optionalUser.get();
			if(passwordEncoder.matches(password,user.getPassword())) {
				return user;
			}
		}
		return null;
	}

	public Optional<User> findUserById(long id) {
		return userRepository.findById(id);
	}
	

	public boolean updateUser(User user) {
		if(userRepository.existsById(user.getId())) {
			userRepository.save(user);
			return true;
		}
		return false;
	}

	public  List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public boolean removeUser(long id) {
		if(userRepository.existsById(id)){
			userRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
