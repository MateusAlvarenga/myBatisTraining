package com.qat.employee.domain;


import com.qat.employee.domain.employee.model.Employee;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.springframework.http.ResponseEntity;

public class Response<R> {

  private List<R> data;
  private STATUS status;
  private final List<ValidationError> messages;

  protected static final List EMPTY_LIST = Collections.emptyList();

  public Response(List<R> data, STATUS aStatus, List<ValidationError> errors) {
    this.data = data;
    this.status = aStatus;
    this.messages = errors;
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
