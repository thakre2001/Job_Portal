package com.dagproject.controller;

import java.util.List;
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
import com.dagproject.entity.Job;
import com.dagproject.service.JobService;

@RestController
@CrossOrigin(origins = "http://localhost:8089")
public class JobController {
	
	@Autowired
	private JobService jobService;
	
	@PostMapping(value = "/api/auth/addJob")
	protected ResponseEntity<?> addJob(@RequestBody Job job) {
		Job job2 = jobService.addJob(job);
		if(job2 != null) {
			return ResponseEntity.ok("Job added successfully");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
	}
	
	@GetMapping(value = "/api/jobs")
	protected List<Job> getAllJobs() {
		return jobService.getAllJobs();
	}
	
	@GetMapping(value = "/api/job/{id}")
	protected ResponseEntity<?> findJobById(@PathVariable Long id) {
		Optional<Job> jobById = jobService.findJobById(id);
		if(jobById.isPresent()) {
			return ResponseEntity.ok(jobById.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not present");
	}
	
	@PutMapping(value = "/api/update/job")
	protected ResponseEntity<?> updateJob(@RequestBody Job job) {
		boolean updatedJob = jobService.updateJob(job);
		if(updatedJob) {
			return ResponseEntity.ok("Job Updated");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found");
		}
	}
	
	@DeleteMapping(value = "/api/removeJob/{id}")
	protected ResponseEntity<?> removeJob(@PathVariable(name = "id") Long id) {
		boolean removedJob = jobService.removeJob(id);
		if(removedJob) {
			return ResponseEntity.ok("Job removed");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found");
	}

}

