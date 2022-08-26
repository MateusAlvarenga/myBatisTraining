package net.mateus.domain.employee.BAR;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employee {
    private Integer id;
    private String name;
    private String phone;
    private String email;
    private String branch;
}
