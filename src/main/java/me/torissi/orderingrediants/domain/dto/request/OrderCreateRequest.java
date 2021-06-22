package me.torissi.orderingrediants.domain.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.torissi.orderingrediants.domain.entity.OrderInfo;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateRequest {

  private List<OrderInfoCreateRequest> orderInfoList;

}
