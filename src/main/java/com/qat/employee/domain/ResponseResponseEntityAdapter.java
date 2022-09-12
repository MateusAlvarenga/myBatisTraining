package com.qat.employee.domain;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseResponseEntityAdapter extends ResponseEntity {

  private final Response response;

  public ResponseResponseEntityAdapter(Response response) {
    super(response, parseStatusToHttpStatus(response.getStatus()));
    this.response = response;
  }

  public Response getResponse() {
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
}
