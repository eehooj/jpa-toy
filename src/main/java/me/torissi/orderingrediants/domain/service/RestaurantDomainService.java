package me.torissi.orderingrediants.domain.service;

import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.torissi.orderingrediants.domain.entity.Restaurant;
import me.torissi.orderingrediants.domain.repository.RestaurantRepository;
import me.torissi.orderingrediants.domain.vo.OrderSearch;
import org.springframework.data.domain.Page;
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

  public Restaurant get(Long id) throws EntityNotFoundException {
    return repository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  public Page<Restaurant> getPage(OrderSearch search) {
    return repository.getPages(search);
  }

}
