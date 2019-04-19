package cn.edu.bupt.demo.dao.InspectionPlan;

import cn.edu.bupt.demo.entity.InspectionPlan;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PlanRepository {
    @Select("select * from inspection_plan where id = #{id}")
    InspectionPlan findPlanById(Integer id);

    @Select("select * from inspection_plan where inspection_person = #{inspection_person}")
    List<InspectionPlan> findPlanByInspectionPerson(String inspection_person);

    @Select("select * from inspection_plan where inspection_date = #{inspection_date}")
    List<InspectionPlan> findPlanByInspectionDate(Long calendar_date);

    @Select("select count(*) from inspection_plan")
    Integer findAllCount();

    @Insert("insert into inspection_plan (inspection_person,create_date,inspection_date) " +
            "values (#{inspection_person},#{create_date},#{inspection_date})")
    @Options(useGeneratedKeys = true,keyProperty="id")
    void save(InspectionPlan inspectionPlan);

    @Update("update inspection_plan set inspection_person = #{inspection_person},create_date = #{create_date}," +
            "inspection_date = #{inspection_date}")
    void update(InspectionPlan inspectionPlan);

    @Delete("delete from inspection_plan where inspection_person=#{inspection_person}")
    void deleteByInspectionPerson(String inspection_person);

    @Select("select * from inspection_report where id > 0")
    List<InspectionPlan> findAll();

}
