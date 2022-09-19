package com.qat.employee.domain.employee.BAC;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.qat.employee.domain.ValidationError;
import com.qat.employee.domain.employee.BAR.EmployeeBuilder;
import com.qat.employee.domain.employee.model.EmployeeRequest;
import com.qat.employee.domain.employee.model.EmployeeResponse;
import java.util.List;
import com.qat.employee.domain.Response;
import com.qat.employee.domain.STATUS;
import com.qat.employee.domain.employee.BAR.EmployeeBAR;
import com.qat.employee.domain.employee.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class EmployeeBACTest {

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
        .id(id).name("Mateus").email("example@gmail.com").branch("abc").phone("123456")
        .build();
  }

  @Test
  public void testFetchEmployees() {
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
  public void testFetchEmployeeById() {
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
  public void testInsertEmployee() {
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
  public void testUpdateEmployee() {
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
  public void testDeleteEmployee() {
    final Employee givenEmployee = givenEmployee();
    final EmployeeResponse responseExpected = new EmployeeResponse()
        .withData(givenEmployee)
        .withStatus(STATUS.OPERATIONSUCCESS);
    EmployeeRequest givenRequest = new EmployeeRequest().withData(givenEmployee);
    when(bar.deleteEmployee(givenRequest)).thenReturn(responseExpected);

    EmployeeResponse employeesResponse = bac.deleteEmployee(givenRequest);
    assertEquals(responseExpected, employeesResponse);
  }

  @Test
  public void testUpdateEmployeeWithInvalidData() {
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
  public void testInsertEmployeeWithInvalidData() {
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
  public void testValidations() {
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
}
