package cn.edu.bupt.demo.dao.PipeGallery;

import cn.edu.bupt.demo.entity.PipeGallery;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PipeGalleryRepository {
    @Select("select * from pipeGallery limit #{index},#{pageSize}")
    List<PipeGallery> findAllByPage(@Param("index") Integer index, @Param("pageSize") Integer pageSize);

    @Select("select*from pipeGallery where unit = #{unit} limit #{index},#{pageSize}")
    List<PipeGallery> findPipeByunitAndPage(@Param("unit") String unit, @Param("index") Integer index, @Param("pageSize") Integer pageSize);

    @Select("select * from pipeGallery where id = #{id}")
    PipeGallery findPipeById(Integer id);


    @Select("select count(*) from pipeGallery")
    Integer AllPipeCount();

    @Select("select count(*) from pipeGallery where unit = #{unit}")
    Integer PipeCountOfUnit(String unit);

    @Insert("insert into pipeGallery (number,name,length,unit,startpoint,endpoint,description,drawpoint)" +
            " values (#{number},#{name},#{length},#{unit},#{startpoint},#{endpoint},#{description},#{drawpoint})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void save(PipeGallery pipeGallery);

    @Update("update pipeGallery set number = #{number},name = #{name},length = #{length},unit = #{unit}," +
            "startpoint = #{startpoint},endpoint = #{endpoint},description = #{description}, drawpoint=#{drawpoint} where id =#{id} ")
    void update(PipeGallery pipeGallery);

    @Delete("delete from pipeGallery where id=#{id}")
    void deleteById(Integer id);

    @Select("select * from pipeGallery where id > 0")
    List<PipeGallery> findAllPipe();
}
