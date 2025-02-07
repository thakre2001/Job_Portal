package com.dagproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dagproject.entity.Job;
import com.dagproject.repository.JobRepository;

@Service
public class JobService {
	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public Job addJob(Job job) {
		return jobRepository.save(job);
	}
	
	public List<Job> getAllJobs() {
		return jobRepository.findAll();
		
	}

	public Optional<Job> findJobById(long id) {
		return jobRepository.findById(id);
	}

	public boolean updateJob(Job job) {
		if (jobRepository.existsById(job.getId())) {
			jobRepository.save(job);
			return true;
		}
		return false;
	}

	public boolean removeJob(long id) {
		if (jobRepository.existsById(id)) {
			jobRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
