package net.mateus.domain.employee.BAR;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class EmployeeBARImpl implements EmployeeBAR {

  private final EmployeeMapper mapper;

  @Override
  public List<Employee> fetchEmployees() {
    return mapper.findAll();
  }

  @Override
  public Employee fetchEmployeeById(Integer id) {
    return mapper.findById(id);
  }

  @Override
  public Integer insertEmployee(Employee employee) {
    return mapper.insert(employee);
  }

  @Override
  public Integer updateEmployee(Employee employee) {
    return mapper.update(employee);
  }

  @Override
  public Integer deleteEmployee(Integer id) {
    return mapper.deleteById(id);
  }
}
