package com.qat.employee.domain;


import com.qat.employee.domain.employee.model.Employee;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.springframework.http.ResponseEntity;

public class Response <R>{

  private List<R> data;
  private STATUS status;
  private final List<ValidationError> messages;

  private static final List EMPTY_LIST = Collections.emptyList();

  private Response(List<R> data, STATUS aStatus, List<ValidationError> errors) {
    this.data = data;
    this.status = aStatus;
    this.messages = errors;
  }

  public static <R> Response<R> of(List<R> data, STATUS aStatus) {
    return new Response<>(data, aStatus, EMPTY_LIST);
  }

  public static <R> Response<R> of(R data, STATUS aStatus) {
    return new Response<>(Collections.singletonList(data), aStatus, EMPTY_LIST);
  }

  public static <R> Response<R> of(STATUS aStatus , List<ValidationError> errors) {
    return new Response<>( EMPTY_LIST, aStatus, errors);
  }

  public static Response<Employee> of(STATUS aStatus) {
    return new Response<>( EMPTY_LIST, aStatus, EMPTY_LIST);
  }

  public static Response<Employee> of(Exception exception) {
    return  new Response<>(EMPTY_LIST, STATUS.EXCEPTIONERROR, Collections.singletonList( ValidationError.of("",exception.getMessage())));
  }


  public List<R> getData() {
    return data;
  }

  public STATUS getStatus() {
    return status;
  }

  public List<ValidationError> getMessages() {
    return messages;
  }

  public ResponseEntity<?> toResponseEntity() {
    return new ResponseResponseEntityAdapter(this);
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
    return Objects.equals(data, response.data) && status == response.status
        && Objects.equals(messages, response.messages);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, status, messages);
  }
}
