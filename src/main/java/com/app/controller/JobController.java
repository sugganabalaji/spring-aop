package com.app.controller;

import com.app.model.JobPost;
import com.app.service.JobJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class JobController {

    @Autowired
    private JobJpaService service;

    @GetMapping(path = "/jobPosts", produces = {"application/json", "application/xml"})
    public List<JobPost> getAllJobs() {
        return service.getAllJobs();
    }

    @GetMapping("/jobPost/{postId}")
    public JobPost getJob(@PathVariable int postId) {
        return service.getJob(postId);
    }

    @PostMapping(path = "/jobPost", consumes = "application/json")
    public JobPost saveJob(@RequestBody JobPost jobPost) {
        service.saveJob(jobPost);
        return service.getJob(jobPost.getPostId());
    }

    @PutMapping(path = "/jobPost", consumes = "application/json")
    public JobPost updateJob(@RequestBody JobPost jobPost) {
        service.updateJob(jobPost);
        return service.getJob(jobPost.getPostId());
    }

    @DeleteMapping("/jobPost/{postId}")
    public String deleteJob(@PathVariable int postId) {
        return service.deleteJob(postId);
    }

    @GetMapping("/loadData")
    public String loadData() {
        return service.loadData();
    }

    @GetMapping("/jobPosts/search/{keyword}")
    public List<JobPost> searchJobs(@PathVariable String keyword) {
        return service.search(keyword);
    }
}
