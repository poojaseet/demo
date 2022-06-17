package com.example.demo.persistence;

import com.example.demo.domain.CompanyEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CorpEventsDataSvc {
    Flux<CompanyEvent> getCurrentEvents();
    Flux<CompanyEvent> getEventsByLocation(String locale);
    Mono<CompanyEvent> addEvent(CompanyEvent nueEvent);
    void addEvents(List<CompanyEvent> nueEvents);
    Flux<CompanyEvent> getEventsByDuration(int duration);
}
