package net.mateus.domain.employee.BAR;

import java.util.List;
import net.mateus.domain.employee.model.Employee;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


@Repository
public class EmployeeBARImpl implements EmployeeBAR {

  private final EmployeeMapper mapper;
  private final SqlSession sqlSession;

  public EmployeeBARImpl(EmployeeMapper mapper, SqlSession sqlSession) {
    this.mapper = mapper;
    this.sqlSession = sqlSession;
  }

  @Override
  public List<Employee> fetchAllEmployees() {
    return mapper.fetchAll();
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
