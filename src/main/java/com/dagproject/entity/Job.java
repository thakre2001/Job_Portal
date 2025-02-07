package com.dagproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "jobs")
@Data
public class Job {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String companyName;
	private String location;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	private String salary;
	
	@Enumerated(EnumType.STRING)
	private ExperienceLevel experienceLevel;
	
	@ManyToOne
	@JoinColumn(name = "posted_by",nullable = false)
	private User postedBy;
	

}
