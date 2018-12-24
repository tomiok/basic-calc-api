package com.upwork.test.component.calculator.usecase;

import static org.assertj.core.api.Assertions.assertThat;

import com.upwork.test.component.calculator.domain.CalculationType;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class HashServiceImplTest {

  @Rule
  public ExpectedException rule = ExpectedException.none();

  private HashService hashService;

  @Before
  public void setUp() {
    hashService = new HashServiceImpl();
  }

  @Test
  public void getHashForDivision() {
    String hash = hashService.getHash(CalculationType.DIVISION, new BigDecimal(10), new BigDecimal(2));
    assertThat(hash).isNotNull();
    assertThat(hash).isNotBlank();
    assertThat(hash).isEqualTo("10/2");
    assertThat(hash).isNotEqualTo("2/10");
  }

  @Test
  public void getHashForAddition() {
    String hash = hashService.getHash(CalculationType.ADDITION, BigDecimal.TEN, BigDecimal.ONE);
    assertThat(hash).isEqualTo(CalculationType.ADDITION.name()
        .concat(String.valueOf(BigDecimal.TEN.hashCode() + BigDecimal.ONE.hashCode())));
    assertThat(hash).isEqualTo(hashService.getHash(CalculationType.ADDITION, BigDecimal.ONE, BigDecimal.TEN));
    assertThat(hash)
        .isNotEqualTo(hashService.getHash(CalculationType.ADDITION, BigDecimal.ONE, BigDecimal.TEN, BigDecimal.ONE));
    assertThat(hash).isNotEqualTo(hashService.getHash(CalculationType.MULTIPLICATION, BigDecimal.TEN, BigDecimal.ONE));
  }

  @Test
  public void shouldFailGivenNullCalcType() {
    rule.expect(NullPointerException.class);
    rule.expectMessage("The type cannot be null.");
    hashService.getHash(null, BigDecimal.ONE, BigDecimal.ONE);
  }

  @Test
  public void shouldFailGivenLessNumberOfParams_PerformingDivision() {
    rule.expect(IllegalArgumentException.class);
    rule.expectMessage("Cannot divide due to wrong number of params, was 1 and 2 are expected");
    hashService.getHash(CalculationType.DIVISION, BigDecimal.ONE);
  }

  @Test
  public void shouldFailGivenMoreNumberOfParams_PerformingDivision() {
    rule.expect(IllegalArgumentException.class);
    rule.expectMessage("Cannot divide due to wrong number of params, was 3 and 2 are expected");
    hashService.getHash(CalculationType.DIVISION, BigDecimal.ONE, BigDecimal.TEN, BigDecimal.ZERO);
  }
}