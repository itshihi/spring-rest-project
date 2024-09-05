package org.example.springrestproject.Events;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = EventController.class)
public class EventControllerTests {
    @Autowired
    MockMvc mockMvc;  // 웹서버를 띄우지 않고도 servlet이 요청 처리하는 과정을 확인할 수 있음 -> web과 관련된 bean들만 따로 구역을 나눠서 테스팅 가능

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @MockBean
    EventRepository eventRepository;

    @Test
    public void createEvent() throws Exception {
        Event event = Event.builder()
                .id(12)
                .name("Spring")
                .description("This is a Spring")
                .beginEnrollmentDateTime(LocalDateTime.of(2024, 9, 5, 10, 45))
                .endEnrollmentDateTime(LocalDateTime.of(2024, 9, 6, 10, 45))
                .beginEventDateTime(LocalDateTime.of(2024, 9, 7, 18, 0))
                .endEventDateTime(LocalDateTime.of(2024, 9, 7, 21, 0))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("정자역")
                .build();
        event.setId(10);
        Mockito.when(eventRepository.save(event)).thenReturn(event);

        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON) //perform 안에 들어가는 것이 요청
                        .accept(MediaTypes.HAL_JSON)
                        .content(jacksonObjectMapper.writeValueAsString(event)))
                .andDo(print()) // 어떤 요청과 응답을 받았는지 확인
                .andExpect(status().isCreated()) // JSON 응답 201
                .andExpect(jsonPath("id").exists()); // id가 존재하는지 확인


    }
}
