package com.tommy.test.component.calculator.usecase.hash;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HashServiceConfiguration {

  @Bean
  public HashService hashService() {
    return new HashServiceImpl();
  }
}
