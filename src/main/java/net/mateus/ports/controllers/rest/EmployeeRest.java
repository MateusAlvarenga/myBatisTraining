package net.mateus.ports.controllers.rest;

import net.mateus.domain.Response;
import net.mateus.domain.employee.BAC.EmployeeBAC;
import net.mateus.domain.employee.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
public class EmployeeRest {

  private final EmployeeBAC bac;

  public EmployeeRest(EmployeeBAC bac) {
    this.bac = bac;
  }

  @GetMapping("/fetch-all")
  public ResponseEntity<?> list(){
    try{
      Response<Employee> response = bac.fetchAllEmployees();
      return response.toResponseEntity();

    }catch (Exception e){
      Response response = Response.of(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      return  response.toResponseEntity();
    }
  }

  @GetMapping("/fetch-by-id/{id}")
  public ResponseEntity<?>  get(@PathVariable Integer id){
    try{
      Response<Employee> response = bac.fetchEmployeeById(id);
      return  response.toResponseEntity();
    }catch (Exception e){
      Response response = Response.of(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      return response.toResponseEntity();
    }
  }

  @PostMapping
  public ResponseEntity<?> insert(@RequestBody Employee employee){
    try{
      Response<Employee> response = bac.insertEmployee(employee);
      return response.toResponseEntity();
    }catch (Exception e){
      Response response = Response.of(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      return response.toResponseEntity();
    }
  }

  @PutMapping
  public ResponseEntity update(@RequestBody Employee employee){
    try {
      Response<Employee> response = bac.updateEmployee(employee);
      return  response.toResponseEntity();
    }catch (Exception e){
      Response response = Response.of(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      return response.toResponseEntity();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable Integer id) {
    try {
      Integer transactionStatus = bac.deleteEmployee(id);

      if (transactionStatus == 1)
        return  Response.of("Employee deleted successfully", HttpStatus.OK).toResponseEntity();
      else
        return  Response.of("Employee not found", HttpStatus.NOT_FOUND).toResponseEntity();

    } catch (Exception e) {
      Response response = Response.of(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      return response.toResponseEntity();
    }
  }
}
