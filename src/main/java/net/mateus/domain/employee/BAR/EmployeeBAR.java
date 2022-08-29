package net.mateus.domain.employee.BAR;

import java.util.List;

public interface EmployeeBAR {

  public List<Employee> fetchAllEmployees();

  public Employee fetchEmployeeById(Integer id);

  public Integer insertEmployee(Employee employee);

  public Integer updateEmployee(Employee employee);

  public Integer deleteEmployee(Integer id);

}
