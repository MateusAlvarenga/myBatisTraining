package net.mateus.springbootmybatiscrud.domain.employee.BAR;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.List;
import net.mateus.domain.Response;
import net.mateus.domain.STATUS;
import net.mateus.domain.employee.BAR.EmployeeBARImpl;
import net.mateus.domain.employee.BAR.EmployeeBuilder;
import net.mateus.domain.employee.BAR.EmployeeMapper;
import net.mateus.domain.employee.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EmployeeBARTest {

  @Mock
  EmployeeMapper employeeMapper;

  @InjectMocks
  EmployeeBARImpl employeeBAR;

  @Test
  public void testFetchAllEmployees() {
    final List<Employee> employeesMock = List.of(
        EmployeeBuilder
            .builder()
            .name("Mateus").email("mat@gmail.com").branch("abc").phone("123456")
            .build(),
        EmployeeBuilder
            .builder()
            .name("joe").email("joe@gmail.com").branch("abc").phone("3456891")
            .build());

    final Response<Employee> responseExpected = Response.of(employeesMock, STATUS.OPERATIONSUCCESS);
    when(employeeMapper.fetchAll()).thenReturn(employeesMock);

    final Response<Employee> actualResponse = employeeBAR.fetchAllEmployees();

    Assertions.assertEquals(responseExpected, actualResponse);

  }

  //@Test
  public void testFetchEmployeeById() {
    final Employee employeeMock = EmployeeBuilder.builder().id(1).name("Mateus").build();
    final Response<Employee> responseExpected = Response.of(employeeMock, STATUS.OPERATIONSUCCESS);
    //when(employeeMapper.findById(anyInt())).thenReturn(employeeMock);
    doReturn(employeeMock).when(employeeMapper).findById(anyInt());

    final Response<Employee> actualResponse = employeeBAR.fetchEmployeeById(1);

    Assertions.assertEquals(responseExpected, actualResponse);
  }

  @Test
  public void testInsertEmployee() {
    final Employee employeeMock = EmployeeBuilder
        .builder()
        .name("Mateus").email("mat@gmail.com").branch("abc").phone("123456")
        .build();
    final Response<Employee> responseExpected = Response.of(employeeMock, STATUS.OPERATIONSUCCESS);
    when(employeeMapper.insert(employeeMock)).thenReturn(1);

    final Response<Employee> actualResponse = employeeBAR.insertEmployee(employeeMock);

    Assertions.assertEquals(responseExpected, actualResponse);
  }

  @Test
  public void testUpdateEmployee() {
    final Employee employeeMock = EmployeeBuilder
        .builder()
        .id(1)
        .name("Mateus").email("mat@gmail.com").branch("abc").phone("123456")
        .build();
    final Response<Employee> responseExpected = Response.of(employeeMock, STATUS.OPERATIONSUCCESS);
    when(employeeMapper.update(employeeMock)).thenReturn(1);

    final Response<Employee> actualResponse = employeeBAR.updateEmployee(employeeMock);

    Assertions.assertEquals(responseExpected, actualResponse);

  }


  @Test
  public void testDeleteEmployee() {
    final Employee employeeMock = EmployeeBuilder
        .builder()
        .id(1)
        .name("Mateus").email("mat@gmail.com").branch("abc").phone("123456")
        .build();
    final Response<Employee> responseExpected = Response.of(employeeMock, STATUS.OPERATIONSUCCESS);

    when(employeeMapper.deleteById(1)).thenReturn(1);

    final Response<Employee> actualResponse = employeeBAR.deleteEmployee(1);

    Assertions.assertEquals( STATUS.OPERATIONSUCCESS, actualResponse.getStatus());

  }

}
