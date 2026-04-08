package com.app.repository;

import com.app.model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobJpaRepository extends JpaRepository<JobPost, Integer> {

    // JPQL query to search for job posts based on profile and description
    List<JobPost> findByPostProfileContainingIgnoreCaseOrPostDescContainingIgnoreCase(String postProfile, String postDesc);
}
