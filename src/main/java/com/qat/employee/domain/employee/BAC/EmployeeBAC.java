package com.qat.employee.domain.employee.BAC;

import com.qat.employee.domain.employee.model.EmployeeRequest;
import com.qat.employee.domain.employee.model.EmployeeResponse;

public interface EmployeeBAC {

  public EmployeeResponse fetchAllEmployees(EmployeeRequest request);

  public EmployeeResponse fetchEmployeeById(EmployeeRequest request);

  public EmployeeResponse insertEmployee(EmployeeRequest request);

  public EmployeeResponse updateEmployee(EmployeeRequest request);

  public EmployeeResponse deleteEmployee(EmployeeRequest request);
  
  public EmployeeResponse insertEmployeeList(EmployeeRequest request);

}
