package me.torissi.orderingrediants.domain.dto.response;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.torissi.orderingrediants.domain.entity.OrderInfo;
import me.torissi.orderingrediants.domain.enumeration.OrderStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

  private Long id;
  private OrderStatus status;
  private List<OrderInfoResponse> orderInfoList;
  @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime createDt;
  @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime updateDt;
}
