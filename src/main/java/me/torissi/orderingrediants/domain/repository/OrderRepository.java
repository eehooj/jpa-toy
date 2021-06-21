package me.torissi.orderingrediants.domain.repository;

import me.torissi.orderingrediants.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
