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
  private final List<ValidationError> messages;

  private static final List EMPTY_LIST = Collections.emptyList();

  private Response(List<R> data, HttpStatus aStatus, List<ValidationError> errors) {
    this.data = data;
    this.httpStatus = aStatus;
    this.messages = errors;
  }

  public static <R> Response<R> of(List<R> data, HttpStatus aStatus) {
    return new Response<>(data, aStatus, EMPTY_LIST);
  }

  public static <R> Response<R> of(R data, HttpStatus aStatus) {
    return new Response<>(Collections.singletonList(data), aStatus, EMPTY_LIST);
  }

  public static <R> Response<R> of(HttpStatus aStatus , List<ValidationError> errors) {
    return new Response<>( EMPTY_LIST, aStatus, errors);
  }

  public static Response<Employee> of(HttpStatus aStatus) {
    return new Response<>( EMPTY_LIST, aStatus, EMPTY_LIST);
  }

  public List<R> getData() {
    return data;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public List<ValidationError> getMessages() {
    return messages;
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
        && Objects.equals(messages, response.messages);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, httpStatus, messages);
  }
}
