package com.qat.employee.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.springframework.http.ResponseEntity;

@SuppressWarnings("unchecked")
public abstract class Response<R, O> {

  private STATUS status;
  private List<R> data = Collections.emptyList();
  private List<ValidationError> messages =  Collections.emptyList();

  protected static final List<?> EMPTY_LIST = Collections.emptyList();

  public O withData(List<R> data) {
    setData(data);
    return (O) this;
  }

  public O withData(R data) {
    setData(List.of(data));
    return (O) this;
  }

  public O withMessages(List<ValidationError> messages) {
    setMessages(messages);
    return (O) this;
  }

  public O withStatus(STATUS status) {
    setStatus(status);
    return (O) this;
  }

  public O withException(Exception e) {
    setStatus(STATUS.EXCEPTIONERROR);
    return (O) this;
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
    Response<?, ?> response = (Response<?, ?>) o;
    return Objects.equals(data, response.data) && status == response.status
        && Objects.equals(messages, response.messages);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, status, messages);
  }
}
