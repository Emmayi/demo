package cn.edu.bupt.demo.dao.PipeGalleryArea;

import cn.edu.bupt.demo.entity.PipeGalleryArea;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class GalleryAreaServiceImpl implements GalleryAreaService {
    @Autowired
    GalleryAreaRepository galleryAreaRepository;

    @Override
    public List<PipeGalleryArea> findAllByPage(Integer page, Integer pageSize) {
        log.trace("Executing findAreaByPage [{}]", page,pageSize);
        Integer index = page * pageSize;
        return galleryAreaRepository.findAllByPage(index,pageSize);
    }

    @Override
    public List<PipeGalleryArea> findGalleryareaBypipe_belongAndPage(String pipe_belong,Integer page, Integer pageSize) {
        Integer index = page*pageSize;
        return galleryAreaRepository.findGalleryareaBypipe_belongAndPage(pipe_belong,index,pageSize);
    }

    @Override
    public Integer findAreaPageNum(Integer size) {
        log.trace("Executing findAreaPageNum [{}]", size);
        Integer num = (galleryAreaRepository.AllAreaCount()+size-1)/size;
        return num;
    }

    @Override
    public PipeGalleryArea findAreaById(Integer id) {
        log.trace("Executing findAreaById [{}]", id);
        return galleryAreaRepository.findGalleryareaById(id);
    }

    @Override
    public Integer getAreaCount() {
        log.trace("Executing getAreaCount [{}]");
        Integer count = galleryAreaRepository.AllAreaCount();
        return count;
    }

    @Override
    public String getId(String response) {
        JSONObject jsonObject = JSONObject.parseObject(response);
        String id=jsonObject.getString("id");
        return id;
    }

    @Override
    public Integer AreaCountOfPipebelong(String pipe_belong) {
        log.trace("Executing AreaCountOfPipebelong [{}]");
        Integer count = galleryAreaRepository.AreaCountOfPipe_belong(pipe_belong);
        return count;
    }

    @Override
    public void save(PipeGalleryArea pipeGalleryArea) {
        log.trace("Executing save [{}]");
        galleryAreaRepository.save(pipeGalleryArea);
    }

    @Override
    public void update(PipeGalleryArea pipeGalleryArea) {
        log.trace("Executing update [{}]");
        galleryAreaRepository.update(pipeGalleryArea);
    }

    @Override
    public String setNumber(String id){
        int Len= 6-id.length();
        String num="GL";
        StringBuilder sb=new StringBuilder();
        sb.append(num);
        for (int i=0;i<Len;i++){
            sb.append("0");
        }
        sb.append(id);
        String number=""+sb;
        return number;
    }

    @Override
    public void fillNumber(String number,Integer id){
        log.trace("Executing fillNumber [{}]", number,id);
        galleryAreaRepository.fillnumber(number,id);
    }

    @Override
    public void deleteById(Integer id) {
        log.trace("Executing deleteById [{}]");
        galleryAreaRepository.deleteById(id);
    }
}
