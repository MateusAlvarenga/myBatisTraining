package com.qat.employee.controllers.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.qat.employee.controllers.BaseWebTest;
import com.qat.employee.domain.STATUS;
import com.qat.employee.domain.employee.BAC.EmployeeBAC;
import com.qat.employee.domain.employee.BAR.EmployeeBuilder;
import com.qat.employee.domain.employee.model.Employee;
import com.qat.employee.domain.employee.model.EmployeeRequest;
import com.qat.employee.domain.employee.model.EmployeeResponse;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
 

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeRest.class)
public class EmployeeControllerTest extends BaseWebTest {

  @MockBean
  private EmployeeBAC BAC;

  private final String FETCHALL = "/api/employee/fetch-all";
  private final String FETCHBYID = "/api/employee/fetch-by-id";
  private final String INSERT = "/api/employee/insert";
  private final String UPDATE = "/api/employee/update";
  private final String DELETE = "/api/employee/delete";


  @Test
  public void fetchList() throws Exception {

    EmployeeRequest givenRequest = new EmployeeRequest();
    final EmployeeResponse responseExpected = new EmployeeResponse()
        .withData(givenEmployees())
        .withStatus(STATUS.OPERATIONSUCCESS);

    when(BAC.fetchAllEmployees(givenRequest)).thenReturn(responseExpected);

    final RequestBuilder request = createRequest(FETCHALL, givenRequest);
    final MvcResult response = performRequest(request);

    assertJsonEquals(response, responseExpected);
  }

  @Test
  public void fetchListError() throws Exception {

    EmployeeRequest givenRequest = new EmployeeRequest();
    final EmployeeResponse responseExpected = new EmployeeResponse()
        .withStatus(STATUS.EXCEPTIONERROR);

    when(BAC.fetchAllEmployees(givenRequest)).thenThrow(new RuntimeException());

    final RequestBuilder request = createRequest(FETCHALL, givenRequest);
    final MvcResult response = performRequestAndReturn(request);

    assertJsonEquals(response, responseExpected);
  }

  @Test
  public void fetchById() throws Exception {

    final Employee givenEmployee = givenEmployee();
    final EmployeeResponse responseExpected =  new EmployeeResponse()
        .withData(givenEmployee())
        .withStatus(STATUS.OPERATIONSUCCESS);

    when(BAC.fetchEmployeeById(any())).thenReturn(responseExpected);

    final RequestBuilder request = createRequest(FETCHBYID, givenEmployee);
    final MvcResult response = performRequest(request);

    assertJsonEquals(response, responseExpected);
  }

  @Test
  public void fetchByIdError() throws Exception {

    final Employee givenEmployee = givenEmployee();
    final EmployeeResponse responseExpected =  new EmployeeResponse()
        .withStatus(STATUS.EXCEPTIONERROR);

    when(BAC.fetchEmployeeById(any())).thenThrow(new RuntimeException());

    final RequestBuilder request = createRequest(FETCHBYID, givenEmployee);
    final MvcResult response = performRequestAndReturn(request);

    assertJsonEquals(response, responseExpected);
  }

  @Test
  public void insert() throws Exception {

    final EmployeeRequest givenRequest = new EmployeeRequest().withData(givenEmployee());
    final EmployeeResponse responseExpected = new EmployeeResponse()
        .withData(givenEmployee())
        .withStatus(STATUS.OPERATIONSUCCESS);

    when(BAC.insertEmployee(any())).thenReturn(responseExpected);

    RequestBuilder request = createRequest(INSERT, givenRequest);
    performRequest(request);
  }

  @Test
  public void insertError() throws Exception {

    final EmployeeRequest givenRequest = new EmployeeRequest().withData(givenEmployee());
    final EmployeeResponse responseExpected = new EmployeeResponse().withStatus(STATUS.EXCEPTIONERROR);

    when(BAC.insertEmployee(any())).thenThrow(new RuntimeException());

    RequestBuilder request = createRequest(INSERT, givenRequest);
    final MvcResult response = performRequestAndReturn(request);

    assertJsonEquals(response, responseExpected);
  }

  @Test
  public void update() throws Exception {

    final EmployeeRequest givenRequest = new EmployeeRequest().withData(givenEmployee());
    final EmployeeResponse responseExpected =  new EmployeeResponse()
        .withData(givenEmployee())
        .withStatus(STATUS.OPERATIONSUCCESS);

    when(BAC.updateEmployee(any())).thenReturn(responseExpected);

    RequestBuilder request = createRequest(UPDATE, givenRequest);
    performRequest(request);
  }

  @Test
  public void updateError() throws Exception {

    final EmployeeRequest givenRequest = new EmployeeRequest().withData(givenEmployee());
    final EmployeeResponse responseExpected = new EmployeeResponse().withStatus(STATUS.EXCEPTIONERROR);

    when(BAC.updateEmployee(any())).thenThrow(new RuntimeException());

    RequestBuilder request = createRequest(UPDATE, givenRequest);
    final MvcResult response = performRequestAndReturn(request);

    assertJsonEquals(response, responseExpected);
  }

  @Test
  public void delete() throws Exception {

    final EmployeeRequest givenRequest = new EmployeeRequest().withData(givenEmployee());
    final EmployeeResponse responseExpected = new EmployeeResponse()
        .withData(givenEmployee())
        .withStatus(STATUS.OPERATIONSUCCESS);

    when(BAC.deleteEmployee(any())).thenReturn(responseExpected);

    RequestBuilder request = createRequest(DELETE, givenRequest);
    performRequest(request);
  }

  @Test
  public void deleteError() throws Exception {

    final EmployeeRequest givenRequest = new EmployeeRequest().withData(givenEmployee());
    final EmployeeResponse responseExpected = new EmployeeResponse().withStatus(STATUS.EXCEPTIONERROR);

    when(BAC.deleteEmployee(any())).thenThrow(new RuntimeException());

    RequestBuilder request = createRequest(DELETE, givenRequest);
    final MvcResult response = performRequestAndReturn(request);

    assertJsonEquals(response, responseExpected);
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
