package org.example.springrestproject.Events;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.head;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@WebMvcTest(controllers = EventController.class)
@SpringBootTest // mock 을 사용하지 않고 실제 repo를 사용해서 test 진행함
@AutoConfigureMockMvc //SpringbootTest와 함께 MockMvc 를 사용하려면 필요한 어노
public class EventControllerTests {
    @Autowired
    MockMvc mockMvc;  // 웹서버를 띄우지 않고도 servlet이 요청 처리하는 과정을 확인할 수 있음 -> web과 관련된 bean들만 따로 구역을 나눠서 테스팅 가능

    @Autowired
    private ObjectMapper jacksonObjectMapper;


    // 입력값이 제대로 들어오는 경우
    @Test
    public void createEvent() throws Exception {
        EventDTO event = EventDTO.builder()
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
//        Mockito.when(eventRepository.save(event)).thenReturn(event);

        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON) //perform 안에 들어가는 것이 요청
                        .accept(MediaTypes.HAL_JSON)
                        .content(jacksonObjectMapper.writeValueAsString(event))) // 처리한 요청을 json string 형식으로 변환
                .andDo(print()) // 어떤 요청과 응답을 받았는지 출력
                // do에서 보이는 내용을 andExpect로 검증한다.
                .andExpect(status().isCreated()) // JSON 응답 201 created 상태 코드를 받았는지 검증
                .andExpect(jsonPath("id").exists()) // jsonpath에 id 필드가 존재하는지 확인
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string("Content-Type", MediaTypes.HAL_JSON_VALUE))

                .andExpect(jsonPath("id").value(Matchers.not(100)))
                .andExpect(jsonPath("free").value(Matchers.not(true)))
                .andExpect(jsonPath("eventStatus").value(EventStatus.DRAFT.name()));

    }

    @Test
    public void createEvent_Bad_Request() throws Exception {
        Event event = Event.builder()
                .id(100)
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
                .free(true)
                .offLine(false)
                .eventStatus(EventStatus.PUBLISHED)
                .build();

        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON) //perform 안에 들어가는 것이 요청
                        .accept(MediaTypes.HAL_JSON)
                        .content(jacksonObjectMapper.writeValueAsString(event))) // 처리한 요청을 json string 형식으로 변환
                .andDo(print())
                .andExpect(status().isBadRequest())

        ;

    }

    // 들어오는 필드값이 비어있는 경우 bad request 발생
    @Test
    public void creatEvent_Bad_Request_Empty_Input() throws Exception {
        EventDTO eventDTO = EventDTO.builder().build();

        this.mockMvc.perform(post("/api/events")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(this.jacksonObjectMapper.writeValueAsString(eventDTO)))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void createEvent_Bad_Request_Wrong_Input() throws Exception {
        EventDTO eventDTO = EventDTO.builder()
                .name("Spring")
                .description("This is a Spring")
                .beginEnrollmentDateTime(LocalDateTime.of(2024, 9, 15, 10, 45)) // 시작일이 종료일보다 이후
                .endEnrollmentDateTime(LocalDateTime.of(2024, 9, 4, 10, 45))
                .beginEventDateTime(LocalDateTime.of(2024, 9, 27, 18, 0))
                .endEventDateTime(LocalDateTime.of(2024, 9, 7, 21, 0))
                .basePrice(100) // max가 base보다 작음
                .maxPrice(50)
                .limitOfEnrollment(100)
                .location("정자역")
                .build();

        this.mockMvc.perform(post("/api/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.jacksonObjectMapper.writeValueAsString(eventDTO)))
                .andExpect(status().isBadRequest());
    }



}
