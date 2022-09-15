package com.qat.employee.domain.employee.BAR;

import com.qat.employee.domain.STATUS;
import com.qat.employee.domain.employee.BAR.mapper.EmployeeMapper;
import com.qat.employee.domain.employee.model.Employee;
import com.qat.employee.domain.employee.model.EmployeeRequest;
import com.qat.employee.domain.employee.model.EmployeeResponse;
import java.util.Objects;
import org.springframework.stereotype.Repository;


@Repository
public class EmployeeBARImpl implements EmployeeBAR {

  private final EmployeeMapper mapper;
//  private final SqlSession sqlSession;

  public EmployeeBARImpl(EmployeeMapper mapper/*, SqlSession sqlSession*/) {
    this.mapper = mapper;
//    this.sqlSession = sqlSession;
  }

  @Override
  public EmployeeResponse fetchAllEmployees(EmployeeRequest request) {
    return new EmployeeResponse().withData(mapper.fetchAll()).withStatus(STATUS.OPERATIONSUCCESS);
  }

  @Override
  public EmployeeResponse fetchEmployeeById(EmployeeRequest request) {
    final Employee employee = mapper.findById(request.getId());
    if (Objects.nonNull(employee)) {
      return new EmployeeResponse().withData(employee).withStatus(STATUS.OPERATIONSUCCESS);
    } else {
      return new EmployeeResponse().withStatus(STATUS.NOROWSFOUNDERROR);
    }
  }

  @Override
  public EmployeeResponse insertEmployee(EmployeeRequest request) {
    Employee employee = request.getData();

    if (mapper.insert(employee) > 0) {
      return  new EmployeeResponse().withData(employee).withStatus(STATUS.OPERATIONSUCCESS);
    } else {
      return new EmployeeResponse().withStatus(STATUS.SYSTEMERROR);
    }
  }

  @Override
  public EmployeeResponse updateEmployee(EmployeeRequest request) {
    final Employee employee = request.getData();
    final Integer id = request.getId();

    if (mapper.update(employee) == 1) {
      return new EmployeeResponse().withData(employee).withStatus(STATUS.OPERATIONSUCCESS);
    } else {
      return new EmployeeResponse().withStatus(STATUS.NOROWSUPDATEDERROR);
    }
  }

  @Override
  public EmployeeResponse deleteEmployee(EmployeeRequest request) {
    final Integer id = request.getId();

    if (mapper.deleteById(id) == 1) {
      return new EmployeeResponse().withStatus(STATUS.OPERATIONSUCCESS);
    } else {
      return new EmployeeResponse().withStatus(STATUS.NOROWSREMOVEDERROR);
    }
  }
}
