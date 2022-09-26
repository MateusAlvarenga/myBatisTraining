package com.qat.employee.domain.employee.model;


public class EmployeeBuilder {

  private Integer id;
  private String name;
  private String phone;
  private String email;
  private String branch;

  private Integer age;

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

  public EmployeeBuilder age(Integer age) {
    this.age = age;
    return this;
  }

  public static EmployeeBuilder builder() {
    return new EmployeeBuilder();
  }

  public Employee build() {
    return new Employee(id, name, phone, email, branch, age);
  }

}
