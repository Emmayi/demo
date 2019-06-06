package cn.edu.bupt.demo.dao.EmergencyPlace;

import cn.edu.bupt.demo.entity.EmergencyPlace;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PlaceRespository {
    @Select("select * from emergency_place limit #{index},#{pageSize}")
    List<EmergencyPlace> findAllByPage(@Param("index")Integer index, @Param("pageSize")Integer pageSize);

    @Select("select*from emergency_place where category = #{category} limit #{index},#{pageSize}")
    List<EmergencyPlace> findPlaceByCategoryAndPage(@Param("category")String category,@Param("index")Integer index,@Param("pageSize")Integer pageSize);

    @Select("select * from emergency_place where place_id = #{place_id}")
    EmergencyPlace findEmergencyPlaceById(Integer place_id);

/*    @Select("select * from emergency_place where name = #{name}")
    List<EmergencyPlace> findEmergencyPlaceByName(String name);

    @Select("select * from emergency_place where affiliation = #{affiliation}")
    List<EmergencyPlace> findEmergencyPlaceByAffiliation(String affiliation);

    @Select("select * from emergency_place where location = #{location}")
    List<EmergencyPlace> findEmergencyPlaceByLocation(String location);*/

    @Select("select count(*) from emergency_place")
    Integer AllPlaceCount();

    @Select("select count(*) from emergency_place where category = #{category}")
    Integer PlaceCountOfCategory(String category);

    @Insert("insert into emergency_place (name,category,location,area,capacity,introduction,affiliation,principal,telephone,cellphone)" +
            " values (#{name},#{category},#{location},#{area},#{capacity},#{introduction},#{affiliation},#{principal},#{telephone},#{cellphone})")
    @Options(useGeneratedKeys = true,keyProperty="id")
    void save(EmergencyPlace emergencyPlace);

    @Update("update emergency_place set name = #{name},category = #{category},location = #{location},area = #{area},capacity = #{capacity},introduction = #{introduction},affiliation = #{affiliation}, " +
            "principal = #{principal},telephone = #{telephone},cellphone = #{cellphone} where place_id=#{place_id}")
    void update(EmergencyPlace emergencyPlace);

    @Delete("delete from emergency_place where place_id=#{place_id}")
    void deleteById(Integer place_id);

    @Select("select * from emergency_place where place_id > 0")
    List<EmergencyPlace> findAllPlace();
}
