package cn.edu.bupt.demo.dao.PipeGalleryArea;

import cn.edu.bupt.demo.entity.PipeGalleryArea;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class GalleryAreaServiceImpl implements GalleryAreaService {
    @Autowired
    GalleryAreaRespository galleryAreaRespository;

    @Override
    public List<PipeGalleryArea> findAllByPage(Integer page, Integer pageSize) {
        log.trace("Executing findAreaByPage [{}]", page,pageSize);
        Integer index = page * pageSize;
        return galleryAreaRespository.findAllByPage(index,pageSize);
    }

    @Override
    public List<PipeGalleryArea> findGalleryareaBypipe_belongAndPage(String pipe_belong,Integer page, Integer pageSize) {
        Integer index = page*pageSize;
        return galleryAreaRespository.findGalleryareaBypipe_belongAndPage(pipe_belong,index,pageSize);
    }

    @Override
    public Integer findAreaPageNum(Integer size) {
        log.trace("Executing findAreaPageNum [{}]", size);
        Integer num = (galleryAreaRespository.AllAreaCount()+size-1)/size;
        return num;
    }

    @Override
    public PipeGalleryArea findAreaById(Integer id) {
        log.trace("Executing findAreaById [{}]", id);
        return galleryAreaRespository.findGalleryareaById(id);
    }

    @Override
    public Integer getAreaCount() {
        log.trace("Executing getAreaCount [{}]");
        Integer count = galleryAreaRespository.AllAreaCount();
        return count;
    }

    @Override
    public Integer AreaCountOfPipebelong(String pipe_belong) {
        log.trace("Executing AreaCountOfPipebelong [{}]");
        Integer count = galleryAreaRespository.AreaCountOfPipe_belong(pipe_belong);
        return count;
    }

    @Override
    public void save(PipeGalleryArea pipeGalleryArea) {
        log.trace("Executing save [{}]");
        galleryAreaRespository.save(pipeGalleryArea);
    }

    @Override
    public void update(PipeGalleryArea pipeGalleryArea) {
        log.trace("Executing update [{}]");
        galleryAreaRespository.update(pipeGalleryArea);
    }

    @Override
    public void deleteById(Integer id) {
        log.trace("Executing deleteById [{}]");
        galleryAreaRespository.deleteById(id);
    }
}
