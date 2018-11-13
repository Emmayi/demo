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

    @Select("select id as id,gender as gender,phone as phone,name as name from staff_number where id = #{id}")
    StaffNumber findStaffById(Integer id);

    @Select("select id as id,gender as gender,phone as phone,name as name from staff_number where name = #{name}")
    List<StaffNumber> findStaffByName(String name);

    @Select("select name as name from staff_number where id = #{id}")
    String findStaffName(Integer id);

    @Select("select count(*) from staff_number")
    Integer findAllCount();

    @Insert("insert into staff_number (gender,phone,name) values (#{gender},#{phone},#{name})")
    @Options(useGeneratedKeys = true,keyProperty="id")
    void save(StaffNumber staffNumber);

    @Update("update staff_number set gender = #{gender},phone = #{phone},name = #{name} where id=#{id}")
    void update(StaffNumber staffNumber);

    @Delete("delete from staff_number where id=#{id}")
    void deleteById(Integer id);

    @Delete("delete from staff_number where name=#{name}")
    void deleteByName(String name);

    @Select("select id as id,gender as gender,phone as phone,name as name from staff_number where id > 0")
    List<StaffNumber> findAll();

}
