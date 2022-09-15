package com.qat.employee.aspect;

public class FakeJoinPoint {
  private String name;

  public FakeJoinPoint(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String toString() {
    return name;
  }
}