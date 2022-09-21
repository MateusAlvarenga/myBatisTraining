package com.qat.employee.domain.employee.BAR;

import com.qat.employee.domain.STATUS;
import com.qat.employee.domain.employee.BAR.mapper.EmployeeMapper;
import com.qat.employee.domain.employee.model.Employee;
import com.qat.employee.domain.employee.model.EmployeeRequest;
import com.qat.employee.domain.employee.model.EmployeeResponse;

import java.util.List;
import java.util.Objects;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class EmployeeBARImpl implements EmployeeBAR {

  private final EmployeeMapper mapper;
  private final SqlSession sqlSession;

  private final String FETCH_ALL = "fetchAll";
  private final String FETCH_BY_ID = "fetchById";
  private final String INSERT = "insert";
  private final String UPDATE = "update";
  private final String DELETE = "deleteById";
  private final String INSERT_BOOKMARK = "insertBookmark";

  public EmployeeBARImpl(EmployeeMapper mapper, SqlSession sqlSession) {
    this.mapper = mapper;
    this.sqlSession = sqlSession;
  }

  @Override
  @Transactional(readOnly = true)
  public EmployeeResponse fetchAllEmployees(EmployeeRequest request) {
    List<Employee> employees = mapper.fetchAll();
    return new EmployeeResponse().withData(employees).withStatus(STATUS.OPERATIONSUCCESS);
  }

  @Override
  @Transactional(readOnly = true)
  public EmployeeResponse fetchEmployeeById(EmployeeRequest request) {
    final Employee employee = mapper.findById(request.getId());
    if (Objects.nonNull(employee)) {
      return new EmployeeResponse().withData(employee).withStatus(STATUS.OPERATIONSUCCESS);
    } else {
      return new EmployeeResponse().withStatus(STATUS.NOROWSFOUNDERROR);
    }
  }

  @Override
  @Transactional
  public EmployeeResponse insertEmployee(EmployeeRequest request) {
    Employee employee = request.getData();

    if (mapper.insert(employee) > 0) {
      return new EmployeeResponse().withData(employee).withStatus(STATUS.OPERATIONSUCCESS);
    } else {
      return new EmployeeResponse().withStatus(STATUS.SYSTEMERROR);
    }
  }

  @Override
  @Transactional
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
  @Transactional
  public EmployeeResponse deleteEmployee(EmployeeRequest request) {
    final Integer id = request.getId();

    if (mapper.deleteById(id) == 1) {
      return new EmployeeResponse().withStatus(STATUS.OPERATIONSUCCESS);
    } else {
      return new EmployeeResponse().withStatus(STATUS.NOROWSREMOVEDERROR);
    }
  }

  @Override
  @Transactional
  public EmployeeResponse insertEmployeeBookmark(EmployeeRequest request) {

    List<Employee> employeeList = request.getDataList();

    for (Employee employee : employeeList) {
      mapper.insert(employee);
    }

    return new EmployeeResponse().withData(employeeList).withStatus(STATUS.OPERATIONSUCCESS);
  }

//    if(sqlSession.insert("insertEmployeeBookmark", employeeList) > 0) {
//      return new EmployeeResponse()
//          .withData(employeeList)
//          .withStatus(STATUS.OPERATIONSUCCESS);
//    }
//
//    return new EmployeeResponse()
//        .withStatus(STATUS.NOROWSINSERTEDERROR);
//    }
}
