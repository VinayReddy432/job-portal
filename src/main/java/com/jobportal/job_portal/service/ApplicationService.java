package com.jobportal.job_portal.service;

import com.jobportal.job_portal.entity.Application;
import com.jobportal.job_portal.entity.ApplicationStatus;
import com.jobportal.job_portal.entity.Job;
import com.jobportal.job_portal.entity.User;
import com.jobportal.job_portal.repository.ApplicationRepository;
import com.jobportal.job_portal.repository.JobRepository;
import com.jobportal.job_portal.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    public ApplicationService(ApplicationRepository applicationRepository,
                              JobRepository jobRepository,
                              UserRepository userRepository) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    public Application apply(Long jobId,String email){
        User candidate=userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("user not found"));
        Job job=jobRepository.findById(jobId)
                .orElseThrow(()->new RuntimeException("Job not found"));
        Application application=new Application();
        application.setCandidate(candidate);
        application.setJob(job);
        application.setStatus(ApplicationStatus.PENDING);
        return applicationRepository.save(application);

    }
    public List<Application> getMyApplications(String email) {
        return applicationRepository.findByCandidate_Email(email);
    }

    public List<Application> getJobApplications(Long jobId) {
        return applicationRepository.findByJob_Id(jobId);
    }

    public Application updateStatus(Long applicationId, ApplicationStatus status) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        application.setStatus(status);
        return applicationRepository.save(application);
    }



}
