package net.mateus.domain.employee.BAR;

import net.mateus.domain.Response;
import net.mateus.domain.employee.model.Employee;

public interface EmployeeBAR {

  public Response<Employee> fetchAllEmployees();

  public Response<Employee> fetchEmployeeById(Integer id);

  public Response<Employee> insertEmployee(Employee employee);

  public Response<Employee> updateEmployee(Employee employee);

  public Response<Employee> deleteEmployee(Integer id);

//  public Integer createNewEmployee(Employee employee);
}
