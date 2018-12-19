package cn.edu.bupt.demo.dao.InspectionReport;

import cn.edu.bupt.demo.entity.InspectionReport;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InspectionRepository {

    @Select("select id as id,duty_person as duty_person,inspection_person as inspection_person,create_date as create_date,calendar_date as calendar_date," +
            "state as state,summary as summary,abnormal as abnormal,maintenance as maintenance from inspection_report where id = #{id}")
    InspectionReport findReportById(Integer id);

    @Select("select id as id,duty_person as duty_person,inspection_person as inspection_person,create_date as create_date,calendar_date as calendar_date," +
            "state as state,summary as summary,abnormal as abnormal,maintenance as maintenance from inspection_report where duty_person = #{duty_person}")
    List<InspectionReport> findReportByDutyPerson(String duty_person);

    @Select("select id as id,duty_person as duty_person,inspection_person as inspection_person,create_date as create_date,calendar_date as calendar_date," +
            "state as state,summary as summary,abnormal as abnormal,maintenance as maintenance from inspection_report where inspection_person = #{inspection_person}")
    List<InspectionReport> findReportByInspectionPerson(String inspection_person);

    @Select("select id as id,duty_person as duty_person,inspection_person as inspection_person,create_date as create_date,calendar_date as calendar_date," +
            "state as state,summary as summary,abnormal as abnormal,maintenance as maintenance from inspection_report where calendar_date = #{calendar_date} limit #{index},#{pageSize}")
    List<InspectionReport> findReportByCreateDate(@Param("calendar_date") Long calendar_date,@Param("index")Integer index,@Param("pageSize")Integer pageSize);

    @Select("select count(*) from inspection_report")
    Integer findAllCount();

    @Insert("insert into inspection_report (duty_person,inspection_person,create_date,calendar_date,state,summary,abnormal,maintenance) " +
                                "values (#{duty_person},#{inspection_person},#{create_date},#{calendar_date},#{state},#{summary},#{abnormal},#{maintenance})")
    @Options(useGeneratedKeys = true,keyProperty="id")
    void save(InspectionReport inspectionReport);

    @Update("update inspection_report set duty_person = #{duty_person},inspection_person = #{inspection_person},create_date = #{create_date}," +
            "calendar_date = #{calendar_date},state = #{state},summary = #{summary},abnormal = #{abnormal},maintenance = #{maintenance} where id=#{id}")
    void update(InspectionReport inspectionReport);

    @Delete("delete from inspection_report where id=#{id}")
    void deleteById(Integer id);

    @Delete("delete from inspection_report where inspection_person=#{inspection_person}")
    void deleteByInspectionPerson(String inspection_person);

    @Select("select id as id,duty_person as duty_person,inspection_person as inspection_person,create_date as create_date,calendar_date as calendar_date," +
            "state as state,summary as summary,abnormal as abnormal,maintenance as maintenance from inspection_report where id > 0")
    List<InspectionReport> findAll();

}
