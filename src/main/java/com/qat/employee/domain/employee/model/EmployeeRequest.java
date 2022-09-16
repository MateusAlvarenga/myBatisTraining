package com.qat.employee.domain.employee.model;

import com.qat.employee.domain.Request;

public class EmployeeRequest extends Request<Employee, Integer> {

  @Override
  public EmployeeRequest withId(Integer integer) {
    setId(integer);
    return this;
  }

  @Override
  public EmployeeRequest withData(Employee data) {
    setData(data);
    return this;
  }
}
