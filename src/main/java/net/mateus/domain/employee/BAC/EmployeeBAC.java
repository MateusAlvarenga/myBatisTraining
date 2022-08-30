package net.mateus.domain.employee.BAC;

import net.mateus.domain.Response;
import net.mateus.domain.employee.model.Employee;

public interface EmployeeBAC {

  public Response<Employee> fetchAllEmployees();

  public Response<Employee> fetchEmployeeById(Integer id);

  public Response<Employee> insertEmployee(Employee employee);

  public Response<Employee> updateEmployee(Employee employee);

  public Integer deleteEmployee(Integer id);

}
