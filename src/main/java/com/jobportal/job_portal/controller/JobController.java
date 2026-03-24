package com.jobportal.job_portal.controller;

import com.jobportal.job_portal.entity.Job;
import com.jobportal.job_portal.exception.ResourceNotFoundException;
import com.jobportal.job_portal.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job job,
                                         Authentication authentication){
        String email=authentication.getName();
        Job savedJob = jobService.createJob(job, email);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedJob);


    }

    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Job job = jobService.getJobById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));
        return ResponseEntity.ok(job);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id,
                                         @RequestBody Job job,
                                         Authentication authentication) {
        String email = authentication.getName();
        Job updatedJob = jobService.updateJob(id, job, email);
        return ResponseEntity.ok(updatedJob);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Job>>searchJobs(
            @RequestParam(required = false)String keyword,
            @RequestParam(required = false)String location) {
        return ResponseEntity.ok(jobService.searchJobs(keyword, location));
    }


}
