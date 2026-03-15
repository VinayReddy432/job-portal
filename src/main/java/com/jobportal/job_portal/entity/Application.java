package com.jobportal.job_portal.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="candidate_id")
    private  User candidate;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    @CreationTimestamp
    private LocalDateTime appliedAt;

    public Application() {}

    public Long getId() { return id; }
    public User getCandidate() { return candidate; }
    public Job getJob() { return job; }
    public ApplicationStatus getStatus() { return applicationStatus; }
    public LocalDateTime getAppliedAt() { return appliedAt; }

    public void setId(Long id) { this.id = id; }
    public void setCandidate(User candidate) { this.candidate = candidate; }
    public void setJob(Job job) { this.job = job; }
    public void setStatus(ApplicationStatus status) { this.applicationStatus = status; }


}
