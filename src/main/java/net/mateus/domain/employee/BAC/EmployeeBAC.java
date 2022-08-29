package net.mateus.domain.employee.BAC;

import java.util.List;
import net.mateus.domain.Response;
import net.mateus.domain.employee.model.Employee;

public interface EmployeeBAC {

  public List<Employee> fetchAllEmployees();

  public Employee fetchEmployeeById(Integer id);

  public Response<Employee> insertEmployee(Employee employee);

  public Response<Employee> updateEmployee(Employee employee);

  public Integer deleteEmployee(Integer id);

}
