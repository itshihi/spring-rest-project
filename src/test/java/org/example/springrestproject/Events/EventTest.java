package org.example.springrestproject.Events;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

//@RunWith(JUnitParamsRunner.class) // JUnit5) 클래스 레벨 어노 삭제
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

//JUnit5) 메서드 레벨 @Test -> ParameterizedTest 어노로 변경
    @ParameterizedTest
    @MethodSource(value = "paramsForTestisFree")
    public void isFree(int basePrice, int maxPrice, boolean isFree){        // Event 객체를 builder로 값과 함께 생성
        // Given
        Event event = Event.builder()
                .basePrice(basePrice)
                .maxPrice(maxPrice)
                .build();

        // When
        // 만약 baseprice가 0보다 크면 이 event는 free가 아님 -> 조건문?
        event.update();

        // Than
        assertThat(event.isFree()).isEqualTo(isFree);
    }

    static private Object[] paramsForTestisFree(){
        return new Object[] {
                new Object[] {0, 0, true},
                new Object[] {100, 0, false},
                new Object[] {0, 100, false},
                new Object[] {100, 200, false}
        };
    }

    @ParameterizedTest
    @MethodSource("paramsForTestisOffline")
    public void isOffline(String location, boolean isOffline){
        // Given
        Event event = Event.builder()
                .location(location)
//                .offLine(isOffline) // 이건 update 값에 의해 변경될 것
                .build();

        // When
        event.update();

        // Then
        assertThat(event.isOffLine()).isEqualTo(isOffline);
    }

    static private Object[] paramsForTestisOffline(){
        return new Object[] {
                new Object[] {"정자역", false},
                new Object[] {null, true},
                new Object[] {"     ", true}
        };
    }
}