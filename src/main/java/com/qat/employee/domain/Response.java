package com.qat.employee.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.springframework.http.ResponseEntity;

public abstract class Response<R> {

  private List<R> data;
  private STATUS status;
  private List<ValidationError> messages;

  protected static final List EMPTY_LIST = Collections.emptyList();

  public abstract Response withData(List<R> data);

  public abstract Response withData(R data);

  public abstract Response withStatus(STATUS status);

  public abstract Response withMessages(List<ValidationError> messages);

  public Response withException(Exception e) {
    return withStatus(STATUS.EXCEPTIONERROR);
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

  public void setData(List<R> data) {
    this.data = data;
  }

  public void setStatus(STATUS status) {
    this.status = status;
  }

  public void setMessages(List<ValidationError> messages) {
    this.messages = messages;
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
