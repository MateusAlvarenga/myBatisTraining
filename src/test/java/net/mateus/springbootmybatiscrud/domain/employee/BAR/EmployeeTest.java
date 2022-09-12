package net.mateus.springbootmybatiscrud.domain.employee.BAR;

import static org.junit.jupiter.api.Assertions.assertEquals;

import net.mateus.domain.employee.BAR.EmployeeBuilder;
import net.mateus.domain.employee.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EmployeeTest {

  @Test
  public void TestBuilderInstantiation() {

    final Employee employeExpected =
        new Employee(1, "Mateus", "222", "joe@gmail.com", "IT");

    final Employee employeActual1 =
        EmployeeBuilder.builder()
            .id(1).name("Mateus").phone("222").email("joe@gmail.com").branch("IT")
            .build();

    final Employee employeActual2 = new Employee();
    employeActual2.setId(1);
    employeActual2.setName("Mateus");
    employeActual2.setPhone("222");
    employeActual2.setEmail("joe@gmail.com");
    employeActual2.setBranch("IT");

    assertEquals(employeExpected, employeActual1);
    assertEquals(employeExpected, employeActual2);
  }



}
