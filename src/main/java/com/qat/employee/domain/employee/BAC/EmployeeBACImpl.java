package com.qat.employee.domain.employee.BAC;

import com.qat.employee.domain.Response;
import com.qat.employee.domain.STATUS;
import com.qat.employee.domain.Validator;
import com.qat.employee.domain.employee.BAR.EmployeeBAR;
import com.qat.employee.domain.employee.model.Employee;
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
    return bar.fetchEmployeeById(request);
  }

  @Override
  public EmployeeResponse insertEmployee(EmployeeRequest request) {
    Validator validator = new EmployeeValidator(request.getData());

    if (!validator.validate()) {
      return new EmployeeResponse(STATUS.VALIDATIONERROR, validator.getErrors());
    }

    return bar.insertEmployee(request);
  }

  @Override
  public EmployeeResponse updateEmployee(EmployeeRequest request) {

    Employee employee = request.getData();
    Validator validator = new EmployeeValidator(employee);

    if (!validator.validate()) {
      return  new EmployeeResponse(STATUS.VALIDATIONERROR, validator.getErrors());
    }
    return bar.updateEmployee(request);
  }

  @Override
  public EmployeeResponse deleteEmployee(EmployeeRequest request) {
    return bar.deleteEmployee(request);
  }
}
