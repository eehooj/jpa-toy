package me.torissi.orderingrediants.domain.service;

import lombok.RequiredArgsConstructor;
import me.torissi.orderingrediants.domain.entity.Order;
import me.torissi.orderingrediants.domain.repository.OrderRepository;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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

  public Order get(Long id) throws NotFoundException {
    return repository.findById(id).orElseThrow(NotFoundException::new);
  }
}
