package net.mateus.domain;

import java.util.ArrayList;
import java.util.List;
import net.mateus.domain.ValidationError;

public abstract class Validator {

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

  public abstract boolean validate();

}
