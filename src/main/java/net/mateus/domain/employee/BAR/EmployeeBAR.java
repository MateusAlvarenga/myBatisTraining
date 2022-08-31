package net.mateus.domain.employee.BAR;

import java.util.List;
import net.mateus.domain.employee.model.Employee;

public interface EmployeeBAR {

  public List<Employee> fetchAllEmployees();

  public Employee fetchEmployeeById(Integer id);

  public Integer insertEmployee(Employee employee);

  public Integer updateEmployee(Employee employee);

  public Integer deleteEmployee(Integer id);

//  public Integer createNewEmployee(Employee employee);
}
