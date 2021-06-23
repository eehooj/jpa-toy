package me.torissi.orderingrediants.common.exceptions;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;

@JsonComponent
public class ErrorsSerializer extends JsonSerializer<Errors> {

  @Override
  public void serialize(Errors errors, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException {
    generator.writeStartObject();
    generator.writeStringField("name", "parameter-exception");
    generator.writeStringField("code", String.valueOf(HttpStatus.BAD_REQUEST.value()));
    generator.writeStringField("message", "Parameter Exception");

    generator.writeArrayFieldStart("list");

    errors.getFieldErrors().forEach(e -> {
      try {
        generator.writeStartObject();
        generator.writeStringField("field", e.getField());
        generator.writeStringField("objectName", e.getObjectName());
        generator.writeStringField("code", e.getCode());
        generator.writeStringField("defaultMessage", e.getDefaultMessage());
        generator.writeEndObject();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    });

    errors.getGlobalErrors().forEach(e -> {
      try {
        generator.writeStartObject();
        generator.writeStringField("objectName", e.getObjectName());
        generator.writeStringField("code", e.getCode());
        generator.writeStringField("defaultMessage", e.getDefaultMessage());
        generator.writeEndObject();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    });

    generator.writeEndArray();
  }

}

