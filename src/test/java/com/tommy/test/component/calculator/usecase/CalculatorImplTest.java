package com.tommy.test.component.calculator.usecase;

import static org.assertj.core.api.Assertions.assertThat;

import com.tommy.test.component.calculator.usecase.calculation.Calculator;
import com.tommy.test.component.calculator.usecase.calculation.CalculatorImpl;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CalculatorImplTest {

  @Rule
  public ExpectedException rule = ExpectedException.none();

  private Calculator calculator;

  @Before
  public void setUp() {
    calculator = new CalculatorImpl();
  }

  @Test
  public void add() {
    BigDecimal result = calculator.add(BigDecimal.ONE, BigDecimal.ONE);
    assertThat(result).isEqualTo(new BigDecimal(2));
  }

  @Test
  public void testAddition() {
    BigDecimal result = calculator.add(new BigDecimal(99), new BigDecimal(140));
    assertThat(result).isEqualTo(new BigDecimal(239));
  }

  @Test
  public void subtraction() {
    BigDecimal result = calculator.subtraction(BigDecimal.ONE, BigDecimal.ONE);
    assertThat(result).isEqualTo(BigDecimal.ZERO);
  }

  @Test
  public void testSubtraction() {
    BigDecimal result = calculator.subtraction(new BigDecimal(40), new BigDecimal(10));
    assertThat(result).isEqualTo(new BigDecimal(30));
  }

  @Test
  public void multiply() {
    BigDecimal result = calculator.multiply(BigDecimal.ONE, BigDecimal.ONE);
    assertThat(result).isEqualTo(BigDecimal.ONE);
  }

  @Test
  public void testMultiply() {
    BigDecimal result = calculator.multiply(new BigDecimal(1), new BigDecimal(2), new BigDecimal(3));
    assertThat(result).isEqualTo(new BigDecimal(6));
  }

  @Test
  public void divide() {
    BigDecimal result = calculator.divide(new BigDecimal(6), new BigDecimal(3));
    assertThat(result).isEqualTo(new BigDecimal(2));
  }

  @Test
  public void testSumWithFloat() {
    BigDecimal result = calculator.add(new BigDecimal("2.95"), new BigDecimal("817.34"));
    assertThat(result).isEqualTo(new BigDecimal("820.29"));
  }

  @Test
  public void shouldFailWithMoreParameters() {
    rule.expect(IllegalArgumentException.class);
    rule.expectMessage("The length should be between 1 and 3");
    BigDecimal result = calculator.add(new BigDecimal("2.95"), new BigDecimal("817.34"), BigDecimal.ZERO,
        BigDecimal.TEN);
    assertThat(result).isEqualTo(new BigDecimal("820.29"));
  }
}