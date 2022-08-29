package net.mateus.domain.employee.model;

import java.util.Objects;

public class Employee {
    private Integer id;
    private String name;
    private String phone;
    private String email;
    private String branch;

    public Employee(Integer id, String name, String phone, String email, String branch) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.branch = branch;
    }

    public Employee() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Employee{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", branch='").append(branch).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(name, employee.name)
            && Objects.equals(phone, employee.phone) && Objects.equals(email,
            employee.email) && Objects.equals(branch, employee.branch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, email, branch);
    }
}
