package me.torissi.orderingrediants.domain.repository;

import me.torissi.orderingrediants.domain.entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long> {

}
