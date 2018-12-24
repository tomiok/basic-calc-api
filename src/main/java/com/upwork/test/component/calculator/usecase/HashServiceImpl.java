package com.upwork.test.component.calculator.usecase;

import static java.lang.String.valueOf;
import static java.util.stream.Stream.of;

import com.upwork.test.component.calculator.domain.CalculationType;
import java.math.BigDecimal;
import org.apache.commons.lang3.Validate;

public class HashServiceImpl implements HashService {

  /**
   * Default package-access level constructor.
   */
  HashServiceImpl() {}

  private static String divisionHash(final BigDecimal... numbers) {
    Validate.isTrue(numbers.length == 2, "Cannot divide due to wrong number of params, "
                                         + "was %s and %s are expected", numbers.length, 2);
    return String.format("%s/%s", numbers[0], numbers[1]);
  }

  private static String getSumOfHashCodes(final CalculationType type, final BigDecimal... numbers) {
    return type.name().concat(valueOf(of(numbers).mapToInt(BigDecimal::hashCode).sum()));
  }

  @Override
  public String getHash(final CalculationType type, final BigDecimal... numbers) {
    Validate.notNull(type, "The type cannot be null.");
    if (CalculationType.DIVISION.equals(type)) {
      return divisionHash(numbers);
    }

    return getSumOfHashCodes(type, numbers);
  }
}
