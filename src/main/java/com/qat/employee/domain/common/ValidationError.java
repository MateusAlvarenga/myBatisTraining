package com.qat.employee.domain.common;

import java.io.Serializable;

public class ValidationError implements Serializable {

private static final long serialVersionUID = 1L;
private final String field;
  private final String message;

  private ValidationError(String field, String message) {
    this.field = field;
    this.message = message;
  }

  public static ValidationError of(String field, String message) {
    return new ValidationError(field, message);
  }

  public String getField() {
    return field;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("ValidationError{");
    sb.append("field='").append(field).append('\'');
    sb.append(", message='").append(message).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
