package com.qat.employee.domain.employee.BAR;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.qat.employee.domain.common.STATUS;
import com.qat.employee.domain.employee.model.EmployeeBuilder;
import com.qat.employee.domain.employee.model.EmployeeRequest;
import com.qat.employee.domain.employee.model.EmployeeResponse;
import java.util.List;
import com.qat.employee.domain.employee.BAR.mapper.EmployeeMapper;
import com.qat.employee.domain.employee.model.Employee;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmployeebarTest {

  @Mock
  EmployeeMapper employeeMapper;

  @Mock
  SqlSession sqlSession;

  @InjectMocks
  EmployeeBARImpl employeeBAR;
 
  @Test
  void testFetchEmployees() {
    final List<Employee> employeesExpected = givenEmployees();
    final EmployeeResponse responseExpected = new EmployeeResponse()
        .withData(employeesExpected)
        .withStatus(STATUS.OPERATIONSUCCESS);

    EmployeeRequest givenRequest = new EmployeeRequest();
    when(employeeMapper.fetchAll()).thenReturn(employeesExpected);

    EmployeeResponse employeesResponse = employeeBAR.fetchAllEmployees(givenRequest);
    Assertions.assertEquals(responseExpected, employeesResponse);
  }

  @Test
  void testFetchEmployeeById() {
    final Employee employeeExpected = givenEmployee();
    final EmployeeResponse responseExpected =  new EmployeeResponse()
        .withData(employeeExpected)
        .withStatus(STATUS.OPERATIONSUCCESS);

    EmployeeRequest givenRequest = new EmployeeRequest().withId(1);
    //when(employeeMapper.findById(anyInt())).thenReturn(employeeExpected);
    when(employeeMapper.findById(1)).thenReturn(employeeExpected);

    EmployeeResponse employeesResponse = employeeBAR.fetchEmployeeById(givenRequest);
    Assertions.assertEquals(responseExpected, employeesResponse);
  }

  @Test
  void testInsertEmployee() {
    final Employee employeeExpected = givenEmployee();
    final EmployeeResponse responseExpected = new EmployeeResponse()
        .withData(employeeExpected)
        .withStatus(STATUS.OPERATIONSUCCESS);

    EmployeeRequest givenRequest = new EmployeeRequest().withData(employeeExpected);
    //when(employeeMapper.insert(any(Employee.class))).thenReturn(1);
    when(sqlSession.insert(any(String.class), any(Employee.class))).thenReturn(1);

    EmployeeResponse employeesResponse = employeeBAR.insertEmployee(givenRequest);
    Assertions.assertEquals(responseExpected, employeesResponse);
  }

  @Test
  void testUpdateEmployee() {
    final Employee employeeExpected = givenEmployee();
    final EmployeeResponse responseExpected = new EmployeeResponse()
        .withData(employeeExpected)
        .withStatus(STATUS.OPERATIONSUCCESS);

    EmployeeRequest givenRequest = new EmployeeRequest().withData(employeeExpected);
    //when(employeeMapper.update(any(Employee.class))).thenReturn(1);
    when(sqlSession.update(any(String.class), any(Employee.class))).thenReturn(1);

    EmployeeResponse employeesResponse = employeeBAR.updateEmployee(givenRequest);
    Assertions.assertEquals(responseExpected, employeesResponse);
  }

  @Test
  void testDeleteEmployee() {
    final Employee employeeExpected = givenEmployee();
    final EmployeeResponse responseExpected =  new EmployeeResponse()
        .withData(employeeExpected)
        .withStatus(STATUS.OPERATIONSUCCESS);

    EmployeeRequest givenRequest = new EmployeeRequest().withId(1);
    //when(employeeMapper.deleteById(1)).thenReturn(1);
    when(sqlSession.delete(any(String.class), any(Integer.class))).thenReturn(1);

    EmployeeResponse employeesResponse = employeeBAR.deleteEmployee(givenRequest);
    Assertions.assertEquals(responseExpected.getStatus(), employeesResponse.getStatus());
  }

  private Employee givenEmployee() {
    return givenEmployee(1);
  }

  private Employee givenEmployee(Integer id) {
    return EmployeeBuilder
        .builder()
        .id(id).name("Mateus").email("example@gmail.com").branch("abc").phone("123456")
        .build();
  }

  private List<Employee> givenEmployees() {
    return List.of(
        givenEmployee(1), givenEmployee(2)
    );
  }
}
