package com.tommy.test.component.calculator.usecase.calculation;

import com.tommy.test.component.cache.CacheConfiguration;
import com.tommy.test.component.cache.CacheService;
import com.tommy.test.component.calculator.usecase.hash.HashService;
import com.tommy.test.component.calculator.usecase.hash.HashServiceConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ CacheConfiguration.class, HashServiceConfiguration.class })
public class CalculatorConfiguration {

  @Bean
  public Calculator calculator() {
    return new CalculatorImpl();
  }

  @Bean
  public CalculatorService calculatorComponent(
      final HashService hashService,
      final CacheService cacheService) {
    return new CalculatorComponentImpl(cacheService, calculator(), hashService);
  }
}
