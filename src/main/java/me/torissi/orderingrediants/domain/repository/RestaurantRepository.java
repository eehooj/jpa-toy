package me.torissi.orderingrediants.domain.repository;

import java.util.Optional;
import me.torissi.orderingrediants.domain.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}
