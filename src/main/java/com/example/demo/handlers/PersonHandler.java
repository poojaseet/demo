package com.example.demo.handlers;

import com.example.demo.domain.Person;
import com.example.demo.domain.Search;
import com.example.demo.persistence.PeopleDataSvc;
import com.example.demo.validator.PersonValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PersonHandler {
    private final PeopleDataSvc dataSvc;

    public Mono<ServerResponse> getPeoples(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(this.dataSvc.findAll(), Person.class);
    }

    public Mono<ServerResponse> addPerson(ServerRequest request){
        Validator validator = new PersonValidator();
      Mono<String> responseBody =   request.bodyToMono(Person.class).map(body -> {
            Errors errors = new BeanPropertyBindingResult(body, Person.class.getName());
            validator.validate(body,errors);
            if(errors == null ){
                return "All is well";
            }else if(errors.getAllErrors().isEmpty()){
                return "All is weell in empty";
            }else{
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,errors.getAllErrors().toString());
            }
        });
 return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(responseBody, String.class);


     /*   Mono<Person> p = request.bodyToMono(Person.class);
        this.dataSvc.addPerson(p.block());
        return ServerResponse.accepted().contentType(MediaType.APPLICATION_JSON)
                .body(p,Person.class); */
    }

    public Mono<ServerResponse> getPersonByEmail(ServerRequest request){
        Mono<Search> searchMono = request.bodyToMono(Search.class);
        return ServerResponse.status(HttpStatus.FOUND).contentType(MediaType.APPLICATION_JSON)
                .body(this.dataSvc.findByEmailAddress(searchMono.block().getTerm())
                        ,Person.class);
    }

    public Mono<ServerResponse> getPersonByPhone(ServerRequest request){
        Mono<Search> searchMono = request.bodyToMono(Search.class);
        return ServerResponse.status(HttpStatus.FOUND).contentType(MediaType.APPLICATION_JSON)
                .body(this.dataSvc.findByPhone(searchMono.block().getTerm())
                        ,Person.class);
    }
}
