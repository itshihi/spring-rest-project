package org.example.springrestproject.Events;

import lombok.*;

import java.time.LocalDateTime;

// 입력값을 받는 객체만 dto에 해당
//dto에 validation과 관련된 어노를 따로 분류한다.controller에 모든 어노를 작성하면 보기 힘들어서..
@Builder @NoArgsConstructor @AllArgsConstructor @Data
public class EventDTO {
    private String name;
    private String description;
    private LocalDateTime beginEnrollmentDateTime;
    private LocalDateTime endEnrollmentDateTime;
    private LocalDateTime beginEventDateTime;
    private LocalDateTime endEventDateTime;
    private String location;
    private int basePrice;
    private int maxPrice;
    private int limitOfEnrollment;

}
