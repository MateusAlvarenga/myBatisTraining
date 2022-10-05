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

  @Select("SELECT id, name, phone, email, branch, age FROM employee")
  List<Employee> fetchAll();

  @Select("SELECT id, name, phone, email, branch, age FROM public.employee WHERE id = #{id}")
  Employee findById(@Param("id") long id);

}
