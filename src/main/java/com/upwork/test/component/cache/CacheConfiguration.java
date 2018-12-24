package com.upwork.test.component.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.math.BigDecimal;
import java.time.Duration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfiguration {

  //The values will be persisted for 50 days, enough for the POC.
  private static final int MAX_NUMBER_OF_DAYS = 50;

  @Bean
  public Cache<String, BigDecimal> cache() {
    return Caffeine
        .newBuilder()
        .expireAfterWrite(Duration.ofDays(MAX_NUMBER_OF_DAYS))
        .build();
  }

  @Bean
  public CacheService cacheService() {
    return new CacheServiceImpl(cache());
  }
}
