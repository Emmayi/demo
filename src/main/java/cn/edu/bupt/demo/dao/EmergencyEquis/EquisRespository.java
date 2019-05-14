package cn.edu.bupt.demo.dao.EmergencyEquis;

import cn.edu.bupt.demo.entity.EmergencyEquis;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface EquisRespository {

    @Select("select * from emergency_equis limit #{index},#{pageSize}")
    List<EmergencyEquis> findAllByPage(@Param("index")Integer index, @Param("pageSize")Integer pageSize);

    @Select("select * from emergency_equis where equis_id = #{equis_id}")
    EmergencyEquis findEmergencyEquisById(Integer equis_id);

    @Select("select * from emergency_equis where name = #{name}")
    List<EmergencyEquis> findEmergencyEquisByName(String name);

    @Select("select * from emergency_equis where affiliation = #{affiliation}")
    List<EmergencyEquis> findEmergencyEquisByAffiliation(String affiliation);

    @Select("select * from emergency_equis where location = #{location}")
    List<EmergencyEquis> findEmergencyEquisByLocation(String location);

    @Select("select count(*) from emergency_equis")
    Integer AllEquisCount();

    @Insert("insert into emergency_equis (name,category,quantity,model,purchase_date,manufacturer,manufacture_date,valid_until,use_description,performance_description,affiliation,location)" +
            " values (#{name},#{category},#{quantity},#{model},#{purchase_date},#{manufacturer},#{manufacture_date},#{valid_until},#{use_description},#{performance_description},#{affiliation},#{location})")
    @Options(useGeneratedKeys = true,keyProperty="id")
    void save(EmergencyEquis emergencyEquis);

    @Update("update emergency_equis set name = #{name},category = #{category},quantity = #{quantity},model = #{model},purchase_date = #{purchase_date},manufacturer = #{manufacturer}, " +
            "manufacture_date = #{manufacture_date},valid_until = #{valid_until},use_description = #{use_description},performance_description = #{performance_description},affiliation = #{affiliation},location = #{location} where equis_id=#{equis_id}")
    void update(EmergencyEquis emergencyEquis);

    @Delete("delete from emergency_equis where equis_id=#{equis_id}")
    void deleteById(Integer equis_id);

    @Select("select * from emergency_equis where equis_id > 0")
    List<EmergencyEquis> findAllEquis();

}

