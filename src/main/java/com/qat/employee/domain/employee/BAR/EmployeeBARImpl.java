package com.qat.employee.domain.employee.BAR;

import com.qat.employee.domain.employee.BAR.mapper.EmployeeMapper;
import com.qat.employee.domain.employee.model.Employee;
import java.util.Objects;
import com.qat.employee.domain.Response;
import com.qat.employee.domain.STATUS;
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
  public Response<Employee> fetchAllEmployees() {
    return Response.of(mapper.fetchAll(), STATUS.OPERATIONSUCCESS);
  }

  @Override
  public Response<Employee> fetchEmployeeById(Integer id) {
    final Employee employee = mapper.findById(id);
    if(Objects.nonNull(employee)){
      return Response.of(employee, STATUS.OPERATIONSUCCESS);
    }else {
      return Response.of(STATUS.NOROWSFOUNDERROR);
    }
  }

  @Override
  public Response<Employee> insertEmployee(Employee employee) {
    if(mapper.insert(employee) > 0){
      return Response.of(employee, STATUS.OPERATIONSUCCESS);
    }else {
      return Response.of(STATUS.PERSISTENCEERROR);
    }
  }

  @Override
  public Response<Employee> updateEmployee(Employee employee) {
     if(mapper.update(employee) == 1){
       return Response.of(employee, STATUS.OPERATIONSUCCESS);
     }else {
       return Response.of(STATUS.NOROWSUPDATEDERROR);
     }
  }

  @Override
  public Response<Employee> deleteEmployee(Integer id) {
    if(mapper.deleteById(id) == 1){
      return Response.of(STATUS.OPERATIONSUCCESS);
    }else {
      return Response.of(STATUS.NOROWSREMOVEDERROR);
    }
  }
}
