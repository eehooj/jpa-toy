package me.torissi.orderingrediants.domain.entity;

import static lombok.AccessLevel.PROTECTED;

import java.util.Optional;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "TB_ORDER_INFO")
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = PROTECTED)
public class OrderInfo extends EntityExtension {

  private Integer quantity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ingredientId")
  private Ingredient ingredient;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "orderId")
  private Order order;

  private OrderInfo(Integer quantity, Ingredient ingredient, Order order) {
    this.quantity = quantity;
    this.ingredient = ingredient;
    this.order = order;
  }

  public static OrderInfo createEntity(Integer quantity, Ingredient ingredient, Order order) {
    return new OrderInfo(quantity, ingredient, order);
  }

  public void updateEntity(Integer quantity, Ingredient ingredient, Order order) {
    this.quantity = Optional.ofNullable(quantity).orElse(this.quantity);
    this.ingredient = Optional.ofNullable(ingredient).orElse(this.ingredient);
    this.order = Optional.ofNullable(order).orElse(this.order);
  }

  public void removeEntity() {
    remove();
  }
}
