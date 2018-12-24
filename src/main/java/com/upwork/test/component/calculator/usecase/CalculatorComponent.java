package com.upwork.test.component.calculator.usecase;

import java.math.BigDecimal;

/**
 * The main component of all the calculation.
 * All the methods in this interface will check if the value is in cache, and return it. Otherwise, will do the
 * calculation and save the result with a proper hash in the cache.
 *
 * @see com.upwork.test.component.calculator.usecase.HashService
 * @see com.upwork.test.component.cache.CacheService
 */
public interface CalculatorComponent {

  BigDecimal calculateAddition(final BigDecimal... numbers);

  BigDecimal calculateSubtraction(final BigDecimal... numbers);

  BigDecimal calculateMultiplication(final BigDecimal... numbers);

  BigDecimal calculateDivision(final BigDecimal... numbers);
}
