package me.torissi.orderingrediants.domain.repository;

import me.torissi.orderingrediants.domain.entity.Ingredient;
import me.torissi.orderingrediants.domain.vo.OrderSearch;
import org.springframework.data.domain.Page;

public interface IngredientRepositoryCustom {

  Page<Ingredient> getPage(OrderSearch search);
}
