package cn.edu.bupt.demo.dao.EntranceWork;

import cn.edu.bupt.demo.entity.EntranceWork;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @author zy
 * @date 2018/10/24 下午4:50
 */

@Mapper
public interface EntranceRepository {

    @Select("select id as id,duration as duration,date as date,work_number as work_number, activity_range as activity_range,evaluation as evaluation from entrance_work limit #{index},#{pageSize}")
    List<EntranceWork> findAllByPage(@Param("index")Integer index,@Param("pageSize")Integer pageSize);

    @Select("select id as id,duration as duration,date as date,work_number as work_number, activity_range as activity_range,evaluation as evaluation from entrance_work where id = #{id}")
    EntranceWork findEntranceWorkById(Integer id);

    @Select("select id as id,duration as duration,date as date,work_number as work_number,activity_range as activity_range,evaluation as evaluation from entrance_work where activity_range = #{activity_range} limit #{index},#{pageSize}")
    List<EntranceWork> findEntranceWorkByRange(@Param("activity_range")String activity_range,@Param("index")Integer index,@Param("pageSize")Integer pageSize);

    @Select("select count(*) from entrance_work where activity_range = #{activity_range}")
    Integer WorkCountByRange(String activity_range);

    @Select("select count(*) from entrance_work")
    Integer AllWorkCount();

    @Insert("insert into entrance_work (duration,date,work_number,activity_range,evaluation) values (#{duration},#{date},#{work_number},#{activity_range},#{evaluation})")
    @Options(useGeneratedKeys = true,keyProperty="id")
    void save(EntranceWork entranceWork);

    @Update("update entrance_work set duration = #{duration},date = #{date},work_number = #{work_number},activity_range = #{activity_range},evaluation = #{evaluation} where id=#{id}")
    void update(EntranceWork entranceWork);

    @Update("update entrance_work set evaluation = #{evaluation} where id=#{id}")
    void evaluation(@Param("evaluation")String evaluation, @Param("id")Integer id);

    @Delete("delete from entrance_work where id=#{id}")
    void deleteById(Integer id);

    @Delete("delete from entrance_work where date=#{date}")
    void deleteByDate(Long date);

    @Select("select id as id,duration as duration,date as date,work_number as work_number,activity_range as activity_range,evaluation as evaluation from entrance_work where id > 0")
    List<EntranceWork> findAll();



}
