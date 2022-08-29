package net.mateus.domain;

import java.util.List;

public interface IValidator {

  Boolean validate();

  public List<ValidationError> getErrors();

}
