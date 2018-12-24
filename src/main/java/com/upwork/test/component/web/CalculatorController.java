package com.upwork.test.component.web;

import static org.springframework.http.ResponseEntity.ok;

import com.upwork.test.component.calculator.usecase.CalculatorComponent;
import java.math.BigDecimal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MVC Controller class, will be responsible to handle HTTP requests.
 * Since {@link PathVariable} cannot be optional, need to write methods for 2 or 3 arguments. If the number of
 * allowed parameters increase, the methods will move to a more flexible solution, like query params
 *
 * @see org.springframework.web.bind.annotation.RequestParam
 */
@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

  private final CalculatorComponent calculatorComponent;

  public CalculatorController(final CalculatorComponent calculatorComponent) {
    this.calculatorComponent = calculatorComponent;
  }

  @GetMapping(value = "/add/{num1}/{num2}/{num3}")
  public ResponseEntity<CalculationHttpResponse> doAddition(@PathVariable("num1") final BigDecimal num1,
                                                            @PathVariable("num2") final BigDecimal num2,
                                                            @PathVariable("num3") final BigDecimal num3) {
    return ok(new CalculationHttpResponse(calculatorComponent.calculateAddition(num1, num2, num3)));
  }

  @GetMapping(value = "/add/{num1}/{num2}")
  public ResponseEntity<CalculationHttpResponse> doAddition(@PathVariable("num1") final BigDecimal num1,
                                                            @PathVariable("num2") final BigDecimal num2) {
    return ok(new CalculationHttpResponse(calculatorComponent.calculateAddition(num1, num2)));
  }

  @GetMapping("/subtract/{num1}/{num2}/{num3}")
  public ResponseEntity<CalculationHttpResponse> doSubtraction(@PathVariable("num1") final BigDecimal num1,
                                                               @PathVariable("num2") final BigDecimal num2,
                                                               @PathVariable(value = "num3", required = false) final
                                                               BigDecimal num3) {
    return ok(new CalculationHttpResponse(calculatorComponent.calculateSubtraction(num1, num2, num3)));
  }

  @GetMapping("/subtract/{num1}/{num2}")
  public ResponseEntity<CalculationHttpResponse> doSubtraction(@PathVariable("num1") final BigDecimal num1,
                                                               @PathVariable("num2") final BigDecimal num2) {
    return ok(new CalculationHttpResponse(calculatorComponent.calculateSubtraction(num1, num2)));
  }

  @GetMapping("/multiply/{num1}/{num2}/{num3}")
  public ResponseEntity<CalculationHttpResponse> doMultiplication(@PathVariable("num1") final BigDecimal num1,
                                                                  @PathVariable("num2") final BigDecimal num2,
                                                                  @PathVariable(value = "num3", required = false)
                                                                    final BigDecimal num3) {
    return ok(new CalculationHttpResponse(calculatorComponent.calculateMultiplication(num1, num2, num3)));
  }

  @GetMapping("/multiply/{num1}/{num2}")
  public ResponseEntity<CalculationHttpResponse> doMultiplication(@PathVariable("num1") final BigDecimal num1,
                                                                  @PathVariable("num2") final BigDecimal num2) {
    return ok(new CalculationHttpResponse(calculatorComponent.calculateMultiplication(num1, num2)));
  }

  @GetMapping("/divide/{num1}/{num2}")
  public ResponseEntity<CalculationHttpResponse> doDivision(@PathVariable("num1") final BigDecimal num1,
                                                            @PathVariable("num2") final BigDecimal num2) {
    return ok(new CalculationHttpResponse(calculatorComponent.calculateDivision(num1, num2)));
  }
}
