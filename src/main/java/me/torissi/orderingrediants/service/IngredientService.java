package me.torissi.orderingrediants.service;

import lombok.RequiredArgsConstructor;
import me.torissi.orderingrediants.domain.dto.request.IngredientCreateRequest;
import me.torissi.orderingrediants.domain.dto.request.IngredientUpdateRequest;
import me.torissi.orderingrediants.domain.dto.response.IngredientResponse;
import me.torissi.orderingrediants.domain.entity.Ingredient;
import me.torissi.orderingrediants.domain.service.IngredientDomainService;
import me.torissi.orderingrediants.domain.vo.OrderSearch;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class IngredientService {

  private final IngredientDomainService service;
  private final ModelMapper mapper;

  @Transactional
  public ResponseEntity<?> createIngredient(IngredientCreateRequest dto) {
    Long id = service.create(Ingredient.createEntity(
        dto.getName(),
        dto.getPrice()
    ));

    return ResponseEntity.ok(id);
  }

  public ResponseEntity<?> getIngredient(Long id) {
    return ResponseEntity.ok(mapper.map(service.get(id), IngredientResponse.class));
  }

  public ResponseEntity<?> getIngredientPage(OrderSearch search) {
    return ResponseEntity.ok(service.getPage(search)
        .map(entity -> mapper.map(entity, IngredientResponse.class)));
  }

  @Transactional
  public ResponseEntity<?> updateIngredient(Long id, IngredientUpdateRequest dto) {
    Ingredient ingredient = service.get(id);
    ingredient.updateEntity(dto.getName(), dto.getPrice());

    return ResponseEntity.ok(null);
  }

  @Transactional
  public ResponseEntity<?> removeIngredient(Long id) {
    Ingredient ingredient = service.get(id);
    ingredient.removeEntity();

    return ResponseEntity.ok(null);
  }
}
