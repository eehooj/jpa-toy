package me.torissi.orderingrediants.domain.repository;

import me.torissi.orderingrediants.domain.entity.Restaurant;
import me.torissi.orderingrediants.domain.vo.OrderSearch;
import org.springframework.data.domain.Page;

public interface RestaurantRepositoryCustom {

  Page<Restaurant> getPages(OrderSearch orderSearch);
}
