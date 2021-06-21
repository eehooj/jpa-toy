package me.torissi.orderingrediants.domain.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import me.torissi.orderingrediants.domain.entity.Restaurant;
import me.torissi.orderingrediants.domain.repository.RestaurantRepository;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RestaurantDomainService {

  private final RestaurantRepository repository;

  @Transactional
  public Long create(Restaurant restaurant) {
    return repository.save(restaurant).getId();
  }

  public Restaurant get(Long id) throws NotFoundException {
    return repository.findById(id).orElseThrow(NotFoundException::new);
  }

}
