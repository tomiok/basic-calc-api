package com.tommy.test.component.calculator.usecase.calculation;

import java.math.BigDecimal;

/**
 * The calculator class will group the logic to parse the input and do the calculations.
 * All the methods will validate the inputs. The min number of params is 2 and the max is 3. Otherwise an
 * {@link IllegalArgumentException} will be thrown.
 */
public interface Calculator {

  /**
   * @param numbers The numbers that the user like to know the addition.
   *
   * @return The result of the addition.
   */
  BigDecimal add(final BigDecimal... numbers);

  /**
   * @param numbers The numbers that the user like to know the subtraction.
   *
   * @return The result of the subtraction.
   */
  BigDecimal subtraction(final BigDecimal... numbers);

  /**
   * @param numbers The numbers that the user like to know the multiplication.
   *
   * @return The result of the multiplication.
   */
  BigDecimal multiply(final BigDecimal... numbers);

  /**
   * @param numbers The numbers that the user like to know the division.
   *
   * @return The result of the division.
   */
  BigDecimal divide(final BigDecimal... numbers);
}
