package org.example.springrestproject.Events;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

public class EventTest {


    @Test
    public void builder(){ // builder가 잘 존재하는지 확인
        Event event = Event.builder()
                .name("Spring REST API")
                .description("REST API ")
                .build();
        assertThat(event).isNotNull(); // 객체가 null인지 검증
    }

    @Test
    public void javaBean(){

        //Given
        Event event = new Event();
        String name = "Event";
        String description = "Spring";

        // When
        event.setName(name);
        event.setDescription("Spring");

        // Then
        assertThat(event.getName()).isEqualTo(name);
        assertThat(event.getDescription()).isEqualTo(description);
    }
}