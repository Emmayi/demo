package cn.edu.bupt.demo.dao.InspectionPlan;

import cn.edu.bupt.demo.entity.InspectionPlan;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface PlanRepository {
    @Select("select * from inspection_plan where id = #{id}")
    InspectionPlan findPlanById(Integer id);

    @Select("select * from inspection_plan where inspection_person = #{inspection_person} limit #{index},#{pageSize}")
    List<InspectionPlan> findPlanByInspectionPerson(@Param("inspection_person") String inspection_person,
                                                    @Param("index")Integer index,
                                                    @Param("pageSize")Integer pageSize);

    @Select("select * from inspection_plan limit #{index},#{pageSize}")
    List<InspectionPlan> findAllPlanByPage(@Param("index")Integer index,
                                           @Param("pageSize")Integer pageSize);

    @Select("select * from inspection_plan where inspection_date = #{inspection_date}")
    List<InspectionPlan> findPlanByInspectionDate(Long calendar_date);

    @Select("select count(*) from inspection_plan")
    Integer findAllCount();

    @Select("select count(*) from inspection_plan where inspection_person = #{inspection_person}")
    Integer findCountOfPerson(String inspection_person);

    @Insert("insert into inspection_plan (inspection_person,create_date,inspection_date,content,status) " +
            "values (#{inspection_person},#{create_date},#{inspection_date},#{content}),#{status})")
    @Options(useGeneratedKeys = true,keyProperty="id")
    void save(InspectionPlan inspectionPlan);

    @Update("update inspection_plan set inspection_person = #{inspection_person},create_date = #{create_date}," +
            "inspection_date = #{inspection_date},content = #{content},status = #{status}")
    void update(InspectionPlan inspectionPlan);

    @Update("update inspection_plan set status = '已完成' where id = #{id}")
    void updateStatus(Integer id);

    @Delete("delete from inspection_plan where id=#{id}")
    void deleteById(Integer id);

    @Select("select * from inspection_plan where id > 0")
    List<InspectionPlan> findAll();

}
