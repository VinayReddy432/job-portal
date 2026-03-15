package com.jobportal.job_portal.controller;

import com.jobportal.job_portal.entity.Application;
import com.jobportal.job_portal.entity.ApplicationStatus;
import com.jobportal.job_portal.service.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/apply/{jobId}")
    public ResponseEntity<Application>apply(@PathVariable Long jobId,
                                            Authentication authentication){
        String email=authentication.getName();
        Application application=applicationService.apply(jobId,email);
        return ResponseEntity.status(HttpStatus.CREATED).body(application);

    }
    @GetMapping("/my")
    public ResponseEntity<List<Application>> getMyApplications(
            Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(applicationService.getMyApplications(email));
    }

@GetMapping("/job/{jobId}")
    public ResponseEntity<List<Application>>getJobApplications(
            @PathVariable Long jobId)
{
   return ResponseEntity.ok(applicationService.getJobApplications(jobId));
}

    @PutMapping("/{id}/status")
    public ResponseEntity<Application> updateStatus(
            @PathVariable Long id,
            @RequestParam ApplicationStatus status) {
        return ResponseEntity.ok(applicationService.updateStatus(id, status));
    }
}
