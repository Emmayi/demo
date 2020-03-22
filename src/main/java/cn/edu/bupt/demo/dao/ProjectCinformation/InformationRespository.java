package cn.edu.bupt.demo.dao.ProjectCinformation;

import cn.edu.bupt.demo.entity.ProjectCinformation;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InformationRespository {
    @Select("select * from project_cinformation limit #{index},#{pageSize}")
    List<ProjectCinformation> findAllByPage(@Param("index") Integer index, @Param("pageSize") Integer pageSize);

    @Select("select * from project_cinformation where project_id = #{project_id}")
    ProjectCinformation findProjectById(Integer project_id);

    @Select("select * from project_cinformation where department = #{department}")
    List<ProjectCinformation> findProjectByDepartment(String department);

    @Select("select * from project_cinformation where location = #{location}")
    List<ProjectCinformation> findProjectByLocation(String location);

    @Select("select count(*) from project_cinformation")
    Integer AllProjectCount();

    @Insert("insert into project_cinformation (location,area,drawpoint,mainequipments,proequipments,department,mainusage)" +
            " values (#{location},#{area},#{drawpoint},#{mainequipments},#{proequipments},#{department},#{mainusage})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void save(ProjectCinformation projectCinformation);

    @Update("update project_cinformation set location = #{location},area = #{area},drawpoint = #{drawpoint},mainequipments = #{mainequipments},proequipments = #{proequipments},department = #{department}, " +
            "mainusage = #{mainusage} where project_id=#{project_id}")
    void update(ProjectCinformation projectCinformation);

    @Delete("delete from project_cinformation where project_id=#{project_id}")
    void deleteById(Integer project_id);

    @Select("select * from project_cinformation where project_id > 0")
    List<ProjectCinformation> findAllProject();
}
