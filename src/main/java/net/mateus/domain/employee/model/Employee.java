package net.mateus.domain.employee.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {
    private Integer id;
    private String name;
    private String phone;
    private String email;
    private String branch;
}
