package com.qat.employee.domain.employee.BAC;

import com.qat.employee.domain.Response;
import com.qat.employee.domain.Validator;
import com.qat.employee.domain.employee.BAR.EmployeeBAR;
import com.qat.employee.domain.employee.model.Employee;
import com.qat.employee.domain.STATUS;
import org.springframework.stereotype.Component;

@Component
public class EmployeeBACImpl implements EmployeeBAC {

  public EmployeeBACImpl(EmployeeBAR bar) {
    this.bar = bar;
  }

  private final EmployeeBAR bar;

  @Override
  public Response<Employee> fetchAllEmployees() {
    return bar.fetchAllEmployees();
  }

  @Override
  public Response<Employee> fetchEmployeeById(Integer id) {
    return bar.fetchEmployeeById(id);
  }

  @Override
  public Response<Employee> insertEmployee(Employee employee) {
    Validator validator = new EmployeeValidator(employee);

    if(!validator.validate()){
      return Response.of(STATUS.VALIDATIONERROR, validator.getErrors());
    }

    return  bar.insertEmployee(employee);
  }

  @Override
  public Response<Employee> updateEmployee(Employee employee) {
    Validator validator = new EmployeeValidator(employee);

    if(!validator.validate()){
      return Response.of(STATUS.VALIDATIONERROR, validator.getErrors());
    }
    return bar.updateEmployee(employee);
  }

  @Override
  public Response<Employee> deleteEmployee(Integer id) {
    return bar.deleteEmployee(id);
  }
}
