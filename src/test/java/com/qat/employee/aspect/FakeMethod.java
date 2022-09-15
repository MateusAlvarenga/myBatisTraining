package com.qat.employee.aspect;


import java.lang.reflect.Method;

public class FakeMethod {

  public Method getMethod() {
    try {
      return getClass().getDeclaredMethod("someMethod");
    } catch (NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
  }

  public void someMethod() {
  }

}
