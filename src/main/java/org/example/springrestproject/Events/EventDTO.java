package org.example.springrestproject.Events;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

// 입력값을 받는 객체만 dto에 해당
//dto에 validation과 관련된 어노를 따로 분류한다.controller에 모든 어노를 작성하면 보기 힘들어서..
@Builder @NoArgsConstructor @AllArgsConstructor @Data
public class EventDTO {
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotNull
    private LocalDateTime beginEnrollmentDateTime;
    @NotNull
    private LocalDateTime endEnrollmentDateTime;
    @NotNull
    private LocalDateTime beginEventDateTime;
    @NotNull
    private LocalDateTime endEventDateTime;
    @NotEmpty
    private String location;
    @Min(0)
    private int basePrice;
    @Min(0)
    private int maxPrice;
    @Min(0)
    private int limitOfEnrollment;

}
