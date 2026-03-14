package com.jobportal.job_portal.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String company;
    private String location;
    private String description;
    private String salary;

    @Enumerated(EnumType.STRING)
    private JobType jobType;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "posted_by")
    private User postedBy;

    public Job() {}

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getCompany() { return company; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }
    public String getSalary() { return salary; }
    public JobType getJobType() { return jobType; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public User getPostedBy() { return postedBy; }

    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setCompany(String company) { this.company = company; }
    public void setLocation(String location) { this.location = location; }
    public void setDescription(String description) { this.description = description; }
    public void setSalary(String salary) { this.salary = salary; }
    public void setJobType(JobType jobType) { this.jobType = jobType; }
    public void setPostedBy(User postedBy) { this.postedBy = postedBy; }
}