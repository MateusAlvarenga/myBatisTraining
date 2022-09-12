package com.qat.employee.domain.employee.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.qat.employee.domain.Response;
import com.qat.employee.domain.STATUS;
import com.qat.employee.domain.employee.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class ResponseResponseEntityAdapter {

  @Test
  public  void testOK(){
    final Employee employeExpected =
        new Employee(1, "Mateus", "222", "", "IT");
    final Response<Employee> responseExpected = Response.of(employeExpected, STATUS.OPERATIONSUCCESS);
    final ResponseEntity responseEntityExpected =  ResponseEntity.ok(responseExpected);

    final ResponseEntity responseEntityActual =  Response.of(employeExpected,STATUS.OPERATIONSUCCESS).toResponseEntity();

    assertEquals(responseEntityExpected.getBody(), responseEntityActual.getBody());
    assertEquals(responseEntityExpected.getStatusCode(), responseEntityActual.getStatusCode());
  }

  @Test
  public  void testEXPECTATION_FAILED(){
    final Employee employeExpected =
        new Employee(1, "Mateus", "222", "", "IT");
    final Response<Employee> responseExpected = Response.of(employeExpected, STATUS.EXCEPTIONERROR);
    final ResponseEntity responseEntityExpected =  new ResponseEntity(responseExpected, HttpStatus.EXPECTATION_FAILED);

    final ResponseEntity responseEntityActual =  Response.of(employeExpected,STATUS.EXCEPTIONERROR).toResponseEntity();

    assertEquals(responseEntityExpected.getBody(), responseEntityActual.getBody());
    assertEquals(responseEntityExpected.getStatusCode(), responseEntityActual.getStatusCode());
  }

  @Test
  public  void testPERSISTENCEERROR(){
    final Employee employeExpected =
        new Employee(1, "Mateus", "222", "", "IT");
    final Response<Employee> responseExpected = Response.of(employeExpected, STATUS.PERSISTENCEERROR);
    final ResponseEntity responseEntityExpected =  new ResponseEntity(responseExpected,  HttpStatus.CONFLICT);

    final ResponseEntity responseEntityActual =  Response.of(employeExpected,STATUS.PERSISTENCEERROR).toResponseEntity();

    assertEquals(responseEntityExpected.getBody(), responseEntityActual.getBody());
    assertEquals(responseEntityExpected.getStatusCode(), responseEntityActual.getStatusCode());
  }

  @Test
  public  void testVALIDATIONERROR(){
    final Employee employeExpected =
        new Employee(1, "Mateus", "222", "", "IT");
    final Response<Employee> responseExpected = Response.of(employeExpected, STATUS.VALIDATIONERROR);
    final ResponseEntity responseEntityExpected =  new ResponseEntity(responseExpected,  HttpStatus.BAD_REQUEST);

    final ResponseEntity responseEntityActual =  Response.of(employeExpected,STATUS.VALIDATIONERROR).toResponseEntity();

    assertEquals(responseEntityExpected.getBody(), responseEntityActual.getBody());
    assertEquals(responseEntityExpected.getStatusCode(), responseEntityActual.getStatusCode());
  }

  @Test
  public  void testNOROWSFOUNDERROR(){
    final Employee employeExpected =
        new Employee(1, "Mateus", "222", "", "IT");
    final Response<Employee> responseExpected = Response.of(employeExpected, STATUS.NOROWSFOUNDERROR);
    final ResponseEntity responseEntityExpected =  new ResponseEntity(responseExpected,  HttpStatus.NOT_FOUND);

    final ResponseEntity responseEntityActual =  Response.of(employeExpected,STATUS.NOROWSFOUNDERROR).toResponseEntity();

    assertEquals(responseEntityExpected.getBody(), responseEntityActual.getBody());
    assertEquals(responseEntityExpected.getStatusCode(), responseEntityActual.getStatusCode());
  }

  @Test
  public  void testUNSPECIFIEDERROR(){
    final Employee employeExpected =
        new Employee(1, "Mateus", "222", "", "IT");
    final Response<Employee> responseExpected = Response.of(employeExpected, STATUS.UNSPECIFIEDERROR);
    final ResponseEntity responseEntityExpected =  new ResponseEntity(responseExpected,  HttpStatus.INTERNAL_SERVER_ERROR);

    final ResponseEntity responseEntityActual =  Response.of(employeExpected,STATUS.UNSPECIFIEDERROR).toResponseEntity();

    assertEquals(responseEntityExpected.getBody(), responseEntityActual.getBody());
    assertEquals(responseEntityExpected.getStatusCode(), responseEntityActual.getStatusCode());
  }

}
