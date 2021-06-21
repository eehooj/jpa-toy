package me.torissi.orderingrediants.domain.service;

import lombok.RequiredArgsConstructor;
import me.torissi.orderingrediants.domain.entity.OrderInfo;
import me.torissi.orderingrediants.domain.repository.OrderInfoRepository;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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

  //단일 조회가 필요한가
  public OrderInfo get(Long id) throws NotFoundException {
    return repository.findById(id).orElseThrow(NotFoundException::new);
  }
}
