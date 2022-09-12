package com.qat.employee.domain.employee.BAC;

import com.qat.employee.domain.Validator;
import com.qat.employee.domain.employee.model.Employee;

public class EmployeeValidator extends Validator {
  private final Employee employee;

  public EmployeeValidator(Employee aEmployee) {
    super();
    employee = aEmployee;
  }

  @Override
  public void checkValidState() {

    required("name", employee.getName());

    maxLength("name", employee.getName(), 50);

    required("phone", employee.getPhone());

    maxLength("phone", employee.getPhone(), 20);

    required("email", employee.getEmail());

    maxLength("email", employee.getEmail(), 50);

    email("email", employee.getEmail());

    required("branch", employee.getBranch());

    maxLength("branch", employee.getBranch(), 30);
  }
}
