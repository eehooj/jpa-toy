package me.torissi.orderingrediants.domain.service;

import lombok.RequiredArgsConstructor;
import me.torissi.orderingrediants.domain.entity.Ingredient;
import me.torissi.orderingrediants.domain.repository.IngredientRepository;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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

  public Ingredient get(Long id) throws NotFoundException {
    return repository.findById(id).orElseThrow(NotFoundException::new);
  }
}
