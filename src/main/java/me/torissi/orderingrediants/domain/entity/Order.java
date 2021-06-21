package me.torissi.orderingrediants.domain.entity;

import static lombok.AccessLevel.PROTECTED;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.torissi.orderingrediants.domain.enumeration.OrderStatus;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "TB_ORDER")
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = PROTECTED)
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  @ColumnDefault("CURRENT_TIMESTAMP")
  private LocalDateTime createDt;

  @ColumnDefault("CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
  private LocalDateTime updateDt;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
  private List<OrderInfo> orderInfoList;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "restaurantId") //외래키를 가지고 있는 테이블에 조인 컬럼! => 반대 경우도 가능하나, 디비와 경우가 달라져 헷갈림
  private Restaurant restaurant;

  private Order(OrderStatus status, List<OrderInfo> orderInfoList, Restaurant restaurant) {
    this.status = status;
    this.orderInfoList = orderInfoList;
    this.restaurant = restaurant;
  }

  public static Order createEntity(
      OrderStatus status, List<OrderInfo> orderInfoList, Restaurant restaurant) {
    return new Order(status, orderInfoList, restaurant);
  }

  public void updateEntity(
      OrderStatus status, List<OrderInfo> orderInfoList, Restaurant restaurant) {
    this.status = Optional.ofNullable(status).orElse(this.status);
    this.orderInfoList = Optional.ofNullable(orderInfoList).orElse(this.orderInfoList);
    this.restaurant = Optional.ofNullable(restaurant).orElse(this.restaurant);
  }
}
