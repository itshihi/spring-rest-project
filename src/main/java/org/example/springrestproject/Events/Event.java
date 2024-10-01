package org.example.springrestproject.Events;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @EqualsAndHashCode(of = "id")
@Entity
public class Event {
    @Id @GeneratedValue
    private Integer id;
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
    private boolean offLine;
    private boolean free;

    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus = EventStatus.DRAFT;

    // update free and offline -> boolean 기본값 설정
    public void update() {
        if (this.basePrice==0 && this.maxPrice==0){
            this.free = true;
        } else {
            this.free = false;
        }

        if (this.location == null || this.location.isBlank() ){ // isBlank는 공백값도 비어있는 것으로 취급, null 먼저 체크 후 blanck 확인해야 에러 회피
            this.offLine = true;
        } else {
            this.offLine = false;
        }
    }
}
