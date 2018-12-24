package com.upwork.test.component.calculator.usecase;

import static java.util.stream.Stream.of;

import java.math.BigDecimal;
import org.apache.commons.lang3.Validate;

public class CalculatorImpl implements Calculator {

  private static final int MIN_NUMBERS_ALLOWED = 1;

  private static final int MAX_NUMBERS_ALLOWED = 3;
  private static final int MAX_NUMBERS_ALLOWED_FOR_DIVISION = 2;

  private static void validateExpression(final int maxNumbersAllowed, final BigDecimal... numbers) {
    Validate.isTrue(numbers.length <= maxNumbersAllowed
                    && numbers.length > MIN_NUMBERS_ALLOWED, "The length should be between " + MIN_NUMBERS_ALLOWED
                                                             + " and " + maxNumbersAllowed);
  }

  private static BigDecimal doAdd(final BigDecimal... numbers) {
    return of(numbers)
        .reduce(BigDecimal::add)
        .orElseThrow(IllegalArgumentException::new);
  }

  private static BigDecimal doSub(final BigDecimal... numbers) {
    return of(numbers)
        .reduce(BigDecimal::subtract)
        .orElseThrow(IllegalArgumentException::new);
  }

  private static BigDecimal doMultiply(final BigDecimal... numbers) {
    return of(numbers)
        .reduce(BigDecimal::multiply)
        .orElseThrow(IllegalArgumentException::new);
  }

  private static BigDecimal doDiv(final BigDecimal... numbers) {
    return of(numbers)
        .reduce(BigDecimal::divide)
        .orElseThrow(IllegalArgumentException::new);
  }

  @Override
  public BigDecimal add(final BigDecimal... numbers) {
    validateExpression(MAX_NUMBERS_ALLOWED, numbers);
    return doAdd(numbers);
  }

  @Override
  public BigDecimal subtraction(final BigDecimal... numbers) {
    validateExpression(MAX_NUMBERS_ALLOWED, numbers);
    return doSub(numbers);
  }

  @Override
  public BigDecimal multiply(final BigDecimal... numbers) {
    validateExpression(MAX_NUMBERS_ALLOWED, numbers);
    return doMultiply(numbers);
  }

  @Override
  public BigDecimal divide(final BigDecimal... numbers) {
    validateExpression(MAX_NUMBERS_ALLOWED_FOR_DIVISION, numbers);
    return doDiv(numbers);
  }
}
