package com.qat.employee.controllers.web;

import com.qat.employee.domain.common.Response;
import com.qat.employee.domain.employee.BAC.EmployeeBAC;
import com.qat.employee.domain.employee.model.Employee;
import com.qat.employee.domain.employee.model.EmployeeRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeWebController {    

  private final EmployeeBAC bac;
  
  private static final String FORM = "/form";
  private static final String EMP = "emp";
  private static final String UPDATE = "/update";
  private static final String ID = "id";
  private static final String DELETE = "/delete";
  private static final String EMP_LIST = "/emp-list";
  private static final String RESPONSE = "response";
  private static final String EMPLOYEE = "employee";
  private static final String INSERT = "/insert";
  private static final String SLASH = "/";
  private static final String EMPLOYEES = "employees";  
  private static final String TEMPLATE_EMPLOYEE = EMPLOYEE;
  private static final String TMPLATE_EMP_LIST = "emp_list";
  private static final String TEMPLATE_FORM = "form";
  private static final String REDIRECT = "redirect:";

  public EmployeeWebController(EmployeeBAC bac) {
    this.bac = bac;
  }

  @GetMapping(SLASH)
  String emp() {
    return REDIRECT + EMP_LIST;
  }

  @PostMapping(INSERT)
  String insert(@ModelAttribute(EMPLOYEE) Employee employee, Model model) {
    final EmployeeRequest givenRequest = new EmployeeRequest().withData(employee);
    Response<?, ?> response = bac.insertEmployee(givenRequest);
    EmployeeRequest request = new EmployeeRequest();

    model.addAttribute(EMPLOYEES, bac.fetchAllEmployees(request).getData());
    model.addAttribute(RESPONSE, response);
    return TEMPLATE_EMPLOYEE;
  }

  @GetMapping(EMP_LIST)
  String employees(Model model) {
    EmployeeRequest request = new EmployeeRequest();
    model.addAttribute(EMPLOYEES, bac.fetchAllEmployees(request).getData());
    return TEMPLATE_EMPLOYEE;
  }

  @GetMapping(DELETE)
  String delete(@RequestParam(ID) int id) {
    EmployeeRequest request = new EmployeeRequest().withId(id);
    bac.deleteEmployee(request);
    return REDIRECT + EMP_LIST;
  }

  @GetMapping(FORM)
  String updateForm(@RequestParam(ID) int id, Model model) {
    EmployeeRequest request = new EmployeeRequest().withId(id);
    Employee emp = bac.fetchEmployeeById(request).getData().get(0);
    if (emp != null) {
      model.addAttribute(EMP, emp);
    }
    return TEMPLATE_FORM;
  }

  @PostMapping(UPDATE)
  String update(@ModelAttribute(EMPLOYEE) Employee employee, Model model) {
    final EmployeeRequest request = new EmployeeRequest().withData(employee);
    Response<?, ?> response = bac.updateEmployee(request);
    model.addAttribute(EMPLOYEES, bac.fetchAllEmployees(request).getData());
    model.addAttribute(RESPONSE, response);
    return TEMPLATE_EMPLOYEE;
  }

}
