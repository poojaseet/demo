package com.example.demo.persistence;

import com.example.demo.domain.Course;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CourseDataService {
    Flux<Course> findAll();

    void save(Course nueCourse);

    void saveAll(List<Course> nueCourses);

    Flux<Course> findCourseByTitleContianing(String phrase);
    Mono<Course> findCourseByTitle(String title);
}
