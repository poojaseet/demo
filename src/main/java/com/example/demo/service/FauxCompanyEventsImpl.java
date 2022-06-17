package com.example.demo.service;

import com.example.demo.domain.CompanyEvent;
import com.example.demo.persistence.CorpEventsDataSvc;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
public class FauxCompanyEventsImpl implements CorpEventsDataSvc {

    private List<CompanyEvent> events;

    public FauxCompanyEventsImpl() {
        this.events = new ArrayList<>();
    }

    @Override
    public Flux<CompanyEvent> getCurrentEvents() {
        return Flux.fromIterable(this.events);
    }

    @Override
    public Flux<CompanyEvent> getEventsByLocation(String locale) {
        return Flux.fromStream(this.events.stream()
                .filter(e -> e.getLocation().equalsIgnoreCase(locale)));
    }

    @Override
    public Mono<CompanyEvent> addEvent(CompanyEvent nueEvent) {
        this.events.add(nueEvent);
        return Mono.just(nueEvent);
    }

    @Override
    public void addEvents(List<CompanyEvent> nueEvents) {
        this.events.addAll(nueEvents);
    }

    @Override
    public Flux<CompanyEvent> getEventsByDuration(int duration) {
        return Flux.fromStream(this.events.stream().filter(e->e.getDuration()==duration));
    }
}
