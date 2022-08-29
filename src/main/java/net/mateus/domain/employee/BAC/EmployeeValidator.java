package net.mateus.domain.employee.BAC;

import java.util.regex.Pattern;
import net.mateus.domain.employee.model.Employee;
import net.mateus.domain.Validator;

public class EmployeeValidator extends Validator {

  public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
      Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

  private final Employee employee;

  public EmployeeValidator(Employee aEmployee) {
    super();
    employee = aEmployee;
  }

  @Override
  public boolean validate() {

    if (employee.getName() == null || employee.getName().isEmpty()) {
      addError("name", "Name is required");
    }

    if(employee.getName() != null && employee.getName().length() > 50) {
      addError("name", "Name must be less than 50 characters");
    }

    if (employee.getPhone() == null || employee.getPhone().isEmpty()) {
      addError("phone", "Phone is required");
    }

    if(employee.getPhone() != null && employee.getPhone().length() > 20) {
      addError("phone", "Phone must be less than 20 characters");
    }

    if (employee.getEmail() == null || employee.getEmail().isEmpty()) {
      addError("email", "Email is required");
    }

    if(employee.getEmail() != null && employee.getEmail().length() > 50) {
      addError("email", "Email must be less than 50 characters");
    }

    if (employee.getBranch() == null || employee.getBranch().isEmpty()) {
      addError("branch", "Branch is required");
    }

    if(employee.getBranch() != null && employee.getBranch().length() > 50) {
      addError("branch", "Branch must be less than 50 characters");
    }

    if(!VALID_EMAIL_ADDRESS_REGEX.matcher(employee.getEmail()).find()) {
      addError("email", "Email is not valid");
    }

    return getErrors().isEmpty();
  }
}
