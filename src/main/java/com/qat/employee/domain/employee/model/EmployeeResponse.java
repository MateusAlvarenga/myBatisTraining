package com.qat.employee.domain.employee.model;

import com.qat.employee.domain.Response;
import com.qat.employee.domain.STATUS;
import com.qat.employee.domain.ValidationError;
import java.util.List;

public class EmployeeResponse extends Response<Employee> {

  public EmployeeResponse(List<Employee> data, STATUS aStatus, List<ValidationError> errors) {
    super(data, aStatus, errors);
  }


  public EmployeeResponse(Exception exception) {
    super(EMPTY_LIST, STATUS.EXCEPTIONERROR, EMPTY_LIST);
  }

  public EmployeeResponse(STATUS validationerror, List<ValidationError> errors) {
    super(EMPTY_LIST, validationerror, errors);
  }

  public EmployeeResponse(Employee employee, STATUS status) {
    super(List.of(employee), status, EMPTY_LIST);
  }

  public EmployeeResponse(STATUS status) {
    super(EMPTY_LIST, status, EMPTY_LIST);
  }

  public EmployeeResponse(List<Employee> employees, STATUS status) {
    super(employees, status, EMPTY_LIST);
  }


}
