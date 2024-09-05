package org.example.springrestproject.Events;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.print.attribute.standard.Media;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest // Web과 관련된 bean 등록이 됨
public class EventControllerTests {
    @Autowired
    MockMvc mockMvc;  // 웹서버를 띄우지 않고도 servlet이 요청 처리하는 과정을 확인할 수 있음 -> web과 관련된 bean들만 따로 구역을 나눠서 테스팅 가능
                    // 웹과 관련된 bean만 등록하기 때문에 slicing testing이라고 함

    @Test
    public void createEvent() throws Exception {
        mockMvc.perform(post("/api/events") //post 요청
                        .contentType(MediaType.APPLICATION_JSON_UTF8) //perform 안에 들어가는 것이 요청
                        .accept(MediaTypes.HAL_JSON)) // 원하는 응답의 확장자 유형 지정
                .andDo(print())
                .andExpect(status().isCreated()); // 원하는 JSON 응답 201

    }
}
