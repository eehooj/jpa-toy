package me.torissi.orderingrediants.domain.service;

import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.torissi.orderingrediants.domain.entity.Ingredient;
import me.torissi.orderingrediants.domain.repository.IngredientRepository;
import me.torissi.orderingrediants.domain.vo.OrderSearch;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class IngredientDomainService {

  private final IngredientRepository repository;

  @Transactional
  public Long create(Ingredient ingredient) {
    return repository.save(ingredient).getId();
  }

  public Ingredient get(Long id) throws EntityNotFoundException {
    return repository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  public Page<Ingredient> getPage(OrderSearch search) {
    return repository.getPage(search);
  }
}
