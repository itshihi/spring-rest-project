package org.example.springrestproject.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.validation.Errors;

import java.io.IOException;

// 스프링에서 제공하는 errors 객체는 Bean spec을 따르지 않는다. -> bean serializor로 json화 할 수 없다
// -> json으로 변환해서 hal_json으로 넘겨주기 위한 코드

@JsonComponent
public class ErrorsSerializer extends JsonSerializer<Errors> {
    @Override
    public void serialize(Errors errors, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        gen.writeStartArray();

        // rejectValue로 error를 담을 때 field에 담는다.
        errors.getFieldErrors().forEach(e->{
            try {
                gen.writeStartObject();
                gen.writeStringField("field", e.getField());
                gen.writeStringField("objectName", e.getObjectName());
                gen.writeStringField("defaultMessage", e.getDefaultMessage());
                gen.writeStringField("code", e.getCode());
                Object rejectedObject = e.getRejectedValue();
                if(rejectedObject != null) {
                    gen.writeStringField("rejectedValue", rejectedObject.toString());
                }
                gen.writeEndObject();

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        errors.getGlobalErrors().forEach(e->{
            try {
                gen.writeStartObject();
                gen.writeStringField("objectName", e.getObjectName());
                gen.writeStringField("defaultMessage", e.getDefaultMessage());
                gen.writeEndObject();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        gen.writeEndArray();
    }
}
