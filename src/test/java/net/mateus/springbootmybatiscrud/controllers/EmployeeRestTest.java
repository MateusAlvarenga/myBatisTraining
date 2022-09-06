package net.mateus.springbootmybatiscrud.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import net.mateus.domain.Response;
import net.mateus.domain.STATUS;
import net.mateus.domain.employee.BAC.EmployeeBAC;
import net.mateus.domain.employee.BAR.EmployeeBuilder;
import net.mateus.domain.employee.model.Employee;
import net.mateus.ports.controllers.rest.EmployeeRest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeRest.class)
public class EmployeeRestTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private EmployeeBAC BAC;

  @Autowired
  ObjectMapper mapper;



  @Test

  public void fetchList() throws Exception {

    final List<Employee> employeesExpected = List.of(
        EmployeeBuilder.builder().id(1).name("Mateus").build(),
        EmployeeBuilder.builder().id(2).name("James").build()
    );
    final String urlTemplate = "/api/employee/fetch-all";
    final Response<Employee> responseExpected = Response.of(employeesExpected, STATUS.OPERATIONSUCCESS);
    when(BAC.fetchAllEmployees()).thenReturn(responseExpected);

    RequestBuilder request = MockMvcRequestBuilders
        .get(urlTemplate)
        .accept(MediaType.APPLICATION_JSON);
    MvcResult Response = mockMvc.perform(request)
        .andExpect(status().isOk())
        .andReturn();

    JSONAssert.assertEquals(
        Response.getResponse().getContentAsString(),
        mapper.writeValueAsString(responseExpected), true);
  }
  @Test

  public void fetchById() throws Exception {

    final Employee employeeExpected =  EmployeeBuilder.builder().id(1).name("Mateus").build();
    final Response<Employee> responseExpected = Response.of(employeeExpected, STATUS.OPERATIONSUCCESS);
    final Integer givenId = 1;
    final String urlTemplate = "/api/employee/fetch-by-id/" + givenId;
    when(BAC.fetchEmployeeById(anyInt())).thenReturn(responseExpected);

    RequestBuilder request = MockMvcRequestBuilders
        .get(urlTemplate)
        .accept(MediaType.APPLICATION_JSON);
    MvcResult Response = mockMvc.perform(request)
        .andExpect(status().isOk())
        .andReturn();

    JSONAssert.assertEquals(
        Response.getResponse().getContentAsString(),
        mapper.writeValueAsString(responseExpected), true);
  }

  @Test
  public void insert() throws Exception {

    final Employee employeeExpected =  EmployeeBuilder
        .builder()
        .name("Mateus").email("example@gmail.com").branch("abc").phone("123456")
        .build();
    final Response<Employee> responseExpected = Response.of(employeeExpected, STATUS.OPERATIONSUCCESS);
    final String urlTemplate = "/api/employee";
    when(BAC.insertEmployee(any())).thenReturn(responseExpected);

    RequestBuilder request = MockMvcRequestBuilders
        .post(urlTemplate)
        .accept(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(employeeExpected))
        .contentType(MediaType.APPLICATION_JSON);
    mockMvc.perform(request)
        .andExpect(status().isOk());
  }

  @Test
  public void update() throws Exception {

    final Employee employeeExpected =  EmployeeBuilder
        .builder()
        .name("Mateus").email("example@gmail.com").branch("abc").phone("123456")
        .build();
    final Response<Employee> responseExpected = Response.of(employeeExpected, STATUS.OPERATIONSUCCESS);
    final String urlTemplate = "/api/employee";
    when(BAC.updateEmployee(any())).thenReturn(responseExpected);

    RequestBuilder request = MockMvcRequestBuilders
        .put(urlTemplate)
        .accept(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(employeeExpected))
        .contentType(MediaType.APPLICATION_JSON);
    mockMvc.perform(request)
        .andExpect(status().isOk());
  }

  @Test
  public void delete() throws Exception {

    final Integer givenId = 1;
    final String urlTemplate = "/api/employee/" + givenId;
    final Response<Employee> responseExpected = Response.of(STATUS.OPERATIONSUCCESS);
    when(BAC.deleteEmployee(anyInt())).thenReturn(responseExpected);
    RequestBuilder request = MockMvcRequestBuilders
        .delete(urlTemplate)
        .accept(MediaType.APPLICATION_JSON);
    mockMvc.perform(request)
        .andExpect(status().isOk());
  }


}
