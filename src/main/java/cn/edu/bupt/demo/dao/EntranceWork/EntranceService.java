package cn.edu.bupt.demo.dao.EntranceWork;

import cn.edu.bupt.demo.entity.EntranceWork;
import org.omg.IOP.ENCODING_CDR_ENCAPS;

import java.util.List;

public interface EntranceService {

    List<EntranceWork> findALlByPage(Integer page, Integer pageSize);

    Integer findEntranceWorkPageNum(Integer size);

    EntranceWork findEntranceWorkById(Integer id);

    List<EntranceWork> findEntranceWorkByRange(String activity_range,Integer page, Integer pageSize);

    Integer allWorkCount();

    void save(EntranceWork entranceWork);

    void update(EntranceWork entranceWork);

    void evaluation(String evaluation, Integer id);

    void deleteById(Integer id);

    void deleteByDate(Long date);

    List<EntranceWork> findAllWork();

}
