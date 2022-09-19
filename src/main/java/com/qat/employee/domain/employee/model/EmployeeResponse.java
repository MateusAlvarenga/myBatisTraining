package com.qat.employee.domain.employee.model;

import com.qat.employee.domain.Response;

public class EmployeeResponse extends Response<Employee, EmployeeResponse> {

  public static EmployeeResponse create() {
    return new EmployeeResponse();
  }

}
