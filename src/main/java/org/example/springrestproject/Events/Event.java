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
    private EventStatus eventStatus;
}
