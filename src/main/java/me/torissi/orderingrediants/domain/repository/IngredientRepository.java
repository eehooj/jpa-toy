package me.torissi.orderingrediants.domain.repository;

import me.torissi.orderingrediants.domain.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
