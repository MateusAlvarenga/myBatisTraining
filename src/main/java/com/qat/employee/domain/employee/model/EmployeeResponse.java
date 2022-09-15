package com.qat.employee.domain.employee.model;

import com.qat.employee.domain.Response;
import com.qat.employee.domain.STATUS;
import com.qat.employee.domain.ValidationError;
import java.util.List;

public class EmployeeResponse extends Response<Employee> {

  @Override
  public EmployeeResponse withData(List<Employee> data) {
    setData(data);
    return this;
  }

  @Override
  public EmployeeResponse withData(Employee data) {
    setData(List.of(data));
    return this;
  }

  @Override
  public EmployeeResponse withStatus(STATUS status) {
    setStatus(status);
    return this;
  }

  @Override
  public EmployeeResponse withMessages(List<ValidationError> messages) {
    setMessages(messages);
    return this;
  }

  public static EmployeeResponse create() {
    return new EmployeeResponse();
  }

}
