package me.torissi.orderingrediants.controller;

import static me.torissi.orderingrediants.common.config.URIConfig.uriRestaurant;
import static me.torissi.orderingrediants.common.config.URIConfig.uriRestaurantId;

import java.util.Objects;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.torissi.orderingrediants.domain.dto.request.RestaurantCreateRequest;
import me.torissi.orderingrediants.domain.dto.request.RestaurantUpdateRequest;
import me.torissi.orderingrediants.domain.vo.OrderSearch;
import me.torissi.orderingrediants.service.RestaurantService;
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
public class RestaurantController {

  private final RestaurantService service;

  @PostMapping(uriRestaurant)
  public ResponseEntity<?> createRestaurant(@RequestBody @Valid RestaurantCreateRequest dto) {
    return service.createRestaurant(dto);
  }

  @PutMapping(uriRestaurantId)
  public ResponseEntity<?> updateRestaurant(
      @PathVariable Long id, @RequestBody @Valid RestaurantUpdateRequest dto) {
    return service.updateRestaurant(id, dto);
  }

  @GetMapping(uriRestaurantId)
  public ResponseEntity<?> getRestaurant(@PathVariable Long id) {
    return service.getRestaurant(id);
  }

  @GetMapping(uriRestaurant)
  public ResponseEntity<?> getRestaurantPage(@ModelAttribute @Valid OrderSearch search, BindingResult bindingResult) {
    if (Objects.isNull(search.getPage())) {
      bindingResult.rejectValue("page", "required value");
    }

    if (Objects.isNull(search.getLimit())) {
      bindingResult.rejectValue("limit", "required value");
    }

    if (bindingResult.hasErrors()) {
      return ResponseEntity.badRequest().body(bindingResult);
    }

    return service.getRestaurantPage(search);
  }

  @DeleteMapping(uriRestaurantId)
  public ResponseEntity<?> removeRestaurant(@PathVariable Long id) {
    return service.removeRestaurant(id);
  }

}
