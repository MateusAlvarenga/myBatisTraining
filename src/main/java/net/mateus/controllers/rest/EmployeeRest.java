package net.mateus.controllers.rest;

import net.mateus.advice.TrackExecutionTime;
import net.mateus.domain.Response;
import net.mateus.domain.employee.BAC.EmployeeBAC;
import net.mateus.domain.employee.model.Employee;
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
  @TrackExecutionTime
  public ResponseEntity<?> list(){
    try{
      Response<Employee> response = bac.fetchAllEmployees();
      return response.toResponseEntity();

    }catch (Exception exception){
      return Response.of(exception).toResponseEntity();
    }
  }

  @GetMapping("/fetch-by-id/{id}")
  public ResponseEntity<?>  get(@PathVariable Integer id){
    try{
      Response<Employee> response = bac.fetchEmployeeById(id);
      return  response.toResponseEntity();
    }catch (Exception exception){
      return Response.of(exception).toResponseEntity();
    }
  }

  @PostMapping
  public ResponseEntity<?> insert(@RequestBody Employee employee){
    try{
      Response<Employee> response = bac.insertEmployee(employee);
      return response.toResponseEntity();
    }catch (Exception exception){
      return Response.of(exception).toResponseEntity();
    }
  }

  @PutMapping
  public ResponseEntity update(@RequestBody Employee employee){
    try {
      Response<Employee> response = bac.updateEmployee(employee);
      return  response.toResponseEntity();
    }catch (Exception exception){
      return Response.of(exception).toResponseEntity();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable Integer id) {
    try {
      return bac.deleteEmployee(id).toResponseEntity();
    } catch (Exception exception) {
      return Response.of(exception).toResponseEntity();
    }
  }
}
