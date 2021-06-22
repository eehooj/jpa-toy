package me.torissi.orderingrediants.service;

import lombok.RequiredArgsConstructor;
import me.torissi.orderingrediants.domain.dto.request.OrderChangeStatusRequest;
import me.torissi.orderingrediants.domain.dto.request.OrderCreateRequest;
import me.torissi.orderingrediants.domain.dto.response.OrderResponse;
import me.torissi.orderingrediants.domain.entity.Order;
import me.torissi.orderingrediants.domain.entity.OrderInfo;
import me.torissi.orderingrediants.domain.entity.Restaurant;
import me.torissi.orderingrediants.domain.service.IngredientDomainService;
import me.torissi.orderingrediants.domain.service.OrderDomainService;
import me.torissi.orderingrediants.domain.service.OrderInfoDomainService;
import me.torissi.orderingrediants.domain.service.RestaurantDomainService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

  private final OrderDomainService orderDomainService;
  private final OrderInfoDomainService orderInfoDomainService;

  private final RestaurantDomainService restaurantDomainService;
  private final IngredientDomainService ingredientDomainService;

  private final ModelMapper mapper;

  @Transactional
  public ResponseEntity<?> createOrder(Long restaurantId, OrderCreateRequest dto) {
    Restaurant restaurant = restaurantDomainService.get(restaurantId);
    Order orderEntity = Order.createEntity(restaurant);
    Long id = orderDomainService.create(orderEntity);

    dto.getOrderInfoList()
        .forEach(item -> orderInfoDomainService.create(OrderInfo.createEntity(
        item.getQuantity(), ingredientDomainService
                .get(item.getIngredient().getId()), orderEntity)));

    return ResponseEntity.ok(id);
  }

  public ResponseEntity<?> getOrder(Long id) {
    Order order = orderDomainService.get(id);
    OrderResponse data = mapper.map(order, OrderResponse.class);

    return ResponseEntity.ok(data);
  }

  @Transactional
  public ResponseEntity<?> changeOrderStatus(Long id, OrderChangeStatusRequest dto) {
    Order order = orderDomainService.get(id);

    order.updateEntity(dto.getStatus());

    return ResponseEntity.ok().body(null);
  }
}
