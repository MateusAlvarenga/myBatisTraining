package com.qat.employee.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public abstract class Validator {

  public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
      Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

  public abstract void checkValidState();

  public Boolean validate() {
    checkValidState();
    return getErrors().isEmpty();
  }

  private List<ValidationError> errors;

  public Validator() {
    this.errors = new ArrayList<>();
  }

  public List<ValidationError> getErrors() {
    return errors;
  }

  public void addError(String field, String message) {
    errors.add(ValidationError.of(field, message));
  }

  public void checkRequired(String field, String value) {
    if (value == null || value.isEmpty()) {
      addError(field, field + " is required");
    }
  }

  public void checkMaxLength(String field, String value, int maxLength) {
    if (value != null && value.length() > maxLength) {
      addError(field, field + " must be less than " + maxLength + " characters");
    }
  }

  public void checkEmail(String field, String value) {
    if (value != null && !VALID_EMAIL_ADDRESS_REGEX.matcher(value).find()) {
      addError(field, field + " is not valid");
    }
  }

}
