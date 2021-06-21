package me.torissi.orderingrediants.domain.entity;

import java.time.LocalDateTime;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@MappedSuperclass
public class EntityExtension {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  @ColumnDefault("CURRENT_TIMESTAMP")
  protected LocalDateTime createDt;

  @ColumnDefault("CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
  protected LocalDateTime updateDt;

  @ColumnDefault("false")
  protected Boolean delFlag;
}
