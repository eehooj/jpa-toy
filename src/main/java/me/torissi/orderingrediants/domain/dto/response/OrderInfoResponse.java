package me.torissi.orderingrediants.domain.dto.response;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.torissi.orderingrediants.domain.entity.Ingredient;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfoResponse {

  private Long id;
  private Integer quantity;
  private IngredientResponse ingredient;
}
