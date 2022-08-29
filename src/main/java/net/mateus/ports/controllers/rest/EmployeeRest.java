package net.mateus.ports.controllers.rest;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class EmployeeRest {

  private final EmployeeBAC bac;

  @GetMapping("/fetch-all")
  public List<Employee> list(){
    return bac.fetchAllEmployees();
  }

  @GetMapping("/{id}")
  public Employee get(@PathVariable Integer id){
    return bac.fetchEmployeeById(id);
  }

  @PostMapping
  public ResponseEntity<?> insert(@RequestBody Employee employee){
    Response<Employee> response = bac.insertEmployee(employee);
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }

  @PutMapping
  public ResponseEntity update(@RequestBody Employee employee){
    Response<Employee> response = bac.updateEmployee(employee);
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable Integer id){
    Integer transactionStatus = bac.deleteEmployee(id);

    if(transactionStatus == 1){
      return new ResponseEntity(HttpStatus.OK);
    }else {
      return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
  }

}
