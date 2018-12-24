package com.tommy.test.component.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalControllerAdvice {

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseBody
  public ResponseEntity<?> handleIllegalArgumentException(final Exception e) {
    return toResponseEntity(HttpStatus.BAD_REQUEST, e.getMessage());
  }

  @ExceptionHandler(NullPointerException.class)
  @ResponseBody
  public ResponseEntity<?> handleNullPointerException(final Exception e) {
    return toResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
  }

  @ExceptionHandler(NumberFormatException.class)
  @ResponseBody
  public ResponseEntity<?> handleNumberFormatException(final Exception e) {
    return toResponseEntity(HttpStatus.BAD_REQUEST, e.getMessage());
  }

  @ExceptionHandler(ArithmeticException.class)
  @ResponseBody
  public ResponseEntity<?> handleArithmeticException(final Exception e) {
    return toResponseEntity(HttpStatus.BAD_REQUEST, e.getMessage());
  }

  private ResponseEntity<?> toResponseEntity(final HttpStatus httpStatus, final String message) {
    return ResponseEntity.status(httpStatus).body(new HttpApiError(httpStatus, message));
  }
}
