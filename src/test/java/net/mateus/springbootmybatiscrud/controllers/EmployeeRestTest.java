package net.mateus.springbootmybatiscrud.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.SneakyThrows;
import net.mateus.domain.employee.BAC.EmployeeBAC;
import net.mateus.domain.employee.BAR.Employee;
import net.mateus.domain.employee.BAR.EmployeeBuilder;
import net.mateus.ports.controllers.rest.EmployeeRest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
  @SneakyThrows
  public void fetchList() {

    final List<Employee> employeesExpected = List.of(
        EmployeeBuilder.builder().id(1).name("Mateus").build(),
        EmployeeBuilder.builder().id(2).name("James").build()
    );
    final String urlTemplate = "/api/employee/list";
    when(BAC.fetchEmployees()).thenReturn(employeesExpected);

    RequestBuilder request = MockMvcRequestBuilders
        .get(urlTemplate)
        .accept(MediaType.APPLICATION_JSON);
    MvcResult Response = mockMvc.perform(request)
        .andExpect(status().isOk())
        .andReturn();

    JSONAssert.assertEquals(
        Response.getResponse().getContentAsString(),
        mapper.writeValueAsString(employeesExpected), true);
  }
  @Test
  @SneakyThrows
  public void fetchById() {

    final Employee employeeExpected =  EmployeeBuilder.builder().id(1).name("Mateus").build();
    final Integer givenId = 1;
    final String urlTemplate = "/api/employee/" + givenId;
    when(BAC.fetchEmployeeById(anyInt())).thenReturn(employeeExpected);

    RequestBuilder request = MockMvcRequestBuilders
        .get(urlTemplate)
        .accept(MediaType.APPLICATION_JSON);
    MvcResult Response = mockMvc.perform(request)
        .andExpect(status().isOk())
        .andReturn();

    JSONAssert.assertEquals(
        Response.getResponse().getContentAsString(),
        mapper.writeValueAsString(employeeExpected), true);
  }

  @Test
  @SneakyThrows
  public void insert() {

    final Employee employeeExpected =  EmployeeBuilder.builder().id(1).name("Mateus").build();
    final String urlTemplate = "/api/employee";
    when(BAC.insertEmployee(employeeExpected)).thenReturn(1);

    RequestBuilder request = MockMvcRequestBuilders
        .post(urlTemplate)
        .accept(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(employeeExpected))
        .contentType(MediaType.APPLICATION_JSON);
    mockMvc.perform(request)
        .andExpect(status().isOk());
  }

  @Test
  @SneakyThrows
  public void update() {

    final Employee employeeExpected =  EmployeeBuilder.builder().id(1).name("Mateus").build();
    final String urlTemplate = "/api/employee";
    when(BAC.updateEmployee(employeeExpected)).thenReturn(1);

    RequestBuilder request = MockMvcRequestBuilders
        .put(urlTemplate)
        .accept(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(employeeExpected))
        .contentType(MediaType.APPLICATION_JSON);
    mockMvc.perform(request)
        .andExpect(status().isOk());
  }

  @Test
  @SneakyThrows
  public void delete() {

    final Integer givenId = 1;
    final String urlTemplate = "/api/employee/" + givenId;
    when(BAC.deleteEmployee(anyInt())).thenReturn(1);

    RequestBuilder request = MockMvcRequestBuilders
        .delete(urlTemplate)
        .accept(MediaType.APPLICATION_JSON);
    mockMvc.perform(request)
        .andExpect(status().isOk());
  }


}
