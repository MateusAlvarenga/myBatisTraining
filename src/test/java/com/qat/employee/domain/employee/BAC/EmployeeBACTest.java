package com.qat.employee.domain.employee.BAC;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.List;
import com.qat.employee.domain.Response;
import com.qat.employee.domain.STATUS;
import com.qat.employee.domain.employee.BAC.EmployeeBACImpl;
import com.qat.employee.domain.employee.BAR.EmployeeBAR;
import com.qat.employee.domain.employee.BAR.EmployeeBuilder;
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

  @Test
  public void testFetchEmployees() {
    final List<Employee> employeesExpected = List.of(
        EmployeeBuilder
            .builder()
            .name("Mateus").email("example@gmail.com").branch("abc").phone("123456")
            .build(),
        EmployeeBuilder
            .builder()
            .name("joe").email("example@gmail.com").branch("abc").phone("3456891")
            .build()
    );
    final Response<Employee> responseExpected = Response.of(employeesExpected, STATUS.OPERATIONSUCCESS);
    when(bar.fetchAllEmployees()).thenReturn(responseExpected);

    Response<Employee> employeesResponse = bac.fetchAllEmployees();
    assertEquals(responseExpected, employeesResponse);
  }

  @Test
  public void testFetchEmployeeById() {
    final Employee employeeExpected = EmployeeBuilder.builder().id(1).name("Mateus").build();
    final Response<Employee> responseExpected = Response.of(employeeExpected, STATUS.OPERATIONSUCCESS);
    when(bar.fetchEmployeeById(anyInt())).thenReturn(responseExpected);

    Response<Employee> employeeResponse = bac.fetchEmployeeById(1);
   assertEquals(responseExpected, employeeResponse);
  }

  @Test
  public void testInsertEmployee() {
    final Employee employeeExpected =  EmployeeBuilder
        .builder()
        .name("Mateus").email("example@gmail.com").branch("abc").phone("123456")
        .build();
    final Response<Employee> responseExpected = Response.of(employeeExpected, STATUS.OPERATIONSUCCESS);
    when(bar.insertEmployee(any())).thenReturn(responseExpected);

    final Response<Employee> response = bac.insertEmployee(employeeExpected);
    assertEquals(responseExpected.getData(), response.getData());
    assertEquals(response.getMessages().size(),0);
  }

  @Test
  public void testUpdateEmployee() {
    final Employee employeeExpected =  EmployeeBuilder
        .builder()
        .name("Mateus").email("example@gmail.com").branch("abc").phone("123456")
        .build();
    final Response<Employee> responseExpected = Response.of(employeeExpected, STATUS.OPERATIONSUCCESS);
    when(bar.updateEmployee(employeeExpected)).thenReturn(responseExpected);

    final Response<Employee> response = bac.updateEmployee(employeeExpected);
    assertEquals(responseExpected.getData(), response.getData());
    assertEquals(response.getMessages().size(),0);
  }
  
  @Test
  public void testUpdateEmployeeWithInvalidData() {
    final Employee employeeInput =  EmployeeBuilder
        .builder()
        .name("Mateus").email("examplegmail.com").branch("abc").phone("123456")
        .build();
    

    final Response<Employee> response = bac.updateEmployee(employeeInput);
    assertEquals(1,response.getMessages().size());
    assertEquals(0,response.getData().size());
  }

  @Test
  public void testDeleteEmployeeById() {
    final Employee employeeExpected =  EmployeeBuilder
        .builder()
        .name("Mateus").email("example@gmail.com").branch("abc").phone("123456")
        .build();
    final Response<Employee> responseExpected = Response.of(STATUS.OPERATIONSUCCESS);
    when(bar.deleteEmployee(anyInt())).thenReturn(responseExpected);
    final Response<Employee> response = bac.deleteEmployee(1);
    assertEquals(responseExpected, response);
  }

  @Test
  public void testValidations(){
    final Employee employeeInput =  EmployeeBuilder
        .builder()
        .build();
    final Integer expectedValidationErrorsSize = 4;

    final Response<Employee> response = bac.insertEmployee(employeeInput);
    assertEquals(expectedValidationErrorsSize,response.getMessages().size());
  }

  @Test
  public void testValidations2(){
    final Employee employeeInput =  EmployeeBuilder
        .builder()
        .name("Mateus").email("example@gmail.com").branch("abc").phone("123456")
        .build();
    final Integer expectedValidationErrorsSize = 0;
    when(bar.insertEmployee(any())).thenReturn(Response.of(employeeInput, STATUS.OPERATIONSUCCESS));

    final Response<Employee> response = bac.insertEmployee(employeeInput);
    assertEquals(expectedValidationErrorsSize,response.getMessages().size());
  }
  @Test
  public void testValidations3(){
    final Employee employeeInput =  EmployeeBuilder
        .builder()
        .name("Mateus").email("example").branch("abc").phone("123456")
        .build();
    final Integer expectedValidationErrorsSize = 1;

    final Response<Employee> response = bac.insertEmployee(employeeInput);
    assertEquals(expectedValidationErrorsSize,response.getMessages().size());
  }
  @Test
  public void testValidations4(){
    final Employee employeeInput =  EmployeeBuilder
        .builder()
        .name("abcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabc").email("abcabcabcabcabcabcabcabcabcab@cabcabcabcabcabcabcab.com").branch("abcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabc").phone("012345678901234567890123456789012345678901234567890123456789")
        .build();
    final Integer expectedValidationErrorsSize = 4;

    final Response<Employee> response = bac.insertEmployee(employeeInput);
    assertEquals(expectedValidationErrorsSize,response.getMessages().size());
  }
}
