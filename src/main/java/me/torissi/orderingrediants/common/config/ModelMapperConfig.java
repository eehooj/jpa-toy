package me.torissi.orderingrediants.common.config;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

  /**
   * ModelMapper Bean 등록 -> kr.nextculture.utils 의존성 필요
   */
  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull()).setMatchingStrategy(
        MatchingStrategies.STRICT);

    return modelMapper;
  }

}
