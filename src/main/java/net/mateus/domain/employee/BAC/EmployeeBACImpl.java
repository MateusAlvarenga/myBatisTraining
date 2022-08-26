package net.mateus.domain.employee.BAC;

import java.util.List;
import lombok.AllArgsConstructor;
import net.mateus.domain.employee.BAR.Employee;
import net.mateus.domain.employee.BAR.EmployeeBAR;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@AllArgsConstructor
public class EmployeeBACImpl implements EmployeeBAC {

  private final EmployeeBAR bar;


  @Override
  public List<Employee> fetchEmployees() {
    return bar.fetchEmployees();
  }

  @Override
  public Employee fetchEmployeeById(Integer id) {
    return bar.fetchEmployeeById(id);
  }

  @Override
  public Integer insertEmployee(Employee employee) {
    return bar.insertEmployee(employee);
  }

  @Override
  public Integer updateEmployee(Employee employee) {
    return bar.updateEmployee(employee);
  }

  @Override
  public Integer deleteEmployee(Integer id) {
    return bar.deleteEmployee(id);
  }
}
