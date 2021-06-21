package me.torissi.orderingrediants.service;

import lombok.RequiredArgsConstructor;
import me.torissi.orderingrediants.domain.service.RestaurantDomainService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RestaurantService {

  private final RestaurantDomainService service;

}
