package me.torissi.orderingrediants.controller;

import static me.torissi.orderingrediants.common.config.URIConfig.uriOrderId;
import static me.torissi.orderingrediants.common.config.URIConfig.uriOrderRestaurantId;

import java.util.Objects;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.torissi.orderingrediants.domain.dto.request.OrderChangeStatusRequest;
import me.torissi.orderingrediants.domain.dto.request.OrderCreateRequest;
import me.torissi.orderingrediants.domain.vo.OrderSearch;
import me.torissi.orderingrediants.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

  private final OrderService service;

  @PostMapping(uriOrderRestaurantId)
  public ResponseEntity<?> createOrder(
      @PathVariable Long restaurantId, @RequestBody @Valid OrderCreateRequest dto) {
    return service.createOrder(restaurantId, dto);
  }

  @PutMapping(uriOrderId)
  public ResponseEntity<?> changeOrderStatus(
      @PathVariable Long id, @RequestBody @Valid OrderChangeStatusRequest dto) {
    return service.changeOrderStatus(id, dto);
  }

  @GetMapping(uriOrderId)
  public ResponseEntity<?> getOrder(@PathVariable Long id) {
    return service.getOrder(id);
  }

  @GetMapping(uriOrderRestaurantId)
  public ResponseEntity<?> getOrderPage(
      @PathVariable Long restaurantId,
      @ModelAttribute @Valid OrderSearch search, BindingResult bindingResult) {
    if (Objects.isNull(search.getPage())) {
      bindingResult.rejectValue("page", "required value");
    }

    if (Objects.isNull(search.getLimit())) {
      bindingResult.rejectValue("limit", "required value");
    }

    if (bindingResult.hasErrors()) {
      return ResponseEntity.badRequest().body(bindingResult);
    }

    return service.getOrderPage(restaurantId, search);
  }

  @DeleteMapping(uriOrderId)
  public ResponseEntity<?> removeOrder(@PathVariable Long id) {
    return service.removeOrder(id);
  }
}
