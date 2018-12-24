package com.upwork.test.component.web;

import org.springframework.http.HttpStatus;

public class HttpApiError {

  private HttpStatus status;

  private String message;

  HttpApiError(final HttpStatus status, final String message) {
    this.status = status;
    this.message = message;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(final HttpStatus status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(final String message) {
    this.message = message;
  }
}
