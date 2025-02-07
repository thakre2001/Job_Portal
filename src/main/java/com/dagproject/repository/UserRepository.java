package com.dagproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dagproject.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findUserByEmail(String email);

	

}
