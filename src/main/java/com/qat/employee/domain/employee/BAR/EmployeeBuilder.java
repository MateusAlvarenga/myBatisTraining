package com.qat.employee.domain.employee.BAR;

import com.qat.employee.domain.employee.model.Employee;


public class EmployeeBuilder {

  private Integer id;
  private String name;
  private String phone;
  private String email;
  private String branch;

  public EmployeeBuilder id(Integer id) {
    this.id = id;
    return this;
  }

  public EmployeeBuilder name(String name) {
    this.name = name;
    return this;
  }

  public EmployeeBuilder phone(String phone) {
    this.phone = phone;
    return this;
  }

  public EmployeeBuilder email(String email) {
    this.email = email;
    return this;
  }

  public EmployeeBuilder branch(String branch) {
    this.branch = branch;
    return this;
  }

  public static EmployeeBuilder builder() {
    return new EmployeeBuilder();
  }

  public Employee build() {
    return new Employee(id, name, phone, email, branch);
  }

}
