package com.dagproject.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false , unique = false)
	private String name;
	
	@Column(nullable = false , unique = true)
	private String email;
	
	@Column(nullable = false , unique = true)
	private long mobile;
	
	@Column(nullable = false , unique = false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;

}
