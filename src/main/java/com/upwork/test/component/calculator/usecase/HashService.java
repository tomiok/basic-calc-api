package com.upwork.test.component.calculator.usecase;

import com.upwork.test.component.calculator.domain.CalculationType;
import java.math.BigDecimal;

public interface HashService {

  /**
   * @param type    The type of operation the perform, so we can hash the same numbers but with different results
   *                for the different operations.
   * @param numbers With the numbers the method can create the hash.
   *
   * @return The string with the hash including the operation and the numbers involved in the calc.
   */
  String getHash(final CalculationType type, final BigDecimal... numbers);
}
