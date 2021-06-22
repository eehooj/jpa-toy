package me.torissi.orderingrediants.domain.entity;

import static lombok.AccessLevel.PROTECTED;

import java.util.List;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "TB_RESTAURANT")
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = PROTECTED)
public class Restaurant extends EntityExtension {

  @Column(length = 50, nullable = false)
  private String name;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
  private List<Order> orderList;

  private Restaurant(String name) {
    this.name = name;
  }

  public static Restaurant createEntity(String name) {
    return new Restaurant(name);
  }

  public void updateEntity(String name) {
    this.name = Optional.ofNullable(name).orElse(this.name);
  }
}
