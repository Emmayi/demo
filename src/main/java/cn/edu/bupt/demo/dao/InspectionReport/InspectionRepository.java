package cn.edu.bupt.demo.dao.InspectionReport;

import cn.edu.bupt.demo.entity.InspectionReport;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InspectionRepository {

    @Select("select id as id,duty_person as duty_person,inspection_person as inspection_person,date as date," +
            "condition as condition,summary as summary,abnormal as abnormal,maintenance as maintenance from inspection_report where id = #{id}")
    InspectionReport findReportById(Integer id);

    @Select("select id as id,duty_person as duty_person,inspection_person as inspection_person,date as date," +
            "condition as condition,summary as summary,abnormal as abnormal,maintenance as maintenance from inspection_report where duty_person = #{duty_person}")
    List<InspectionReport> findReportByDutyPerson(String duty_person);

    @Select("select id as id,duty_person as duty_person,inspection_person as inspection_person,date as date," +
            "condition as condition,summary as summary,abnormal as abnormal,maintenance as maintenance from inspection_report where inspection_person = #{inspection_person}")
    List<InspectionReport> findReportByInspectionPerson(String inspection_person);

    @Select("select id as id,duty_person as duty_person,inspection_person as inspection_person,date as date," +
            "condition as condition,summary as summary,abnormal as abnormal,maintenance as maintenance from inspection_report where condition = #{condition}")
    List<InspectionReport> findReportByCondition(String condition);

    @Select("select count(*) from inspection_report")
    Integer findAllCount();

    @Insert("insert into inspection_report (duty_person,inspection_person,date,condition,summary,abnormal,maintenance) " +
            "values (#{duty_person},#{inspection_person},#{date},#{condition},#{summary},#{summary},#{maintenance})")
    @Options(useGeneratedKeys = true,keyProperty="id")
    void save(InspectionReport inspectionReport);

    @Update("update inspection_report set duty_person = #{duty_person},inspection_person = #{inspection_person}," +
            "date = #{date} condition = #{condition} summary = #{summary} abnormal = #{abnormal} maintenance = #{maintenance} where id=#{id}")
    void update(InspectionReport inspectionReport);

    @Delete("delete from inspection_report where id=#{id}")
    void deleteById(Integer id);

    @Delete("delete from inspection_report where inspection_person=#{inspection_person}")
    void deleteByInspectionPerson(String inspection_person);

    @Select("select id as id,duty_person as duty_person,inspection_person as inspection_person,date as date," +
            "condition as condition,summary as summary,abnormal as abnormal,maintenance as maintenance from inspection_report where id > 0")
    List<InspectionReport> findAll();

}
