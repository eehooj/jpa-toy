package me.torissi.orderingrediants.controller;

import static me.torissi.orderingrediants.common.config.URIConfig.uriIngredient;
import static me.torissi.orderingrediants.common.config.URIConfig.uriIngredientId;

import java.util.Objects;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.torissi.orderingrediants.domain.dto.request.IngredientCreateRequest;
import me.torissi.orderingrediants.domain.dto.request.IngredientUpdateRequest;
import me.torissi.orderingrediants.domain.vo.OrderSearch;
import me.torissi.orderingrediants.service.IngredientService;
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
public class IngredientController {

  private final IngredientService service;

  @PostMapping(uriIngredient)
  public ResponseEntity<?> createIngredient(@RequestBody @Valid IngredientCreateRequest dto) {
    return service.createIngredient(dto);
  }

  @PutMapping(uriIngredientId)
  public ResponseEntity<?> updateIngredient(
      @PathVariable Long id, @RequestBody @Valid IngredientUpdateRequest dto) {
    return service.updateIngredient(id, dto);
  }

  @GetMapping(uriIngredientId)
  public ResponseEntity<?> getIngredient(@PathVariable Long id) {
    return service.getIngredient(id);
  }

  @GetMapping(uriIngredient)
  public ResponseEntity<?> getIngredientPage(
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

    return service.getIngredientPage(search);
  }

  @DeleteMapping(uriIngredientId)
  public ResponseEntity<?> removeIngredient(@PathVariable Long id) {
    return service.removeIngredient(id);
  }
}
