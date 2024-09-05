package org.example.springrestproject.Events;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = EventController.class)
public class EventControllerTests {
    @Autowired
    MockMvc mockMvc;  // 웹서버를 띄우지 않고도 servlet이 요청 처리하는 과정을 확인할 수 있음 -> web과 관련된 bean들만 따로 구역을 나눠서 테스팅 가능

    @Test
    public void createEvent() throws Exception {
        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON) //perform 안에 들어가는 것이 요청
                        .accept(MediaTypes.HAL_JSON))
                .andExpect(status().isCreated()); // JSON 응답 201

    }
}
