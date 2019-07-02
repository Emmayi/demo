package cn.edu.bupt.demo.dao.PipeGallery;

import java.util.List;
import cn.edu.bupt.demo.entity.PipeGallery;

public interface PipeGalleryService {
    List<PipeGallery> findAllByPage(Integer page, Integer pageSize);

    List<PipeGallery> findPipeByunitAndPage(String unit,Integer index,Integer pageSize);

    Integer findPipePageNum(Integer size);

    PipeGallery findPipeById(Integer id);

    Integer getPipeCount();

    Integer PipeCountOfunit(String unit);

    String setNumber(String number);

    void save(PipeGallery pipeGallery);

    void update(PipeGallery pipeGallery);

    void deleteById(Integer id);
}
