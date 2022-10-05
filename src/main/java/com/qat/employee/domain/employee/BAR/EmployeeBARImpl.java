package com.qat.employee.domain.employee.BAR;

import com.qat.employee.domain.common.STATUS;
import com.qat.employee.domain.employee.BAR.mapper.EmployeeMapper;
import com.qat.employee.domain.employee.model.Employee;
import com.qat.employee.domain.employee.model.EmployeeRequest;
import com.qat.employee.domain.employee.model.EmployeeResponse;

import java.util.List;
import java.util.Objects;

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
  public EmployeeResponse fetchAllEmployees(EmployeeRequest request) {
    List<Employee> employees = sqlSession.selectList("fetchAllEmployee");
    return new EmployeeResponse().withData(employees).withStatus(STATUS.OPERATIONSUCCESS);
  }

  @Override
  public EmployeeResponse fetchEmployeeById(EmployeeRequest request) {
    final Employee employee = sqlSession.selectOne("fetchByIdEmployee", request.getId());
    if (Objects.nonNull(employee)) {
      return new EmployeeResponse().withData(employee).withStatus(STATUS.OPERATIONSUCCESS);
    } else {
      return new EmployeeResponse().withStatus(STATUS.NOROWSFOUNDERROR);
    }
  }

  @Override
  public EmployeeResponse insertEmployee(EmployeeRequest request) {
    Employee employee = request.getData();

    if (sqlSession.insert("insertEmployee", employee) > 0) {
      return new EmployeeResponse().withData(employee).withStatus(STATUS.OPERATIONSUCCESS);
    } else {
      return new EmployeeResponse().withStatus(STATUS.SYSTEMERROR);
    }
  }

  @Override
  public EmployeeResponse updateEmployee(EmployeeRequest request) {
    final Employee employee = request.getData();   

    if (sqlSession.update("updateEmployee",employee) == 1) {
      return new EmployeeResponse().withData(employee).withStatus(STATUS.OPERATIONSUCCESS);
    } else {
      return new EmployeeResponse().withStatus(STATUS.NOROWSUPDATEDERROR);
    }
  }

  @Override
  public EmployeeResponse deleteEmployee(EmployeeRequest request) {
    final Integer id = request.getId();

    if (sqlSession.delete("deleteEmployee",id)== 1) {
      return new EmployeeResponse().withStatus(STATUS.OPERATIONSUCCESS);
    } else {
      return new EmployeeResponse().withStatus(STATUS.NOROWSREMOVEDERROR);
    }
  }

  public SqlSession getSqlSession() {
    return sqlSession;
  }
}
