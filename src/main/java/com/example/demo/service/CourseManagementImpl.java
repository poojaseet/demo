package com.example.demo.service;

import com.example.demo.domain.Course;
import com.example.demo.persistence.CourseDataService;
import com.example.demo.persistence.FauxDataSvc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseManagementImpl implements CourseManagementSvc {
    private final FauxDataSvc fauxCourseDataSvc;

    private final CourseDataService crsData;

    @Override
    public Flux<Course> getCurrentCourses() {

        return this.crsData.findAll();
    }

    @Override
    public Mono<Course> findCourseByTitle(String title) {
        return crsData.findCourseByTitle(title);
    }

    @Override
    public void save(Course nueCourse) {
        this.crsData.save(nueCourse);
    }

    @Override
    public void saveAll(List<Course> newCourses) {
        this.crsData.saveAll(newCourses);
    }

    @Override
    public Flux<Course> findCoursesByTitleContaining(String phrase) {
        return this.crsData.findCourseByTitleContianing(phrase);
    }
}
