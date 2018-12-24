package com.tommy.test.component.cache;

import com.github.benmanes.caffeine.cache.Cache;
import java.math.BigDecimal;
import org.apache.commons.lang3.Validate;

public class CacheServiceImpl implements CacheService {

  private final Cache<String, BigDecimal> caffeine;

  CacheServiceImpl(final Cache<String, BigDecimal> caffeine) {
    Validate.notNull(caffeine, "caffeine cannot be null.");
    this.caffeine = caffeine;
  }

  @Override
  public void put(final String hash, final BigDecimal value) {
    caffeine.put(hash, value);
  }

  @Override
  public BigDecimal get(final String hash) {
    return caffeine.getIfPresent(hash);
  }
}
