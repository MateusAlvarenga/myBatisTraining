package net.mateus.domain;


import java.util.Collections;
import java.util.List;
import java.util.Objects;
import net.mateus.domain.employee.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response <R>{

  private List<R> data;
  private HttpStatus httpStatus;
  private final List<ValidationError> validationErrors;

  private static final List EMPTY_LIST = Collections.emptyList();

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

  public ResponseEntity<?> toResponseEntity() {
    return new ResponseEntity<>(this, httpStatus);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Response<?> response = (Response<?>) o;
    return Objects.equals(data, response.data) && httpStatus == response.httpStatus
        && Objects.equals(validationErrors, response.validationErrors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, httpStatus, validationErrors);
  }
}
