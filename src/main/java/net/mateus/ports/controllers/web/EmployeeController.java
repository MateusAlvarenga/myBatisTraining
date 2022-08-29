package net.mateus.ports.controllers.web;

import net.mateus.domain.Response;
import net.mateus.domain.employee.BAC.EmployeeBAC;
import net.mateus.domain.employee.model.Employee;
import net.mateus.domain.employee.BAR.EmployeeMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeController {
    private final EmployeeMapper mapper;
    private final EmployeeBAC bac;

    public EmployeeController(EmployeeMapper mapper, EmployeeBAC bac) {
        this.mapper = mapper;
        this.bac = bac;
    }

    @GetMapping("/")
    private String emp(){
        return "redirect:/emp-list";
    }
    @PostMapping("/insert")
    private String insert(@ModelAttribute("employee") Employee employee, Model model){
        Response response = bac.insertEmployee(employee);
        model.addAttribute("employees", bac.fetchAllEmployees());
        model.addAttribute("response", response);
        return "employee";
    }

    @GetMapping("/emp-list")
    private String employees(Model model){
        model.addAttribute("employees", bac.fetchAllEmployees());
        return "employee";
    }

    @GetMapping("/delete")
    private String delete(@RequestParam("id") int id){
        bac.deleteEmployee(id);
        return "redirect:/emp-list";
    }

    @GetMapping("/form")
    private String updateForm(@RequestParam("id") int id, Model model){
        Employee emp = bac.fetchEmployeeById(id);
        if(emp != null){
            model.addAttribute("emp", emp);
        }
        return "form";
    }

    @PostMapping("/update")
    private String update(@ModelAttribute("employee") Employee employee, Model model){
        Response response = bac.insertEmployee(employee);
        model.addAttribute("employees", bac.fetchAllEmployees());
        model.addAttribute("response", response);
        return "employee";
    }

}
