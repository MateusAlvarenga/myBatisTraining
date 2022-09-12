package com.qat.employee.domain.employee.BAR;

import com.qat.employee.domain.employee.model.Employee;
import com.qat.employee.domain.Response;

public interface EmployeeBAR {

  public Response<Employee> fetchAllEmployees();

  public Response<Employee> fetchEmployeeById(Integer id);

  public Response<Employee> insertEmployee(Employee employee);

  public Response<Employee> updateEmployee(Employee employee);

  public Response<Employee> deleteEmployee(Integer id);

//  public Integer createNewEmployee(Employee employee);
}
