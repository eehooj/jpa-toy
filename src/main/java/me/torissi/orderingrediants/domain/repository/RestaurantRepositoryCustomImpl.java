package me.torissi.orderingrediants.domain.repository;

import static me.torissi.orderingrediants.domain.entity.QRestaurant.restaurant;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.torissi.orderingrediants.domain.entity.Restaurant;
import me.torissi.orderingrediants.domain.enumeration.SortTarget;
import me.torissi.orderingrediants.domain.vo.OrderSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
public class RestaurantRepositoryCustomImpl implements RestaurantRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  @Override
  public Page<Restaurant> getPages(OrderSearch search) {
    JPAQuery<Restaurant> query = queryFactory.selectFrom(restaurant)
        .where(restaurant.delFlag.isFalse());

    if (!StringUtils.hasLength(search.getName())) {
      query.where(restaurant.name.containsIgnoreCase(search.getName()));
    }

    if (!StringUtils.hasLength(search.getStarget())
        && !StringUtils.hasLength(search.getStype())) {
      SortTarget sort = SortTarget.valueOf(search.getStarget());
      String type = search.getStype();
      Order orderType = type.equalsIgnoreCase("asc") ? Order.ASC : Order.DESC;

      if (sort == SortTarget.NAME) {
        query.orderBy(new OrderSpecifier<>(orderType, restaurant.name));
      }
    }

    query.orderBy(restaurant.id.desc());

    // 페이징 객체 생성
    PageRequest pageRequest = PageRequest.of(search.getPage(), search.getLimit());

    // 페이징 처리
    query.offset(pageRequest.getOffset())
        .limit(pageRequest.getPageSize());

    // 쿼리 실행
    QueryResults<Restaurant> result = query.fetchResults();

    // 페이지 객체로 반환
    return new PageImpl<>(result.getResults(), pageRequest, result.getTotal());
  }
}
