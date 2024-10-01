package org.example.springrestproject.Events;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static org.springframework.hateoas.MediaTypes.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
@RequestMapping(value = "/api/events", produces = HAL_JSON_VALUE)
public class EventController {

    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;
    private final EventValidator eventValidator;

    // 생성자를 통해 repo 의존성 주입, 유일한 생성자이고 파라미터로 불러오는 repo가 bean에 등록되어있다면 Autowired 어노는 생략 가능
    public EventController(EventRepository eventRepository, ModelMapper modelMapper, EventValidator eventValidator) {
        this.eventRepository = eventRepository; // 생성자를 통해 의존성 주입
        this.modelMapper = modelMapper;
        this.eventValidator = eventValidator;
    }

    @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody @Valid EventDTO eventDto, Errors errors) {
        if (errors.hasErrors()){
            return ResponseEntity.badRequest().build();
        }

        eventValidator.validateEvent(eventDto, errors); // DTO 데이터 검증
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body(errors);
        }

        Event event = modelMapper.map(eventDto, Event.class); // modelmapper를 통해 dto를 event타입의 class 형태로 변환
        event.update(); // free와 offline 검증
        Event newEvent = this.eventRepository.save(event); // 저장된 객체 newEvent

        URI createdUri =linkTo(EventController.class).slash(newEvent.getId()).toUri(); // 링크를 만들고 linkTo로 URL 생성한다.
        return ResponseEntity.created(createdUri).body(event); // 헤더를 생성?

    }
}
