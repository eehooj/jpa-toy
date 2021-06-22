package me.torissi.orderingrediants.domain.repository;

import me.torissi.orderingrediants.domain.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository
    extends JpaRepository<Restaurant, Long>, RestaurantRepositoryCustom {

}
