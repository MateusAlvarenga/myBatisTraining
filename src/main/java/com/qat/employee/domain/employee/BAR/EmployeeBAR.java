package com.qat.employee.domain.employee.BAR;

import com.qat.employee.domain.Response;
import com.qat.employee.domain.employee.model.Employee;
import com.qat.employee.domain.employee.model.EmployeeRequest;
import com.qat.employee.domain.employee.model.EmployeeResponse;

public interface EmployeeBAR {

  public EmployeeResponse fetchAllEmployees(EmployeeRequest request);

  public EmployeeResponse fetchEmployeeById(EmployeeRequest request);

  public EmployeeResponse insertEmployee(EmployeeRequest request);

  public EmployeeResponse updateEmployee(EmployeeRequest request);

  public EmployeeResponse deleteEmployee(EmployeeRequest request);

//  public Integer createNewEmployee(Employee employee);
}
