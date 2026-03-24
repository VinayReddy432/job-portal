package com.jobportal.job_portal.repository;

import com.jobportal.job_portal.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobRepository  extends JpaRepository<Job,Long> {
    List<Job> findByPostedBy_Email(String email);

    @Query("SELECT j FROM Job j WHERE " +
            "(:keyword IS NULL OR LOWER(j.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(j.description) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
            "(:location IS NULL OR LOWER(j.location) LIKE LOWER(CONCAT('%', :location, '%')))")
    List<Job> searchJobs(@Param("keyword") String keyword,
                         @Param("location") String location);

}
