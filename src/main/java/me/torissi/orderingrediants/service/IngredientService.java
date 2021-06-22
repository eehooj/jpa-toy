package me.torissi.orderingrediants.service;

import lombok.RequiredArgsConstructor;
import me.torissi.orderingrediants.domain.dto.request.IngredientCreateRequest;
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
  public ResponseEntity<?> createIngredient(IngredientCreateRequest ingredientCreateRequest) {
    Long id = service.create(Ingredient.createEntity(
        ingredientCreateRequest.getName(),
        ingredientCreateRequest.getPrice()
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
}
