package net.mateus;

import net.mateus.domain.employee.BAC.EmployeeBAC;
import net.mateus.domain.employee.BAC.EmployeeBACImpl;
import net.mateus.domain.employee.BAR.EmployeeBARImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootmybatiscrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootmybatiscrudApplication.class, args);
    }

}