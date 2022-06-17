package com.example.demo.service;

import com.example.demo.domain.Course;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

public interface CourseManagementSvc {
    Flux<Course> getCurrentCourses() ;
    Mono<Course> findCourseByTitle(String title);

    void save(Course nueCourse);

    void saveAll(List<Course> newCourses);

    Flux<Course> findCoursesByTitleContaining(String phrase);

}
