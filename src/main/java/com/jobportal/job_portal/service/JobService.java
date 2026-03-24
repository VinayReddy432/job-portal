package com.jobportal.job_portal.service;


import com.jobportal.job_portal.entity.Job;
import com.jobportal.job_portal.entity.User;
import com.jobportal.job_portal.repository.JobRepository;
import com.jobportal.job_portal.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public JobService(JobRepository jobRepository, UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }
    public Job createJob(Job job,String email){
        User user=userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("User not found"));
        job.setPostedBy(user);
        return  jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Optional<Job> getJobById(Long id) {
        return jobRepository.findById(id);
    }

    public Job updateJob(Long id, Job updatedJob, String email) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        job.setTitle(updatedJob.getTitle());
        job.setCompany(updatedJob.getCompany());
        job.setLocation(updatedJob.getLocation());
        job.setDescription(updatedJob.getDescription());
        job.setSalary(updatedJob.getSalary());
        job.setJobType(updatedJob.getJobType());
        return jobRepository.save(job);
    }


    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
    public List<Job> searchJobs(String keyword,String location){
        return jobRepository.searchJobs(keyword,location);
    }

}
