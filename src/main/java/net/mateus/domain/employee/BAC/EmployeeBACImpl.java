package net.mateus.domain.employee.BAC;

import java.util.List;
import net.mateus.domain.Response;
import net.mateus.domain.Validator;
import net.mateus.domain.employee.model.Employee;
import net.mateus.domain.employee.BAR.EmployeeBAR;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class EmployeeBACImpl implements EmployeeBAC {

  public EmployeeBACImpl(EmployeeBAR bar) {
    this.bar = bar;
  }

  private final EmployeeBAR bar;

  @Override
  public List<Employee> fetchAllEmployees() {
    return bar.fetchAllEmployees();
  }

  @Override
  public Employee fetchEmployeeById(Integer id) {
    return bar.fetchEmployeeById(id);
  }

  @Override
  public Response<Employee> insertEmployee(Employee employee) {
    IValidator validator = new EmployeeValidator(employee);

    if(!validator.validate()){
      return Response.of(HttpStatus.BAD_REQUEST, validator.getErrors());
    }

    Integer transactionStatus = bar.insertEmployee(employee);

    if(transactionStatus == 1){
      return Response.of(employee, HttpStatus.OK);
    }else {
      return Response.of(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  public Response<Employee> updateEmployee(Employee employee) {
    Validator validator = new EmployeeValidator(employee);

    if(!validator.validate()){
      return Response.of(HttpStatus.BAD_REQUEST, validator.getErrors());
    }

    Integer transactionStatus = bar.updateEmployee(employee);

    if(transactionStatus == 1){
      return Response.of(employee, HttpStatus.OK);
    }else {
      return Response.of(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  public Integer deleteEmployee(Integer id) {
    return bar.deleteEmployee(id);
  }
}
