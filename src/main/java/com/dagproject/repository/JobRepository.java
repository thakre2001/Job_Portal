package com.dagproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dagproject.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long>{

}
