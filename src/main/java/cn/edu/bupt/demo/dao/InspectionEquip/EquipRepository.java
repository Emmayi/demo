package cn.edu.bupt.demo.dao.InspectionEquip;

import cn.edu.bupt.demo.entity.InspectionEquip;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface EquipRepository {

    @Select("select * from inspection_report limit #{index},#{pageSize}")
    List<InspectionEquip> findEquipInspectionPlanByPage(@Param("index")Integer index,@Param("pageSize")Integer pageSize);

    @Select("select * from inspection_equip where id = #{id}")
    InspectionEquip findPlanById(Integer id);

    @Select("select * from inspection_equip where name = #{name}")
    List<InspectionEquip> findPlanByEquipName(String name);

    @Select("select count(*) from inspection_equip")
    Integer findAllCount();

    @Insert("insert into inspection_equip (name,content,period,last_time) " +
            "values (#{name},#{content},#{period}),#{last_time}")
    @Options(useGeneratedKeys = true,keyProperty="id")
    void save(InspectionEquip inspectionEquip);

    @Update("update inspection_equip set name = #{name},content = #{content}," +
            "period = #{period},last_time = #{last_time} where id = #{id}")
    void update(InspectionEquip inspectionPlan);

    @Delete("delete from inspection_equip where id=#{id}")
    void deleteById(Integer id);

    @Select("select * from inspection_equip where id > 0")
    List<InspectionEquip> findAll();

}
