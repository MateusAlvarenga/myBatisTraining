package com.qat.employee.domain.employee.BAR;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.qat.employee.domain.STATUS;
import com.qat.employee.domain.employee.model.Employee;
import com.qat.employee.domain.employee.model.EmployeeRequest;
import com.qat.employee.domain.employee.model.EmployeeResponse;
import java.util.List;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Employeebar2Test {

  @Autowired
  EmployeeBAR employeeBAR;


  @Test
  @Order(1)
  void testInsertEmployees() {

    final EmployeeResponse responseExpected = new EmployeeResponse()
        .withData(givenEmployee())
        .withStatus(STATUS.OPERATIONSUCCESS);

    final EmployeeRequest requestToInsert = new EmployeeRequest().withData(givenEmployee());

    final EmployeeResponse responseActual = employeeBAR.insertEmployee(requestToInsert);

    assertEquals(responseExpected, responseActual);

  }

  @Test
  @Order(2)
  void testFetchEmployees(){


    final EmployeeRequest requestToInsert = new EmployeeRequest().withData(givenEmployee(null));

    employeeBAR.insertEmployee(requestToInsert);
    employeeBAR.insertEmployee(requestToInsert);
    employeeBAR.insertEmployee(requestToInsert);

    final EmployeeResponse responseExpected = new EmployeeResponse()
        .withData(List.of(givenEmployee(1), givenEmployee(2), givenEmployee(3),givenEmployee(4)))
        .withStatus(STATUS.OPERATIONSUCCESS);


    final EmployeeResponse responseActual = employeeBAR.fetchAllEmployees(new EmployeeRequest());

    assertEquals(responseExpected, responseActual);
  }

  @Test
  @Order(3)
  void testFetchEmployeeById() {

    final EmployeeResponse responseActual = employeeBAR
        .fetchEmployeeById(new EmployeeRequest().withId(2));

    final EmployeeResponse responseExpected = new EmployeeResponse()
        .withData(givenEmployee(2))
        .withStatus(STATUS.OPERATIONSUCCESS);

    assertEquals(responseExpected, responseActual);
  }

  @Test
  @Order(4)
   void testUpdateEmployee() {

    Employee givenEmployee = givenEmployee(1);
    givenEmployee.setName("New Name");

    final EmployeeResponse responseActual = employeeBAR
        .updateEmployee(new EmployeeRequest().withData(givenEmployee));

    final EmployeeResponse responseExpected = new EmployeeResponse().withData(givenEmployee).withStatus(STATUS.OPERATIONSUCCESS);

    assertEquals(responseExpected, responseActual);
  }

  @Test
  @Order(5)
  void testDeleteEmployee() {

    final EmployeeResponse responseActual = employeeBAR
        .deleteEmployee(new EmployeeRequest().withId(1));

    final EmployeeResponse responseExpected = new EmployeeResponse().withStatus(STATUS.OPERATIONSUCCESS);

    assertEquals(responseExpected, responseActual);
  }



  private Employee givenEmployee() {
    return givenEmployee(1);
  }

  private Employee givenEmployee(Integer id) {
    return EmployeeBuilder
        .builder()
        .id(id).name("TestName").email("teste@gmail.com").branch("teste").phone("123456")
        .build();
  }

}
