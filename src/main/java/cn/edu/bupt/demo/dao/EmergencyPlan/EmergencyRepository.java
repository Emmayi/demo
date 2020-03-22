package cn.edu.bupt.demo.dao.EmergencyPlan;


import cn.edu.bupt.demo.entity.EmergencyPlan;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zy
 * @date 2018/11/24 下午10:14
 */

@Mapper
public interface EmergencyRepository {

    @Select("select * from emergency_plan limit #{index},#{pageSize}")
    List<EmergencyPlan> findAllByPage(@Param("index") Integer index, @Param("pageSize") Integer pageSize);

    @Select("select * from emergency_plan where level = #{level} limit #{index},#{pageSize}")
    List<EmergencyPlan> findPlanByLevelPage(@Param("level") Integer level, @Param("index") Integer index, @Param("pageSize") Integer pageSize);

    @Select("select * from emergency_plan where emergency_id = #{emergency_id}")
    EmergencyPlan findEmergencyPlanById(Integer emergency_id);

    @Select("select * from emergency_plan where name = #{name}")
    List<EmergencyPlan> findEmergencyPlanByName(String name);

    @Select("select * from emergency_plan where level = #{level}")
    List<EmergencyPlan> findEmergencyPlanByLevel(Integer level);

    @Select("select * from emergency_plan where signer = #{signer}")
    List<EmergencyPlan> findEmergencyPlanBySigner(String signer);

    @Select("select count(*) from emergency_plan")
    Integer AllPlanCount();

    @Select("select count(*) from emergency_plan where level = #{level}")
    Integer AllPlanPageCount(Integer level);

    @Insert("insert into emergency_plan (name,category,level,associated_event_type,content,department,release_date,release_number,issued,signer,file)" +
            " values (#{name},#{category},#{level},#{associated_event_type},#{content},#{department},#{release_date},#{release_number},#{issued},#{signer},#{file})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void save(EmergencyPlan emergencyPlan);

    @Update("update emergency_plan set name = #{name},category = #{category},level = #{level},associated_event_type = #{associated_event_type},content = #{content}, " +
            "department = #{department},release_date = #{release_date},release_number = #{release_number},issued = #{issued},signer = #{signer},file = #{file} where emergency_id=#{emergency_id}")
    void update(EmergencyPlan emergencyPlan);

    @Delete("delete from emergency_plan where emergency_id=#{emergency_id}")
    void deleteById(Integer emergency_id);

    @Select("select * from emergency_plan where emergency_id > 0")
    List<EmergencyPlan> findAll();

}
