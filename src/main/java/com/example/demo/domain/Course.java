package com.example.demo.domain;

import com.example.demo.marshalling.CourseDeserializer;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor
@Builder
@JsonDeserialize(using = CourseDeserializer.class)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Course {
    private UUID id;
    private String catalogTitle;
    private String description;
    @JsonManagedReference()
    private Author author;

}
