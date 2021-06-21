package me.torissi.orderingrediants.domain.entity;

import static lombok.AccessLevel.PROTECTED;

import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "TB_INGREDIANT")
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = PROTECTED)
public class Ingredient extends EntityExtension {

  @Column(length = 50, nullable = false)
  private String name;

  @Column(nullable = false)
  private Integer price;

  private Ingredient(String name, Integer price) {
    this.name = name;
    this.price = price;
  }

  public static Ingredient createEntity(String name, Integer price) {
    return new Ingredient(name, price);
  }

  public void updateEntity(String name, Integer price) {
    this.name = Optional.ofNullable(name).orElse(this.name);
    this.price = Optional.ofNullable(price).orElse(this.price);
  }
}
