package com.upwork.test.component.web;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.upwork.test.TestApplication;
import com.upwork.test.component.calculator.usecase.CalculatorComponent;
import java.math.BigDecimal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = TestApplication.class)
@AutoConfigureMockMvc
public class CalculatorControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private CalculatorComponent calculatorComponent;

  private static BigDecimal bigDecimalFactory(final String num) {
    return new BigDecimal(num);
  }

  @Test
  public void doAddition_WithTwoValues() throws Exception {

    mockMvc.perform(get("/api/calculator/add/{a}/{b}", BigDecimal.TEN, BigDecimal.ONE)
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.result", is(11)));
  }

  @Test
  public void doAddition_WithThreeValues() throws Exception {

    mockMvc.perform(get("/api/calculator/add/{a}/{b}/{c}", bigDecimalFactory("95.2"),
        bigDecimalFactory("11.33"), bigDecimalFactory("130"))
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.result", is(236.53)));
  }

  @Test
  public void doSubtraction_WithTwoValues() throws Exception {

    mockMvc.perform(get("/api/calculator/subtract/{a}/{b}", bigDecimalFactory("10000000"),
        bigDecimalFactory("1"))
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.result", is(9999999)));
  }

  @Test
  public void doSubtraction_WithThreeValues() throws Exception {

    mockMvc.perform(get("/api/calculator/subtract/{a}/{b}/{c}", bigDecimalFactory("10000000"),
        bigDecimalFactory("0.5"), bigDecimalFactory("0.5"))
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.result", is(9999999.0)));
  }

  @Test
  public void doMultiplication_WithTwoValues() throws Exception {

    mockMvc.perform(get("/api/calculator/multiply/{a}/{b}", bigDecimalFactory("18"),
        bigDecimalFactory("6"))
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.result", is(108)));
  }

  @Test
  public void doMultiplication_WithThreeValues() throws Exception {
    mockMvc.perform(get("/api/calculator/multiply/{a}/{b}/{c}", bigDecimalFactory("23"),
        bigDecimalFactory("9"), bigDecimalFactory("7"))
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.result", is(1449)));
  }

  @Test
  public void doDivision() throws Exception {
    mockMvc.perform(get("/api/calculator/divide/{a}/{b}", bigDecimalFactory("900"),
        bigDecimalFactory("9"))
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.result", is(100)));
  }

  @Test
  public void shouldFailDividingByZero() throws Exception {
    mockMvc.perform(get("/api/calculator/divide/{a}/{b}", bigDecimalFactory("900"),
        bigDecimalFactory("0"))
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.status", is("BAD_REQUEST")))
        .andExpect(jsonPath("$.message", is("Division by zero")));
  }

  @Test
  public void shouldFailWithBadRequest() throws Exception {
    mockMvc.perform(get("/api/calculator/divide/{a}/{b}", bigDecimalFactory("900"), "nine")
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.status", is("BAD_REQUEST")));
  }

  @Test
  public void shouldFailWithFourParams() throws Exception {
    mockMvc.perform(get("/api/calculator/multiply/{a}/{b}/{c}/{d}", bigDecimalFactory("23"),
        bigDecimalFactory("9"), bigDecimalFactory("7"), bigDecimalFactory("99"))
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isNotFound());
  }
}