package com.qat.employee.controllers.web;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.qat.employee.controllers.BaseWebTest;
import com.qat.employee.domain.STATUS;
import com.qat.employee.domain.employee.BAR.EmployeeBuilder;
import com.qat.employee.domain.employee.model.Employee;
import com.qat.employee.domain.employee.model.EmployeeResponse;
import com.qat.employee.domain.employee.BAC.EmployeeBAC;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.RequestBuilder;


@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest extends BaseWebTest {

  private final String EMPTY_LIST = "/emp-list";
  private final String INSERT = "/insert";

  private final String UPDATE = "/update";
  private final String DELETE = "/delete";

  @MockBean
  private EmployeeBAC BAC;

  @Test
  public void fetchList() throws Exception {

    final EmployeeResponse responseExpected = givenResponse();
    when(BAC.fetchAllEmployees(any())).thenReturn(responseExpected);

    final RequestBuilder request = createRequest(HttpMethod.GET, EMPTY_LIST);
    String result = performRequest(request).getResponse().getContentAsString();
    System.out.println(result);

    assertEmployee(result, responseExpected);
  }

  @Test
  public void insertTest() throws Exception {
    final EmployeeResponse responseExpected = givenResponse();
    when(BAC.insertEmployee(any())).thenReturn(responseExpected);
    when(BAC.fetchAllEmployees(any())).thenReturn(responseExpected);

    final RequestBuilder request = createRequest(HttpMethod.POST, INSERT, givenEmployee());
    String result = performRequest(request).getResponse().getContentAsString();
    System.out.println(result);

    assertEmployee(result, responseExpected);

  }

  @Test
  public void updateTest() throws Exception {
    final EmployeeResponse responseExpected = givenResponse();
    when(BAC.updateEmployee(any())).thenReturn(responseExpected);
    when(BAC.fetchAllEmployees(any())).thenReturn(responseExpected);

    final RequestBuilder request = createRequest(HttpMethod.POST, UPDATE, givenEmployee());
    String result = performRequest(request).getResponse().getContentAsString();
    System.out.println(result);

    assertEmployee(result, responseExpected);

  }

//  //@Test
//  public void deleteTest() throws Exception {
//    final EmployeeResponse responseExpected =  givenResponse();
//    when(BAC.deleteEmployee(any())).thenReturn(responseExpected);
//    when(BAC.fetchAllEmployees(any())).thenReturn(responseExpected);
//
//    final RequestBuilder request =  createRequest(HttpMethod.GET, DELETE + "?id=1", givenEmployee());
//    performRequest(request).getResponse().getContentAsString();
//
//
//  }

  private void assertEmployee(String content, EmployeeResponse employeeResponse) {
    employeeResponse.getData().forEach(e -> {
      assertThat(content).contains(e.getName()).contains(e.getId().toString())
          .contains(e.getEmail()).contains(e.getBranch()).contains(e.getPhone());
    });
  }

  private EmployeeResponse givenResponse() {
    return new EmployeeResponse(givenEmployees(), STATUS.OPERATIONSUCCESS);
  }

  private Employee givenEmployee() {
    return givenEmployee(1);
  }

  private Employee givenEmployee(Integer id) {
    return EmployeeBuilder.builder().id(id).name("Mateus").email("example@gmail.com").branch("abc")
        .phone("123456").build();
  }

  private List givenEmployees() {
    return List.of(givenEmployee(1), givenEmployee(2), givenEmployee(3), givenEmployee(4),
        givenEmployee(5));
  }

}
