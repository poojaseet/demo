package com.example.demo.domain;

import lombok.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CompanyEvent {
    private UUID id;
    private String eventTitle;
    private String location;
    private int duration;
    private List<Person> attendees;
}
