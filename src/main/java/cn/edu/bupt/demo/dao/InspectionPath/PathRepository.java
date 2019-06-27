package cn.edu.bupt.demo.dao.InspectionPath;

import cn.edu.bupt.demo.entity.InspectionPath;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PathRepository {

    @Select("select * from inspection_path where id = #{id}")
    InspectionPath findPathById(Integer id);

    @Select("select * from inspection_path where area_belong = #{area} limit #{index},#{pageSize}")
    List<InspectionPath> findPathByArea(@Param("area") String area,
                                        @Param("index")Integer index,
                                        @Param("pageSize")Integer pageSize);

    @Select("select * from inspection_path limit #{index},#{pageSize}")
    List<InspectionPath> findAllPathByPage(@Param("index")Integer index,
                                           @Param("pageSize")Integer pageSize);

    @Select("select count(*) from inspection_path")
    Integer findAllCount();

    @Select("select count(*) from inspection_path where area_belong = #{area_belong}")
    Integer findCountOfArea(String area_belong);

    @Insert("insert into inspection_path (number,area_belong,pipe_belong,startpoint,endpoint,create_date,description,name) " +
            "values (#{number},#{area_belong}#{pipe_belong},#{startpoint},#{endpoint},#{create_date},#{description},#{name})")
    @Options(useGeneratedKeys = true,keyProperty="id",keyColumn = "id")
    void save(InspectionPath inspectionPath);

    @Update("update inspection_path set number = #{number},area_belong = #{area_belong},pipe_belong = #{pipe_belong},startpoint = #{startpoint}," +
            "endpoint = #{endpoint},create_date = #{create_date},description = #{description}, name = #{name} where id = #{id}")
    void update(InspectionPath inspectionPath);

    @Delete("delete from inspection_path where id=#{id}")
    void deleteById(Integer id);

    @Select("select * from inspection_path where id > 0")
    List<InspectionPath> findAll();
}
