package cn.edu.bupt.demo.dao.PipeGallery;

import cn.edu.bupt.demo.entity.PipeGallery;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PieGalleyServiceImpl implements PipeGalleryService {
    @Autowired
    PipeGalleryRepository pipeGalleryRepository;

    @Override
    public List<PipeGallery> findAllByPage(Integer page, Integer pageSize) {
        log.trace("Executing findPipeByPage [{}]", page,pageSize);
        Integer index = page * pageSize;
        return pipeGalleryRepository.findAllByPage(index,pageSize);
    }

    @Override
    public List<PipeGallery> findPipeByunitAndPage(String unit,Integer page, Integer pageSize) {
        Integer index = page*pageSize;
        return pipeGalleryRepository.findPipeByunitAndPage(unit,index,pageSize);
    }

    @Override
    public Integer findPipePageNum(Integer size) {
        log.trace("Executing findAreaPageNum [{}]", size);
        Integer num = (pipeGalleryRepository.AllPipeCount()+size-1)/size;
        return num;
    }

    @Override
    public PipeGallery findPipeById(Integer id) {
        log.trace("Executing findAreaById [{}]", id);
        return pipeGalleryRepository.findPipeById(id);
    }

    @Override
    public Integer getPipeCount() {
        log.trace("Executing getAreaCount [{}]");
        Integer count = pipeGalleryRepository.AllPipeCount();
        return count;
    }


    @Override
    public Integer PipeCountOfunit(String unit) {
        log.trace("Executing AreaCountOfPipebelong [{}]");
        Integer count = pipeGalleryRepository.PipeCountOfUnit(unit);
        return count;
    }

    @Override
    public void save(PipeGallery pipeGallery) {
        log.trace("Executing save [{}]");
        pipeGalleryRepository.save(pipeGallery);
    }

    @Override
    public void update(PipeGallery pipeGallery) {
        log.trace("Executing update [{}]");
        pipeGalleryRepository.update(pipeGallery);
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
    public void deleteById(Integer id) {
        log.trace("Executing deleteById [{}]");
        pipeGalleryRepository.deleteById(id);
    }

    @Override
    public List<PipeGallery> findAll(){
        log.trace("Executing findAll [{}]");
        return pipeGalleryRepository.findAllPipe();
    }
}
