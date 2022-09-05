package net.mateus.springbootmybatiscrud.domain.employee.BAC;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.List;
import net.mateus.domain.Response;
import net.mateus.domain.employee.BAC.EmployeeBACImpl;
import net.mateus.domain.employee.BAR.EmployeeBAR;
import net.mateus.domain.employee.BAR.EmployeeBuilder;
import net.mateus.domain.employee.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;


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
    final Response<Employee> responseExpected = Response.of(employeesExpected, HttpStatus.OK);
    when(bar.fetchAllEmployees()).thenReturn(employeesExpected);

    Response<Employee> employeesResponse = bac.fetchAllEmployees();
    assertEquals(responseExpected, employeesResponse);
  }

  @Test
  public void testFetchEmployeeById() {
    final Employee employeeExpected = EmployeeBuilder.builder().id(1).name("Mateus").build();
    final Response<Employee> responseExpected = Response.of(employeeExpected, HttpStatus.OK);
    when(bar.fetchEmployeeById(anyInt())).thenReturn(employeeExpected);

    Response<Employee> employeeResponse = bac.fetchEmployeeById(1);
   assertEquals(responseExpected, employeeResponse);
  }

  @Test
  public void testInsertEmployee() {
    final Employee employeeExpected =  EmployeeBuilder
        .builder()
        .name("Mateus").email("example@gmail.com").branch("abc").phone("123456")
        .build();
    final Response<Employee> responseExpected = Response.of(employeeExpected, HttpStatus.OK);
    when(bar.insertEmployee(employeeExpected)).thenReturn(1);

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
    final Response<Employee> responseExpected = Response.of(employeeExpected, HttpStatus.OK);
    when(bar.updateEmployee(employeeExpected)).thenReturn(1);

    final Response<Employee> response = bac.updateEmployee(employeeExpected);
    assertEquals(responseExpected.getData(), response.getData());
    assertEquals(response.getMessages().size(),0);
  }

  @Test
  public void testDeleteEmployeeById() {
    final Employee employeeExpected =  EmployeeBuilder
        .builder()
        .name("Mateus").email("example@gmail.com").branch("abc").phone("123456")
        .build();
    when(bar.deleteEmployee(anyInt())).thenReturn(1);

    Integer idResponse = bac.deleteEmployee(1);
    assertEquals(idResponse, 1);
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
}
