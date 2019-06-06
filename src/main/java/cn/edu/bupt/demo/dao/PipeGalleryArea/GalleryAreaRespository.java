package cn.edu.bupt.demo.dao.PipeGalleryArea;

import cn.edu.bupt.demo.entity.PipeGalleryArea;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GalleryAreaRespository {

        @Select("select * from pipeGallery_area limit #{index},#{pageSize}")
        List<PipeGalleryArea> findAllByPage(@Param("index") Integer index, @Param("pageSize") Integer pageSize);

        @Select("select*from pipeGallery_area where pipe_belong = #{pipe_belong} limit #{index},#{pageSize}")
        List<PipeGalleryArea> findGalleryareaBypipe_belongAndPage(@Param("pipe_belong")String pipe_belong,@Param("index")Integer index,@Param("pageSize")Integer pageSize);

        @Select("select * from pipeGallery_area where id = #{id}")
        PipeGalleryArea findGalleryareaById(Integer id);


        @Select("select count(*) from pipeGallery_area")
        Integer AllAreaCount();

        @Select("select count(*) from pipeGallery_area where pipe_belong = #{pipe_belong}")
        Integer AreaCountOfPipe_belong(String pipe_belong);

        @Insert("insert into pipeGallery_area (number,name,length,pipe_belong,startpoint,endpoint,description)" +
                " values (#{number},#{name},#{length},#{pipe_belong},#{startpoint},#{endpoint},#{description})")
        @Options(useGeneratedKeys = true, keyProperty = "id")
        void save(PipeGalleryArea pipeGalleryArea);

        @Update("update pipeGallery_area set number = #{number},name = #{name},length = #{length},pipe_belong = #{pipe_belong},startpoint = #{startpoint},endpoint = #{endpoint},description = #{description}" )
        void update(PipeGalleryArea pipeGalleryArea);

        @Delete("delete from pipeGallery_area where id=#{id}")
        void deleteById(Integer id);

        @Select("select * from pipeGallery_area where id > 0")
        List<PipeGalleryArea> findAllArea();
    }

