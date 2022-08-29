package net.mateus.domain;


import java.util.Collections;
import java.util.List;
import net.mateus.domain.employee.model.Employee;
import org.springframework.http.HttpStatus;

public class Response <R>{

  private List<R> data;
  private HttpStatus httpStatus;
  private final List<ValidationError> validationErrors;

  private Response(List<R> data, HttpStatus aStatus, List<ValidationError> errors) {
    this.data = data;
    this.httpStatus = aStatus;
    this.validationErrors = errors;
  }

  public static <R> Response<R> of(List<R> data, HttpStatus aStatus) {
    return new Response<>(data, aStatus, Collections.emptyList());
  }

  public static <R> Response<R> of(R data, HttpStatus aStatus) {
    return new Response<>(Collections.singletonList(data), aStatus, Collections.emptyList());
  }

  public static <R> Response<R> of(HttpStatus aStatus , List<ValidationError> errors) {
    return new Response<>( null, aStatus, errors);
  }

  public static Response<Employee> of(HttpStatus aStatus) {
    return new Response<>( null, aStatus, Collections.emptyList());
  }

  public List<R> getData() {
    return data;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public List<ValidationError> getValidationErrors() {
    return validationErrors;
  }
}
