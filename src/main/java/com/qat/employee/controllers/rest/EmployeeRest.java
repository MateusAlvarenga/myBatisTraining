package com.qat.employee.controllers.rest;

import com.qat.employee.domain.Response;
import com.qat.employee.domain.employee.BAC.EmployeeBAC;
import com.qat.employee.domain.employee.model.Employee;
import com.qat.employee.domain.employee.model.EmployeeRequest;
import com.qat.employee.domain.employee.model.EmployeeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
public class EmployeeRest {

  private final String FETCHALL = "/fetch-all";
  private final String FETCHBYID = "/fetch-by-id";
  private final String INSERT = "/insert";
  private final String UPDATE = "/update";
  private final String DELETE = "/delete";

  private final EmployeeBAC bac;

  public EmployeeRest(EmployeeBAC bac) {
    this.bac = bac;
  }

  @PostMapping(FETCHALL)
  public ResponseEntity<?> list(@RequestBody EmployeeRequest request) {
    try {
      Response<Employee> response = bac.fetchAllEmployees(request);
      return response.toResponseEntity();

    } catch (Exception exception) {
      return  new EmployeeResponse(exception).toResponseEntity();
    }
  }

  @PostMapping(FETCHBYID)
  public ResponseEntity<?> fetchById(@RequestBody EmployeeRequest request) {
    try {
      Response<Employee> response = bac.fetchEmployeeById(request);
      return response.toResponseEntity();
    } catch (Exception exception) {
      return new EmployeeResponse(exception).toResponseEntity();
    }
  }

  @PostMapping(INSERT)
  public ResponseEntity<?> insert(@RequestBody EmployeeRequest request) {
    try {
      Response<Employee> response = bac.insertEmployee(request);
      return response.toResponseEntity();
    } catch (Exception exception) {
      return new EmployeeResponse(exception).toResponseEntity();
    }
  }

  @PostMapping(UPDATE)
  public ResponseEntity update(@RequestBody EmployeeRequest request) {
    try {
      Response<Employee> response = bac.updateEmployee(request);
      return response.toResponseEntity();
    } catch (Exception exception) {
      return new EmployeeResponse(exception).toResponseEntity();
    }
  }

  @PostMapping(DELETE)
  public ResponseEntity delete(@RequestBody EmployeeRequest request) {
    try {
      return bac.deleteEmployee(request).toResponseEntity();
    } catch (Exception exception) {
      return new EmployeeResponse(exception).toResponseEntity();
    }
  }
}
