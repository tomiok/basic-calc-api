package com.upwork.test.component.calculator.usecase;

import com.upwork.test.component.cache.CacheConfiguration;
import com.upwork.test.component.cache.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CacheConfiguration.class)
public class CalculatorConfiguration {

  @Autowired
  private CacheService cacheService;

  @Bean
  public Calculator calculator() {
    return new CalculatorImpl();
  }

  @Bean
  public HashService hashService() {
    return new HashServiceImpl();
  }

  @Bean
  public CalculatorComponent calculatorComponent() {
    return new CalculatorComponentImpl(cacheService, calculator(), hashService());
  }
}
