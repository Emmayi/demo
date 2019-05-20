package cn.edu.bupt.demo.dao.StaffNumber;

import org.apache.ibatis.annotations.*;
import cn.edu.bupt.demo.entity.StaffNumber;
import java.util.List;

/**
 * @author zy
 * @date 2018/10/18 上午11:15
 */

@Mapper
public interface StaffRepository {

    @Select("select * from staff_number where id = #{id}")
    StaffNumber findStaffById(Integer id);

    @Select("select * from staff_number where name = #{name}")
    List<StaffNumber> findStaffByName(String name);

    @Select("select name as name from staff_number where id > #{id}")
    List<String> findAllStaffName(Integer id);

    @Select("select staff_number.email from staff_number left join inspection_plan on " +
            "(inspection_plan.inspection_person = staff_number.name) where staff_number.name = #{name}")
    String findEmailByName(String name);

    @Select("select count(*) from staff_number")
    Integer findAllCount();

    @Insert("insert into staff_number (gender,phone,name,email) values (#{gender},#{phone},#{name},#{email})")
    @Options(useGeneratedKeys = true,keyProperty="id")
    void save(StaffNumber staffNumber);

    @Update("update staff_number set gender = #{gender},phone = #{phone},name = #{name} email=#{email} where id=#{id}")
    void update(StaffNumber staffNumber);

    @Delete("delete from staff_number where id=#{id}")
    void deleteById(Integer id);

    @Delete("delete from staff_number where name=#{name}")
    void deleteByName(String name);

    @Select("select * from staff_number where id > 0")
    List<StaffNumber> findAll();

}
