package com.example.demo.config;

import com.example.demo.handlers.CompanyEventHandler;
import com.example.demo.handlers.PersonHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;

@Configuration
public class FunctionalRouteConfig {

    @Bean
    public RouterFunction<ServerResponse> buildFunctionalRoutes(CompanyEventHandler corpEvents,
                                                                PersonHandler people){
        return RouterFunctions.route()
                .path("events", e -> e.nest(accept(MediaType.APPLICATION_JSON),
                        p -> p.GET("{days:\\d}", corpEvents::getEventsByDays)
                                .GET("{locale}", corpEvents::getEventsByLocation)
                                .GET("",corpEvents::getCurrentEvents)
                                .POST("",corpEvents::createNewEvent)
                                .POST("group",corpEvents::createEvents)))
                .path("person", n -> n.nest( accept(MediaType.APPLICATION_JSON).and(contentType(MediaType.APPLICATION_JSON)),
                        p->p.GET("",people::getPeoples)
                                .POST("",people::addPerson)
                                .POST("email",people::getPersonByEmail)
                                .POST("phone",people::getPersonByPhone)))
                .build();
    }
}
