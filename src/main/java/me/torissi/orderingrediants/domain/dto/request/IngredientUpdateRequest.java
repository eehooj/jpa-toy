package me.torissi.orderingrediants.domain.dto.request;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngredientUpdateRequest {

  @NotEmpty
  private String name;

  @NotEmpty
  private Integer price;
}
