package me.torissi.orderingrediants.domain.service;

import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.torissi.orderingrediants.domain.entity.Order;
import me.torissi.orderingrediants.domain.repository.OrderRepository;
import me.torissi.orderingrediants.domain.vo.OrderSearch;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderDomainService {

  private final OrderRepository repository;

  @Transactional
  public Long create(Order order) {
    return repository.save(order).getId();
  }

  public Order get(Long id) throws EntityNotFoundException {
    return repository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  public Page<Order> getPage(Long restaurantId, OrderSearch search) {
    return repository.getPage(restaurantId, search);
  }
}
