package cn.edu.bupt.demo.dao.InspectionReport;

import cn.edu.bupt.demo.entity.InspectionReport;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InspectionRepository {

    @Select("select * from inspection_report where id = #{id}")
    InspectionReport findReportById(Integer id);

    @Select("select * from inspection_report where duty_person = #{duty_person}")
    List<InspectionReport> findReportByDutyPerson(String duty_person);

    @Select("select * from inspection_report where plan_id = #{plan_id}")
    List<InspectionReport> findReportByPlanId(Integer plan_id);

    @Select("select * from inspection_report where calendar_date = #{calendar_date} limit #{index},#{pageSize}")
    List<InspectionReport> findReportByCreateDate(@Param("calendar_date") Long calendar_date,
                                                  @Param("index")Integer index,
                                                  @Param("pageSize")Integer pageSize);

    @Select("select * from inspection_report limit #{index},#{pageSize}")
    List<InspectionReport> findReportByPage(@Param("index")Integer index,@Param("pageSize")Integer pageSize);

    @Select("select count(*) from inspection_report")
    Integer findAllCount();

    @Select("select count(*) from inspection_report where calendar_date = #{calendar_date}")
    Integer findDayCount(Long calendar_date);

    @Insert("insert into inspection_report (duty_person,inspection_person,create_date,calendar_date,state,summary,abnormal,maintenance,image,video,plan_id) " +
                                "values (#{duty_person},#{inspection_person},#{create_date},#{calendar_date},#{state},#{summary},#{abnormal},#{maintenance},#{image},#{video},#{plan_id})")
    @Options(useGeneratedKeys = true,keyProperty="id")
    void save(InspectionReport inspectionReport);

    @Update("update inspection_report set duty_person = #{duty_person},inspection_person = #{inspection_person},create_date = #{create_date}," +
            "calendar_date = #{calendar_date},state = #{state},summary = #{summary},abnormal = #{abnormal},maintenance = #{maintenance},plan_id=#{plan_id} where id=#{id}")
    void update(InspectionReport inspectionReport);

    @Delete("delete from inspection_report where id=#{id}")
    void deleteById(Integer id);

    @Delete("delete from inspection_report where inspection_person=#{inspection_person}")
    void deleteByInspectionPerson(String inspection_person);

    @Select("select * from inspection_report where id > 0")
    List<InspectionReport> findAll();



}
