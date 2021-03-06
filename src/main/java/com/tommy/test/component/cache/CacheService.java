package com.tommy.test.component.cache;

import com.tommy.test.component.calculator.usecase.hash.HashService;
import java.math.BigDecimal;

/**
 * In-memory cache service. Will store the values with an in-house hash service.
 *
 * @see HashService
 */
public interface CacheService {

  /**
   * Save the value in the cache, with the proper hash
   *
   * @param hash  The hash provided.
   * @param value The result of the calculation.
   */
  void put(final String hash, final BigDecimal value);

  /**
   * Get the value given a hash.
   *
   * @param hash The hash to retrieve the result.
   *
   * @return The result stored in cache, if the key is absent, null will be returned.
   */
  BigDecimal get(final String hash);
}
