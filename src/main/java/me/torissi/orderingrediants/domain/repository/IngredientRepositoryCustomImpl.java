package me.torissi.orderingrediants.domain.repository;

import static me.torissi.orderingrediants.domain.entity.QIngredient.ingredient;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.torissi.orderingrediants.domain.entity.Ingredient;
import me.torissi.orderingrediants.domain.enumeration.SortTarget;
import me.torissi.orderingrediants.domain.vo.OrderSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
public class IngredientRepositoryCustomImpl implements IngredientRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  @Override
  public Page<Ingredient> getPage(OrderSearch search) {
    JPAQuery<Ingredient> query = queryFactory.selectFrom(ingredient)
        .where(ingredient.delFlag.isFalse())
        ;

    if (!StringUtils.hasLength(search.getName())) {
      query.where(ingredient.name.containsIgnoreCase(search.getName()));
    }

    if (!StringUtils.hasLength(search.getSortTarget())
        && !StringUtils.hasLength(search.getSortType())) {
      SortTarget sort = SortTarget.valueOf(search.getSortTarget());
      String type = search.getSortType();
      Order orderType = type.equalsIgnoreCase(Order.ASC.name()) ? Order.ASC : Order.DESC;

      if (sort == SortTarget.NAME) {
        query.orderBy(new OrderSpecifier<>(orderType, ingredient.name));
      } else if (sort == SortTarget.PRICE) {
        query.orderBy(new OrderSpecifier<>(orderType, ingredient.price));
      }
    }

    query.orderBy(ingredient.id.desc());

    // 페이징 객체 생성
    PageRequest pageRequest = PageRequest.of(search.getPage(), search.getLimit());

    // 페이징 처리
    query.offset(pageRequest.getOffset())
        .limit(pageRequest.getPageSize());

    // 쿼리 실행
    QueryResults<Ingredient> result = query.fetchResults();

    // 페이지 객체로 반환
    return new PageImpl<>(result.getResults(), pageRequest, result.getTotal());
  }
}
