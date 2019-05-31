package cn.edu.bupt.demo.dao.DaliyInspection;

import cn.edu.bupt.demo.entity.DailyInspection;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DaliyRepository {
    @Select("select * from inspection_daily where id = #{id}")
    DailyInspection findTableById(Integer id);

    @Select("select * from inspection_daily where inspection_person = #{inspection_person} limit #{index},#{pageSize}")
    List<DailyInspection> findTableByInspectionPerson(@Param("inspection_person")String inspection_person, @Param("index")Integer index, @Param("pageSize")Integer pageSize);

    @Select("select * from inspection_daily limit #{index},#{pageSize}")
    List<DailyInspection> findAllByPage(@Param("index")Integer index, @Param("pageSize")Integer pageSize);


    @Select("select count(*) from inspection_daily")
    Integer findAllCount();

    @Select("select count(*) from inspection_daily where inspection_person=#{inspection_person}")
    Integer findCountByPerson(String inspection_person);

    @Insert("insert into inspection_daily (time,inspection_person,path,work) " +
            "values (#{time},#{inspection_person},#{path},#{work})")
    @Options(useGeneratedKeys = true,keyProperty="id",keyColumn = "id")
    void save(DailyInspection inspectionPlan);

    @Update("update inspection_daily set time = #{time},inspection_person = #{inspection_person},path = #{path}," +
            "work = #{work}")
    void update(DailyInspection inspectionPlan);

    @Delete("delete from inspection_daily where id=#{id}")
    void deleteById(Integer id);

    @Select("select * from inspection_daily where id > 0")
    List<DailyInspection> findAll();
}
