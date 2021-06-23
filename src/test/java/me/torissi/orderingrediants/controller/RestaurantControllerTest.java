package me.torissi.orderingrediants.controller;

import static me.torissi.orderingrediants.common.config.URIConfig.uriOrderRestaurantId;
import static me.torissi.orderingrediants.common.config.URIConfig.uriRestaurant;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.modifyUris;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import me.torissi.orderingrediants.domain.dto.request.IdRequest;
import me.torissi.orderingrediants.domain.dto.request.OrderCreateRequest;
import me.torissi.orderingrediants.domain.dto.request.OrderInfoCreateRequest;
import me.torissi.orderingrediants.domain.entity.Ingredient;
import me.torissi.orderingrediants.domain.entity.Restaurant;
import me.torissi.orderingrediants.domain.service.IngredientDomainService;
import me.torissi.orderingrediants.domain.service.RestaurantDomainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
@AutoConfigureRestDocs
@AutoConfigureMockMvc
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RestaurantControllerTest {

  /**
   * The Object mapper
   */
  @Autowired
  protected ObjectMapper objectMapper;
  /**
   * The Entity manager
   */
  @Autowired
  private EntityManager entityManager;
  /**
   * The Mock mvc
   */
  private MockMvc mockMvc;

  /**
   * The Document
   */
  private RestDocumentationResultHandler document;

  @Autowired
  private RestaurantDomainService restaurantDomainService;
  @Autowired
  private IngredientDomainService ingredientDomainService;

  /**
   * Before each.
   *
   * @param webApplicationContext the web application context
   * @param restDocumentation     the rest documentation
   * @author [류성재]
   * @implNote 이 클래스를 상속받는 모든 클래스의 기본 설정
   * @since 2021. 2. 25. 오후 5:53:36
   */
  @BeforeEach
  public void beforeEach(WebApplicationContext webApplicationContext,
      RestDocumentationContextProvider restDocumentation) {
    this.document = document(
        "{class-name}/{method-name}", // 문서 경로 설정
        preprocessRequest( // Request 설정
            modifyUris()
                .scheme("https")
                .host("rest.emotion.kr"), // 문서에 노출되는 도메인 설정
            prettyPrint() // 정리해서 출력
        ),
        preprocessResponse(prettyPrint()) // Response 설정. 정리해서 출력
    );

    this.mockMvc = MockMvcBuilders // MockMvc 공통 설정. 문서 출력 설정
        .webAppContextSetup(webApplicationContext)
        .addFilter(new CharacterEncodingFilter("UTF-8", true))
        .apply(documentationConfiguration(restDocumentation))
        .alwaysDo(document)
        .build();
  }

  @Test
  public void getRestaurantPage() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(uriRestaurant).contentType(APPLICATION_JSON)
        .accept(APPLICATION_JSON)).andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void createOrder() throws Exception {
    Restaurant restaurant = Restaurant.createEntity("TEST 001");
    Ingredient ingredient1 = Ingredient.createEntity("TEST-I-001", 1000);
    Ingredient ingredient2 = Ingredient.createEntity("TEST-I-002", 1100);
    Ingredient ingredient3 = Ingredient.createEntity("TEST-I-003", 1200);

    Long restaurantId = restaurantDomainService.create(restaurant);
    Long ingredient1Id = ingredientDomainService.create(ingredient1);
    Long ingredient2Id = ingredientDomainService.create(ingredient2);
    Long ingredient3Id = ingredientDomainService.create(ingredient3);

    entityManager.flush(); // 영속성 컨텍스트 내용을 데이터베이스에 반영
    entityManager.clear(); // 영속성 컨텍스트 초기화

    OrderCreateRequest content = OrderCreateRequest
        .builder()
        .orderInfoList(List.of(
            OrderInfoCreateRequest
                .builder()
                .ingredient(IdRequest.builder().id(ingredient1Id).build())
                .quantity(1)
                .build(),
            OrderInfoCreateRequest
                .builder()
                .ingredient(IdRequest.builder().id(ingredient2Id).build())
                .quantity(2)
                .build(),
            OrderInfoCreateRequest
                .builder()
                .ingredient(IdRequest.builder().id(ingredient3Id).build())
                .quantity(3)
                .build()
        ))
        .build();

    mockMvc.perform(MockMvcRequestBuilders.post(uriOrderRestaurantId, restaurantId)
        .content(objectMapper.writeValueAsString(content))
        .contentType(APPLICATION_JSON)
        .accept(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

}