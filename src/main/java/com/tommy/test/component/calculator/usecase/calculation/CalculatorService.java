package com.tommy.test.component.calculator.usecase.calculation;

import com.tommy.test.component.cache.CacheService;
import com.tommy.test.component.calculator.usecase.hash.HashService;
import java.math.BigDecimal;

/**
 * The main component of all the calculation.
 * All the methods in this interface will check if the value is in cache, and return it. Otherwise, will do the
 * calculation and save the result with a proper hash in the cache.
 *
 * @see HashService
 * @see CacheService
 */
public interface CalculatorService {

  BigDecimal calculateAddition(final BigDecimal... numbers);

  BigDecimal calculateSubtraction(final BigDecimal... numbers);

  BigDecimal calculateMultiplication(final BigDecimal... numbers);

  BigDecimal calculateDivision(final BigDecimal... numbers);
}
