package com.upwork.test.component.web;

import java.math.BigDecimal;

/**
 * DTO to wrap the HTTP response in a better and more readable way.
 */
class CalculationHttpResponse {

  private final BigDecimal result;

  CalculationHttpResponse(final BigDecimal result) {
    this.result = result;
  }

  public BigDecimal getResult() {
    return result;
  }
}
