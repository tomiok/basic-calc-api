package com.tommy.test.component.calculator.usecase.calculation;

import com.tommy.test.component.calculator.domain.CalculationType;
import com.tommy.test.component.cache.CacheService;
import com.tommy.test.component.calculator.usecase.hash.HashService;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.Function;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculatorComponentImpl implements CalculatorService {

  private static final Logger log = LoggerFactory.getLogger(CalculatorService.class);

  private final CacheService cacheService;

  private final Calculator calculator;

  private final HashService hashService;

  CalculatorComponentImpl(final CacheService cacheService,
                          final Calculator calculator,
                          final HashService hashService) {
    Validate.notNull(cacheService, "The cacheService cannot be null.");
    Validate.notNull(calculator, "The calculator cannot be null.");
    Validate.notNull(hashService, "The hashService cannot be null.");
    this.cacheService = cacheService;
    this.calculator = calculator;
    this.hashService = hashService;
  }

  @Override
  public BigDecimal calculateAddition(final BigDecimal... numbers) {
    return calculateAndSave(CalculationType.ADDITION, calculator::add, numbers);
  }

  @Override
  public BigDecimal calculateSubtraction(final BigDecimal... numbers) {
    return calculateAndSave(CalculationType.SUBTRACTION, calculator::subtraction, numbers);
  }

  @Override
  public BigDecimal calculateMultiplication(final BigDecimal... numbers) {
    return calculateAndSave(CalculationType.MULTIPLICATION, calculator::multiply, numbers);
  }

  @Override
  public BigDecimal calculateDivision(final BigDecimal... numbers) {
    return calculateAndSave(CalculationType.DIVISION, calculator::divide, numbers);
  }

  /**
   * The method will be in charge to manage all the calc operations, given the type, so the hash function
   * can generate the proper key for the cache and the function which actually will calculate the value, also
   * passed as a param.
   *
   * @param type     The type of calculation. {@link CalculationType}.
   * @param function The lambda expression or method reference to calculate the value.
   * @param numbers  The numbers that the client want to put in the calculation.
   *
   * @return The result of the calculation.
   */
  private BigDecimal calculateAndSave(final CalculationType type, final Function<BigDecimal[], BigDecimal> function,
                                      final BigDecimal... numbers) {
    String hash = getHash(type, numbers);
    Optional<BigDecimal> value = Optional.ofNullable(cacheService.get(hash));
    return value.orElseGet(() -> calculateAndSave(hash, function, numbers));
  }

  private BigDecimal calculateAndSave(final String hash, final Function<BigDecimal[], BigDecimal> function,
                                      final BigDecimal... numbers) {
    BigDecimal result = function.apply(numbers);
    cacheService.put(hash, result);
    log.info("Saved value {}, with hash {}.", result, hash);
    return result;
  }

  private String getHash(final CalculationType type, final BigDecimal... numbers) {
    return hashService.getHash(type, numbers);
  }
}
