package com.qat.employee.domain.employee.model;

import com.qat.employee.domain.Request;

public class EmployeeRequest extends Request<Employee, Integer> {

  public EmployeeRequest(Integer integer, Employee data) {
    super(integer, data);
  }

  public EmployeeRequest() {
    super(null, null);
  }

  public EmployeeRequest(Employee employee) {
    super(null,employee);
  }

  public EmployeeRequest(int id) {
    super(id, null);
  }
}
