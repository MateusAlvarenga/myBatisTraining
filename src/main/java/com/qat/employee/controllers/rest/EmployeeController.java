package com.qat.employee.controllers.rest;

import com.qat.employee.domain.employee.BAC.EmployeeBAC;
import com.qat.employee.domain.employee.model.EmployeeRequest;
import com.qat.employee.domain.employee.model.EmployeeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

  private final EmployeeBAC bac;
  private static final String FETCHALL = "/fetch-all";
  private static final String FETCHBYID = "/fetch-by-id";
  private static final String INSERT = "/insert";
  private static final String UPDATE = "/update";
  private static final String DELETE = "/delete";
  private static final String INSERT_LIST = "/insert-list";



  public EmployeeController(EmployeeBAC bac) {
    this.bac = bac;
  }

  @PostMapping(FETCHALL)
  public ResponseEntity<?> list(@RequestBody EmployeeRequest request) {
    try {
      EmployeeResponse response = bac.fetchAllEmployees(request);
      return response.toResponseEntity();

    } catch (Exception exception) {
      return new EmployeeResponse().withException(exception).toResponseEntity();
    }
  }

  @PostMapping(FETCHBYID)
  public ResponseEntity<?> fetchById(@RequestBody EmployeeRequest request) {
    try {
      EmployeeResponse response = bac.fetchEmployeeById(request);
      return response.toResponseEntity();
    } catch (Exception exception) {
      return new EmployeeResponse().withException(exception).toResponseEntity();
    }
  }

  @PostMapping(INSERT)
  public ResponseEntity<?> insert(@RequestBody EmployeeRequest request) {
    try {
      EmployeeResponse response = bac.insertEmployee(request);
      return response.toResponseEntity();
    } catch (Exception exception) {
      return new EmployeeResponse().withException(exception).toResponseEntity();
    }
  }

  @PostMapping(UPDATE)
  public ResponseEntity<?> update(@RequestBody EmployeeRequest request) {
    try {
      EmployeeResponse response = bac.updateEmployee(request);
      return response.toResponseEntity();
    } catch (Exception exception) {
      return new EmployeeResponse().withException(exception).toResponseEntity();
    }
  }

  @PostMapping(DELETE)
  public ResponseEntity<?> delete(@RequestBody EmployeeRequest request) {
    try {
      return bac.deleteEmployee(request).toResponseEntity();
    } catch (Exception exception) {
      return new EmployeeResponse().withException(exception).toResponseEntity();
    }
  }
  
  @PostMapping(INSERT_LIST)
  public ResponseEntity<?> insertBookmark(@RequestBody EmployeeRequest request) {
    try {
      return bac.insertEmployeeList(request).toResponseEntity();
    } catch (Exception exception) {
      return new EmployeeResponse().withException(exception).toResponseEntity();
    }
  }
  
}
