package me.torissi.orderingrediants.domain.entity;

import static lombok.AccessLevel.PROTECTED;

import java.util.List;
import java.util.Optional;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.torissi.orderingrediants.domain.enumeration.OrderStatus;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "TB_ORDER")
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = PROTECTED)
public class Order extends EntityExtension {

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
  @OrderBy("id asc")
  private List<OrderInfo> orderInfoList;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "restaurantId") //외래키를 가지고 있는 테이블에 조인 컬럼! => 반대 경우도 가능하나, 디비와 경우가 달라져 헷갈림
  private Restaurant restaurant;

  private Order(Restaurant restaurant) {
    this.status = OrderStatus.READY;
    this.restaurant = restaurant;
  }

  public static Order createEntity(Restaurant restaurant) {
    return new Order(restaurant);
  }

  public void updateEntity(OrderStatus status) {
    this.status = Optional.ofNullable(status).orElse(this.status);
  }

  public void removeEntity() {
    remove();
  }
}
