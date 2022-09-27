package com.qat.employee.domain.common;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseResponseEntityAdapter extends ResponseEntity<Object> {

  private final Response<?, ?> response;

  public ResponseResponseEntityAdapter(Response<?, ?> response) {
    super(response, parseStatusToHttpStatus(response.getStatus()));
    this.response = response;
  }

  public Response<?, ?> getResponse() {
    return response;
  }

  private static HttpStatus parseStatusToHttpStatus(STATUS status) {
    switch (status) {
      case OPERATIONSUCCESS:
        return HttpStatus.OK;
      case EXCEPTIONERROR:
        return HttpStatus.EXPECTATION_FAILED;
      case PERSISTENCEERROR:
        return HttpStatus.CONFLICT;
      case VALIDATIONERROR:
        return HttpStatus.BAD_REQUEST;
      case NOROWSFOUNDERROR:
      case NOROWSREMOVEDERROR:
      case NOROWSUPDATEDERROR:
        return HttpStatus.NOT_FOUND;
      default:
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
  }

@Override
public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + Objects.hash(response);
    return result;
}

@Override
public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (!super.equals(obj))
        return false;
    if (getClass() != obj.getClass())
        return false;
    ResponseResponseEntityAdapter other = (ResponseResponseEntityAdapter) obj;
    return Objects.equals(response, other.response);
}
  
  
}
