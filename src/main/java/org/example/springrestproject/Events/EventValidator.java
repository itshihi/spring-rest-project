package org.example.springrestproject.Events;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.View;

import java.time.LocalDateTime;

@Component
public class EventValidator {


    public void validateEvent(EventDTO eventDTO, Errors errors) {
        // max값이 base보다 작을 때
        if(eventDTO.getBasePrice()>eventDTO.getMaxPrice() && eventDTO.getMaxPrice()>=0) {
            errors.rejectValue("basePrice", "wringValue", "BasePrice is wrong.");
            errors.rejectValue("maxPrice", "wringValue", "maxPrice is wrong.");
        }

        // 이벤트 종류시간이 시작시간, 접수기간에 비해 이를 때
        LocalDateTime endEventDateTime = eventDTO.getEndEventDateTime();
        if(endEventDateTime.isBefore(eventDTO.getBeginEventDateTime()) ||
        endEventDateTime.isBefore(eventDTO.getBeginEnrollmentDateTime()) ||
        endEventDateTime.isBefore(eventDTO.getEndEnrollmentDateTime())) {
            errors.rejectValue("endEventDateTime", "endEventDateTime", "End Event DateTime is wrong.");
        }

        // 이벤트 시작 시간이 접수 기간보다 이를 때
        LocalDateTime beginEventDateTime = eventDTO.getBeginEventDateTime();
        if(beginEventDateTime.isBefore(eventDTO.getBeginEnrollmentDateTime()) ||
        beginEventDateTime.isBefore(eventDTO.getEndEnrollmentDateTime())) {
            errors.rejectValue("beginEventDateTime", "beginEventDateTime", "Begin Event DateTime is wrong.");
        }

        // 이벤트 접수 마감 시간이 시작시간보다 이를 때
        LocalDateTime endEnrollmentDateTime = eventDTO.getEndEnrollmentDateTime();
        if(endEnrollmentDateTime.isBefore(eventDTO.getBeginEnrollmentDateTime())){
            errors.rejectValue("endEnrollmentDateTime", "endEnrollmentDateTime", "End Enrollment DateTime is wrong.");
        }
    }
}
