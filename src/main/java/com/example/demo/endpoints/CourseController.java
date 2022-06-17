package com.example.demo.endpoints;

import com.example.demo.domain.Course;
import com.example.demo.service.CourseManagementSvc;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path= "course")
public class CourseController {
    private final CourseManagementSvc crsCatalog;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Course> getCurrentCourses(){
        return this.crsCatalog.getCurrentCourses();
    }


    @GetMapping(path="nd-stream",produces = MediaType.APPLICATION_NDJSON_VALUE)
    @ExceptionHandler
    public ResponseEntity<Flux<Course>> getCurrentCoursesNDStream(IOException ioException) throws IOException {
    // return   ResponseEntity.internalServerError().build();
        String test = "test";
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).headers(HttpHeaders.EMPTY).body(Flux.fromIterable(Arrays.asList(new Course(UUID.randomUUID(),"cata","desc",null))));
 //  throw new IOException("test ");
       //     return this.crsCatalog.getCurrentCourses();


    }

    @GetMapping(path="txt-stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent> getCurrentCoursesStream(){
        return this.crsCatalog.getCurrentCourses()
                .map(c -> ServerSentEvent.builder()
                        .id(UUID.randomUUID().toString())
                        .event(String.valueOf(Instant.now().getEpochSecond()))
                        .data(c)
                        .build());
    }

    @GetMapping(path = "{crsTitle:[\\w-]{3,30}}")
    public Mono<Course> findCourseByTitle(@PathVariable("crsTitle") String title){
        return this.crsCatalog.findCourseByTitle(title);
    }
}
