package com.qat.employee;

import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class SpringbootmybatiscrudApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringbootmybatiscrudApplication.class, args);
  }

  @Bean
  public ServletRegistrationBean h2servletRegistration() {
    ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
    registration.addUrlMappings("/console/*");
    return registration;
  }

}