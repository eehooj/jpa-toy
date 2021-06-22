package me.torissi.orderingrediants.domain.repository;

import static me.torissi.orderingrediants.domain.entity.QOrder.order;
import static me.torissi.orderingrediants.domain.entity.QRestaurant.restaurant;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.torissi.orderingrediants.domain.entity.Order;
import me.torissi.orderingrediants.domain.vo.OrderSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

@RequiredArgsConstructor
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  @Override
  public Page<Order> getPage(Long restaurantId, OrderSearch search) {
    JPAQuery<Order> query = queryFactory.selectFrom(order)
        .where(
            order.delFlag.isFalse(),
            order.restaurant.id.eq(restaurantId)
        );

    query.orderBy(restaurant.id.desc());

    // 페이징 객체 생성
    PageRequest pageRequest = PageRequest.of(search.getPage(), search.getLimit());

    // 페이징 처리
    query.offset(pageRequest.getOffset())
        .limit(pageRequest.getPageSize());

    // 쿼리 실행
    QueryResults<Order> result = query.fetchResults();

    // 페이지 객체로 반환
    return new PageImpl<>(result.getResults(), pageRequest, result.getTotal());
  }
}
