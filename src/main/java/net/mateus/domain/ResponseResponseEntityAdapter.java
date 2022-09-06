package net.mateus.domain;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseResponseEntityAdapter extends ResponseEntity {

  private final Response response;

  public ResponseResponseEntityAdapter(Response response) {
    super(response, parseStatustoHttpStatus(response.getStatus()));
    this.response = response;
  }

  public Response getResponse() {
    return response;
  }

  private static HttpStatus parseStatustoHttpStatus(STATUS status) {
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
        return HttpStatus.NOT_FOUND;
      case NOROWSREMOVEDERROR:
        return HttpStatus.NOT_FOUND;
      case NOROWSUPDATEDERROR:
        return HttpStatus.NOT_FOUND;
      default:
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
  }

  /*
  *
  * EXCEPTIONERROR,

  SYSTEMERROR,

  PERSISTENCEERROR,

  VALIDATIONERROR,

  OPTIMISTICLOCKINGERROR,

  VERSIONNOTFOUNDERROR,

  NOROWSFOUNDERROR,

  NOROWSUPDATEDERROR,

  NOROWSREMOVEDERROR,

  NOROWSINSERTEDERROR,

  SINGLETONSELECTERROR,

  QUERYBUILDERERROR,

  UNSPECIFIEDERROR,

  EXTERNALERROR
  * */
}
