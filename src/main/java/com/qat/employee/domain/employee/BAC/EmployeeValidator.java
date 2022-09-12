package com.qat.employee.domain.employee.BAC;

import com.qat.employee.domain.Validator;
import com.qat.employee.domain.employee.model.Employee;

public class EmployeeValidator extends Validator {
  private final Employee employee;

  public EmployeeValidator(Employee aEmployee) {
    employee = aEmployee;
  }

  @Override
  public void checkValidState() {

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
}
