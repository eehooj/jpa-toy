package me.torissi.orderingrediants.service;

import lombok.RequiredArgsConstructor;
import me.torissi.orderingrediants.domain.dto.request.RestaurantCreateRequest;
import me.torissi.orderingrediants.domain.dto.response.RestaurantResponse;
import me.torissi.orderingrediants.domain.entity.Restaurant;
import me.torissi.orderingrediants.domain.service.RestaurantDomainService;
import me.torissi.orderingrediants.domain.vo.OrderSearch;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RestaurantService {

  private final RestaurantDomainService service;
  private final ModelMapper mapper;

  @Transactional
  public ResponseEntity<?> createRestaurant(RestaurantCreateRequest restaurantCreateRequest) {
    Long restaurant = service.create(Restaurant.createEntity(
        restaurantCreateRequest.getName()
    ));

    return ResponseEntity.ok(restaurant);
  }

  public ResponseEntity<?> getRestaurant(Long id) {
    return ResponseEntity.ok(mapper.map(service.get(id), RestaurantResponse.class));
  }

  public ResponseEntity<?> getRestaurantPage(OrderSearch search) {
    return ResponseEntity.ok(service.getPage(search)
        .map(entity -> mapper.map(entity, RestaurantResponse.class)));
  }
}
