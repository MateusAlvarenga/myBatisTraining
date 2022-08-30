package net.mateus.domain.employee.BAR;

import java.util.List;
import net.mateus.domain.employee.model.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface EmployeeMapper {

    @Insert("INSERT INTO employee(name, phone, email, branch) " +
            " VALUES (#{name}, #{phone}, #{email}, #{branch})")
    int insert(Employee employee);

    @Select("SELECT * FROM employee")
    List<Employee> fetchAll();

    @Select("SELECT * FROM public.employee WHERE id = #{id}")
    Employee findById(@Param("id") long id);

    @Update("Update employee set name=#{name}, " +
            " phone=#{phone}, email=#{email}, branch = #{branch} where id=#{id}")
    int update(Employee employee);

    @Delete("DELETE FROM employee WHERE id = #{id}")
    int deleteById(@Param("id") long id);

}
