package me.torissi.orderingrediants.domain.repository;

import me.torissi.orderingrediants.domain.entity.Order;
import me.torissi.orderingrediants.domain.vo.OrderSearch;
import org.springframework.data.domain.Page;

public interface OrderRepositoryCustom {

  Page<Order> getPage(Long restaurantId, OrderSearch search);

}
