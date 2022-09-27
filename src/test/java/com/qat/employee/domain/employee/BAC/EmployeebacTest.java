package com.qat.employee.domain.employee.BAC;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.qat.employee.domain.common.ValidationError;
import com.qat.employee.domain.employee.model.EmployeeBuilder;
import com.qat.employee.domain.employee.model.EmployeeRequest;
import com.qat.employee.domain.employee.model.EmployeeResponse;
import java.util.List;
import com.qat.employee.domain.common.STATUS;
import com.qat.employee.domain.employee.BAR.EmployeeBAR;
import com.qat.employee.domain.employee.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class EmployeebacTest {

  @Mock
  EmployeeBAR bar;
  @InjectMocks
  EmployeeBACImpl bac;

  private Employee givenEmployee() {
    return givenEmployee(1);
  }

  private Employee givenEmployee(Integer id) {
    return EmployeeBuilder
        .builder()
        .id(id).name("Mateus").email("example@gmail.com").branch("abc").phone("123456").age(20)
        .build();
  }

  @Test
  void testFetchEmployees() {
    final List<Employee> givenEmployee = List.of(
        givenEmployee(1), givenEmployee(2)
    );
    final EmployeeResponse responseExpected = new EmployeeResponse()
        .withData(givenEmployee)
        .withStatus(STATUS.OPERATIONSUCCESS);
    EmployeeRequest givenRequest = new EmployeeRequest();
    when(bar.fetchAllEmployees(givenRequest)).thenReturn(responseExpected);

    EmployeeResponse employeesResponse = bac.fetchAllEmployees(givenRequest);
    assertEquals(responseExpected, employeesResponse);
  }

  @Test
  void testFetchEmployeeById() {
    final Employee givenEmployee = givenEmployee();
    final EmployeeResponse responseExpected = new EmployeeResponse()
        .withData(givenEmployee)
        .withStatus(STATUS.OPERATIONSUCCESS);
    EmployeeRequest givenRequest = new EmployeeRequest().withId(1);
    when(bar.fetchEmployeeById(givenRequest)).thenReturn(responseExpected);

    EmployeeResponse employeesResponse = bac.fetchEmployeeById(givenRequest);
    assertEquals(responseExpected, employeesResponse);
  }

  @Test
  void testInsertEmployee() {
    final Employee givenEmployee = givenEmployee();
    final EmployeeResponse responseExpected = new EmployeeResponse()
        .withData(givenEmployee)
        .withStatus(STATUS.OPERATIONSUCCESS);
    EmployeeRequest givenRequest = new EmployeeRequest().withData(givenEmployee);
    when(bar.insertEmployee(givenRequest)).thenReturn(responseExpected);

    EmployeeResponse employeesResponse = bac.insertEmployee(givenRequest);
    assertEquals(responseExpected, employeesResponse);
  }

  @Test
  void testUpdateEmployee() {
    final Employee givenEmployee = givenEmployee();
    final EmployeeResponse responseExpected = new EmployeeResponse()
        .withData(givenEmployee)
        .withStatus(STATUS.OPERATIONSUCCESS);
    EmployeeRequest givenRequest = new EmployeeRequest().withData(givenEmployee);
    when(bar.updateEmployee(givenRequest)).thenReturn(responseExpected);

    EmployeeResponse employeesResponse = bac.updateEmployee(givenRequest);
    assertEquals(responseExpected, employeesResponse);
  }

  @Test
  void testDeleteEmployee() {
    final Employee givenEmployee = givenEmployee();
    final EmployeeResponse responseExpected = new EmployeeResponse()
        .withData(givenEmployee)
        .withStatus(STATUS.OPERATIONSUCCESS);
    EmployeeRequest givenRequest = new EmployeeRequest().withId(1);
    when(bar.deleteEmployee(givenRequest)).thenReturn(responseExpected);

    EmployeeResponse employeesResponse = bac.deleteEmployee(givenRequest);
    assertEquals(responseExpected, employeesResponse);
  }

  @Test
  void testUpdateEmployeeWithInvalidData() {
    final Employee givenEmployee = givenEmployee();
    final EmployeeResponse responseExpected = new EmployeeResponse()
        .withStatus(STATUS.VALIDATIONERROR)
        .withMessages(List.of());
    EmployeeRequest givenRequest = new EmployeeRequest().withData(givenEmployee);
    when(bar.updateEmployee(givenRequest)).thenReturn(responseExpected);

    EmployeeResponse employeesResponse = bac.updateEmployee(givenRequest);
    assertEquals(responseExpected, employeesResponse);
  }

  @Test
  void testInsertEmployeeWithInvalidData() {
    final Employee givenEmployee = givenEmployee();
    givenEmployee.setName(null);
    final EmployeeResponse responseExpected = new EmployeeResponse()
          .withStatus(STATUS.VALIDATIONERROR)
          .withMessages(List.of(ValidationError.of("name", "name is required")));
    EmployeeRequest givenRequest = new EmployeeRequest().withData(givenEmployee);
    //when(bar.insertEmployee(givenRequest)).thenReturn(responseExpected);

    EmployeeResponse employeesResponse = bac.insertEmployee(givenRequest);
    assertEquals(responseExpected.getStatus(), employeesResponse.getStatus());
    assertEquals(responseExpected.getData(), employeesResponse.getData());
  }

  @Test
  void testValidations() {
    final Employee givenEmployee = new Employee();
    givenEmployee.setName(null);
    final EmployeeResponse responseExpected = new EmployeeResponse()
        .withStatus(STATUS.VALIDATIONERROR)
        .withMessages(List.of(ValidationError.of("name", "name is required")));
    EmployeeRequest givenRequest = new EmployeeRequest().withData(givenEmployee);
    //when(bar.insertEmployee(givenRequest)).thenReturn(responseExpected);

    EmployeeResponse employeesResponse = bac.insertEmployee(givenRequest);
    assertEquals(responseExpected.getStatus(), employeesResponse.getStatus());
    assertEquals(responseExpected.getData(), employeesResponse.getData());
  }

  @Test
  void testInsertEmployeeList() {
    final List<Employee> givenEmployees = List.of(givenEmployee(), givenEmployee(), givenEmployee());
    final EmployeeResponse responseExpected = new EmployeeResponse()
        .withData(givenEmployees)
        .withStatus(STATUS.OPERATIONSUCCESS);
    EmployeeRequest givenRequest = new EmployeeRequest().withDataList(givenEmployees);
    when(bar.insertEmployeeList(givenRequest)).thenReturn(responseExpected);

    EmployeeResponse employeesResponse = bac.insertEmployeeList(givenRequest);
    assertEquals(responseExpected, employeesResponse);
  }
}
