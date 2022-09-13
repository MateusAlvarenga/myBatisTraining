package com.qat.employee.domain;

import java.util.Objects;

public abstract class Request<T, ID> {

  private final ID id;
  private final T data;

  public Request(ID id, T data) {
    this.id = id;
    this.data = data;
  }

  public ID getId() {
    return id;
  }

  public T getData() {
    return data;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Request{");
    sb.append("id=").append(id);
    sb.append(", data=").append(data);
    sb.append('}');
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Request)) {
      return false;
    }
    Request<?, ?> request = (Request<?, ?>) o;
    return Objects.equals(id, request.id) && Objects.equals(data, request.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, data);
  }

}
