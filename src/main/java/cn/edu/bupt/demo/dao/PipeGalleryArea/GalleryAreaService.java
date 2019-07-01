package cn.edu.bupt.demo.dao.PipeGalleryArea;

import cn.edu.bupt.demo.entity.PipeGalleryArea;

import java.util.List;

public interface GalleryAreaService {
    List<PipeGalleryArea> findAllByPage(Integer page, Integer pageSize);

    List<PipeGalleryArea> findGalleryareaBypipe_belongAndPage(String pipe_belong,Integer index,Integer pageSize);

    Integer findAreaPageNum(Integer size);

    PipeGalleryArea findAreaById(Integer id);

    Integer getAreaCount();

    Integer AreaCountOfPipebelong(String pipe_belong);

    String getId(String response);

    String getNumber(String number);

    void fillNumber(String number,String id);

    void save(PipeGalleryArea pipeGalleryArea);

    void update(PipeGalleryArea pipeGalleryArea);

    void deleteById(Integer id);

}
