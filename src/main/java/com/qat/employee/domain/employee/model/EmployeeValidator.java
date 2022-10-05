package com.qat.employee.domain.employee.model;

import com.qat.employee.domain.common.ValidationContextIndicator;
import com.qat.employee.domain.common.Validator;
import java.util.List;

public class EmployeeValidator extends Validator {

  private final EmployeeRequest request;

  public EmployeeValidator(EmployeeRequest aRequest) {
    request = aRequest;
  }

  @Override
  public void checkValidState(ValidationContextIndicator validationContextIndicator) {

    switch (validationContextIndicator) {
      case INSERT:
      case UPDATE:
        validateEmployee(request.getData());
        break;
      case DELETE:
      case FETCHBYID:
        checkValidateId(request.getId());
      case FETCH:
        break;
      default:
         throw new IllegalArgumentException("ValidationContextIndicator not supported: " + validationContextIndicator);
    }


  }

  private void validateEmployee(Employee employee) {

    if(employee == null) {
      addError("Employee","Employee is required");
      return;
    }

    checkRequired("employee", employee);

    checkRequired("name", employee.getName());

    checkRequired("phone", employee.getPhone());

    checkRequired("email", employee.getEmail());

    checkRequired("branch", employee.getBranch());

    checkMaxLength("name", employee.getName(), 50);

    checkMaxLength("phone", employee.getPhone(), 20);

    checkMaxLength("email", employee.getEmail(), 50);

    checkMaxLength("branch", employee.getBranch(), 30);

    checkEmail("email", employee.getEmail());
  }

  private void checkValidateId(Integer id) {

    checkRequired("id", id);
  }
}
