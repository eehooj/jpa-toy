package me.torissi.orderingrediants.service;

import lombok.RequiredArgsConstructor;
import me.torissi.orderingrediants.domain.service.OrderDomainService;
import me.torissi.orderingrediants.domain.service.OrderInfoDomainService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

  private final OrderDomainService orderDomainService;
  private final OrderInfoDomainService orderInfoDomainService;


}
