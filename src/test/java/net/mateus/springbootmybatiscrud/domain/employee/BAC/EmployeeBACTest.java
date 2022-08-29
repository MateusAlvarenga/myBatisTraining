package net.mateus.springbootmybatiscrud.domain.employee.BAC;


import java.util.List;
import net.mateus.domain.Response;
import net.mateus.domain.employee.BAC.EmployeeBACImpl;
import net.mateus.domain.employee.model.Employee;
import net.mateus.domain.employee.BAR.EmployeeBAR;
import net.mateus.domain.employee.BAR.EmployeeBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class EmployeeBACTest {

  @Mock
  EmployeeBAR bar;
  @InjectMocks
  EmployeeBACImpl bac;

  @Test
  public void testFetchEmployees() {
    final List<Employee> employeesExpected = List.of(
        EmployeeBuilder.builder().id(1).name("Mateus").build(),
        EmployeeBuilder.builder().id(2).name("James").build()
    );
    when(bar.fetchAllEmployees()).thenReturn(employeesExpected);

    List<Employee> employeesResponse = bac.fetchAllEmployees();
    assertThat(employeesResponse).isEqualTo(employeesExpected);
  }

  @Test
  public void testFetchEmployeeById() {
    final Employee employeeExpected = EmployeeBuilder.builder().id(1).name("Mateus").build();
    when(bar.fetchEmployeeById(anyInt())).thenReturn(employeeExpected);

    Employee employeeResponse = bac.fetchEmployeeById(1);
    assertThat(employeeResponse).isEqualTo(employeeExpected);
  }

//  @Test
//  public void testInsertEmployee() {
//    final Employee employeeExpected = EmployeeBuilder.builder().id(1).name("Mateus").build();
//    when(bar.insertEmployee(employeeExpected)).thenReturn(1);
//
//    Integer idResponse = bac.insertEmployee(employeeExpected);
//    assertEquals(idResponse, 1);
//  }

  @Test
  public void testUpdateEmployee() {
    final Employee employeeExpected = EmployeeBuilder.builder().id(1).name("Mateus").build();
    final Response<Employee> responseExpected = Response.of(employeeExpected, HttpStatus.OK);
    when(bar.updateEmployee(employeeExpected)).thenReturn(1);

    Response<Employee> idResponse = bac.updateEmployee(employeeExpected);
    assertEquals(idResponse, responseExpected);
  }

  @Test
  public void testDeleteEmployeeById() {
    final Employee employeeExpected = EmployeeBuilder.builder().id(1).name("Mateus").build();
    when(bar.deleteEmployee(anyInt())).thenReturn(1);

    Integer idResponse = bac.deleteEmployee(1);
    assertEquals(idResponse, 1);
  }
}
