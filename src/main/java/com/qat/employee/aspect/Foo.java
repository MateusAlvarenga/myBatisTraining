package com.qat.employee.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Foo {

  static final Logger logger = LoggerFactory.getLogger(Foo .class);

  public void doThat() {
    logger.info("start");
    //...
    logger.info("finish");
  }
}