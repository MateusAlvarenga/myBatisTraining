package com.qat.employee.domain.employee.BAC;

import com.qat.employee.domain.Response;
import com.qat.employee.domain.employee.model.Employee;

public interface EmployeeBAC {

  public Response<Employee> fetchAllEmployees();

  public Response<Employee> fetchEmployeeById(Integer id);

  public Response<Employee> insertEmployee(Employee employee);

  public Response<Employee> updateEmployee(Employee employee);

  public Response<Employee> deleteEmployee(Integer id);

}
