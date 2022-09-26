package com.qat.employee.domain.employee.BAC;

import com.qat.employee.domain.STATUS;
import com.qat.employee.domain.ValidationContextIndicator;
import com.qat.employee.domain.Validator;
import com.qat.employee.domain.employee.BAR.EmployeeBAR;
import com.qat.employee.domain.employee.model.EmployeeRequest;
import com.qat.employee.domain.employee.model.EmployeeResponse;
import org.springframework.stereotype.Component;

@Component
public class EmployeeBACImpl implements EmployeeBAC {

  public EmployeeBACImpl(EmployeeBAR bar) {
    this.bar = bar;
  }

  private final EmployeeBAR bar;

  @Override
  public EmployeeResponse fetchAllEmployees(EmployeeRequest request) {
    return bar.fetchAllEmployees(request);
  }

  @Override
  public EmployeeResponse fetchEmployeeById(EmployeeRequest request) {
    EmployeeValidator validator = new EmployeeValidator(request);

    if (validator.validate(ValidationContextIndicator.FETCHBYID)) {
      return bar.fetchEmployeeById(request);
    } else {
      return new EmployeeResponse()
          .withStatus(STATUS.VALIDATIONERROR)
          .withMessages(validator.getErrors());
    }
  }

  @Override
  public EmployeeResponse insertEmployee(EmployeeRequest request) {
    Validator validator = new EmployeeValidator(request);

    if (!validator.validate(ValidationContextIndicator.INSERT)) {
      return new EmployeeResponse()
          .withStatus(STATUS.VALIDATIONERROR)
          .withMessages(validator.getErrors());
    }

    return bar.insertEmployee(request);
  }

  @Override
  public EmployeeResponse updateEmployee(EmployeeRequest request) {

    Validator validator = new EmployeeValidator(request);

    if (!validator.validate(ValidationContextIndicator.UPDATE)) {
      return new EmployeeResponse()
          .withStatus(STATUS.VALIDATIONERROR)
          .withMessages(validator.getErrors());
    }
    return bar.updateEmployee(request);
  }

  @Override
  public EmployeeResponse deleteEmployee(EmployeeRequest request) {
    EmployeeValidator validator = new EmployeeValidator(request);

    if (validator.validate(ValidationContextIndicator.DELETE)) {
      return bar.deleteEmployee(request);
    } else {
      return new EmployeeResponse()
          .withStatus(STATUS.VALIDATIONERROR)
          .withMessages(validator.getErrors());
    }
  }

@Override
public EmployeeResponse insertEmployeeBookmark(EmployeeRequest request) {

  Validator validator = new EmployeeValidator(request);

  if (!validator.validate(ValidationContextIndicator.INSERTBOOKMARK)) {
    return new EmployeeResponse()
        .withStatus(STATUS.VALIDATIONERROR)
        .withMessages(validator.getErrors());
  }

  return bar.insertEmployeeBookmark(request);

}
}
