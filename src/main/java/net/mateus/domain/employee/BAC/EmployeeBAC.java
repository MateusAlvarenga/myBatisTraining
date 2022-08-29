package net.mateus.domain.employee.BAC;

import java.util.List;
import net.mateus.domain.employee.BAR.Employee;

public interface EmployeeBAC {

  public List<Employee> fetchAllEmployees();

  public Employee fetchEmployeeById(Integer id);

  public Integer insertEmployee(Employee employee);

  public Integer updateEmployee(Employee employee);

  public Integer deleteEmployee(Integer id);

}
