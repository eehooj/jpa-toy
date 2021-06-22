package me.torissi.orderingrediants.domain.service;

import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.torissi.orderingrediants.domain.entity.OrderInfo;
import me.torissi.orderingrediants.domain.repository.OrderInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderInfoDomainService {

  private final OrderInfoRepository repository;

  @Transactional
  public Long create(OrderInfo orderInfo) {
    return repository.save(orderInfo).getId();
  }

  public OrderInfo get(Long id) throws EntityNotFoundException {
    return repository.findById(id).orElseThrow(EntityNotFoundException::new);
  }
}
