package com.qat.employee.domain.employee.BAR.mapper;

import com.qat.employee.domain.employee.model.Employee;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface EmployeeMapper {

  @Insert("INSERT INTO employee(name, phone, email, branch, age) " +
      " VALUES (#{name}, #{phone}, #{email}, #{branch}, #{age})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  int insert(Employee employee);

  @Select("SELECT id, name, phone, email, branch, age FROM employee")
  List<Employee> fetchAll();

  @Select("SELECT id, name, phone, email, branch, age FROM public.employee WHERE id = #{id}")
  Employee findById(@Param("id") long id);

  @Update("Update employee set name=#{name}, " +
      " phone=#{phone}, email=#{email}, branch = #{branch}, age=#{age} where id=#{id}")
  int update(Employee employee);

  @Delete("DELETE FROM employee WHERE id = #{id}")
  int deleteById(@Param("id") long id);

}
