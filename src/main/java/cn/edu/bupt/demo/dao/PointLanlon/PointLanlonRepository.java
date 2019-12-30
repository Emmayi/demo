package cn.edu.bupt.demo.dao.PointLanlon;


import cn.edu.bupt.demo.entity.PointLanlon;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PointLanlonRepository {

    @Select("select lanlon from point_latlon_relation where pointName = #{pointName}")
    PointLanlon findLatlonByPoint(String pointName);

    @Select("select pointName from point_latlon_relation where id > 0")
    List<PointLanlon> findAllPoint();

    @Insert("insert into point_latlon_relation (pointName,lanlon) values (#{pointName},#{lanlon})")
    @Options(useGeneratedKeys = true, keyProperty = "id",keyColumn = "id")
    void save(PointLanlon pointLanlon);

    @Update("update point_latlon_relation set pointName = #{pointName}, lanlon = #{lanlon} where id =#{id} ")
    void update(PointLanlon pointLanlon);

}
