package cn.edu.bupt.demo.dao.EmergencyTeam;

import cn.edu.bupt.demo.entity.EmergencyTeam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TeamRespository {
    @Select("select * from emergency_team limit #{index},#{pageSize}")
    List<EmergencyTeam> findAllByPage(@Param("index")Integer index, @Param("pageSize")Integer pageSize);

    @Select("select * from emergency_team where category = #{category} limit #{index},#{pageSize}")
    List<EmergencyTeam> findTeamByCategoryAndPage(@Param("category")String category,@Param("index")Integer index, @Param("pageSize")Integer pageSize);


    @Select("select * from emergency_team where team_id = #{team_id}")
    EmergencyTeam findEmergencyTeamById(Integer team_id);

/*    @Select("select * from emergency_team where name = #{name}")
    List<EmergencyTeam> findEmergencyTeamByName(String name);

    @Select("select * from emergency_team where affiliation = #{affiliation}")
    List<EmergencyTeam> findEmergencyTeamByAffiliation(String affiliation);

    @Select("select * from emergency_team where location = #{location}")
    List<EmergencyTeam> findEmergencyTeamByLocation(String location);*/

    @Select("select count(*) from emergency_team")
    Integer AllTeamCount();

    @Select("select count(*) from emergency_team where category = #{category}")
    Integer TeamCountOfCategory(String category);

    @Insert("insert into emergency_team (name,category,level,specialty,introduction,affiliation,principal,phone,location)" +
            " values (#{name},#{category},#{level},#{specialty},#{introduction},#{affiliation},#{principal},#{phone},#{location})")
    @Options(useGeneratedKeys = true,keyProperty="id",keyColumn = "id")
    void save(EmergencyTeam emergencyTeam);

    @Update("update emergency_team set name = #{name},category = #{category},level = #{level},specialty = #{specialty},introduction = #{introduction},affiliation = #{affiliation}, " +
            "principal = #{principal},phone = #{phone},location = #{location} where team_id=#{team_id}")
    void update(EmergencyTeam emergencyTeam);

    @Delete("delete from emergency_team where team_id=#{team_id}")
    void deleteById(Integer team_id);

    @Select("select * from emergency_team where team_id > 0")
    List<EmergencyTeam> findAllTeam();
}
